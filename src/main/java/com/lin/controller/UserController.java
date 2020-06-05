package com.lin.controller;

import com.lin.entity.LoginLog;
import com.lin.entity.User;
import com.lin.entity.UserLoginLog;
import com.lin.entity.bo.UserBo;
import com.lin.entity.vo.Flag;
import com.lin.service.IUserService;
import com.lin.util.MD5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 用户登录
     * @param userBo
     * @param req
     * @return
     */
    @PostMapping("/loginCheck")
    @ResponseBody
    public Flag loignCheck(@RequestBody UserBo userBo, HttpServletRequest req){
        //根据账号查询用户
        User user = userService.getUser(userBo.getAccount());
        System.out.println(user);
        Flag flag = new Flag();
        //判断用户是否为空
        if(user==null){
            //说明账号或密码错误
            flag.setInfo("用户名或密码错误");
            return flag;
        }

            //取出该用户的加密盐
            String salt = user.getSalt();
            //用改盐加密用户输入的密码和查出来的用户密码比较
            String md5 = MD5Utils.getMD5(salt + userBo.getPassword());
            if(md5.equals(user.getPassword())){
                //说明密码正确
                //保存用户到session作用域
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                //获取客户端IP
                String remoteAddr = req.getRemoteAddr();
                //把此次登录信息保存到登录记录表中
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                UserLoginLog ull = new UserLoginLog();
                ull.setLogin_time(sdf.format(date));
                ull.setUser_id(user.getUser_id());
                ull.setLogin_ip(remoteAddr);
                userService.saveLoginLog(ull);

                flag.setInfo("登录成功");
                return flag;

        }else{
                flag.setInfo("用户名或密码错误");
                return flag;
            }


    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/regist")
    @ResponseBody
    public Flag regist(@RequestBody User user){
        Flag flag = new Flag();
        //获取加密盐
        String salt = UUID.randomUUID().toString().replace("-","").substring(0,5);
        //用加密盐加密密码
        String md5 = MD5Utils.getMD5(salt + user.getPassword());
        user.setPassword(md5);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreate_time(sdf.format(date));
        user.setSalt(salt);
        user.setState("1");
        //保存注册用户之前先判断该手机号或邮箱是否已经被注册过了
        User user1 = userService.getUser(user.getCellphone());
        if(user1!=null){
            //该手机号已经注册过了
            flag.setInfo("该手机号已经注册过了");
            return flag;
        }
        User user2 = userService.getUser(user.getEmail());
        if(user2!=null){
            //该邮箱已经注册过了
            flag.setInfo("该邮箱已经注册过了");
            return flag;
        }
        userService.UserRegist(user);
        flag.setInfo("注册成功");
        return flag;
    }

    /**
     * 显示登录表
     * @return
     */
    @GetMapping("/showLoginList")
    public String showLoginList(){
        System.out.println("显示登录列表");
        return "views/userLoginList";
    }

    /**
     * 获取用户所有登录记录
     * @param req
     * @return
     */
    @RequestMapping("/getAllLoginLog")
    @ResponseBody
    public List<LoginLog> getAllLoginLog(HttpServletRequest req){
        System.out.println("进来了。。。");
        //获取当前登录的用户
         User user = (User) req.getSession().getAttribute("user");
         //获取当前账户的所有的登录记录
        List<LoginLog> allLoginLog = userService.getAllLoginLog(user.getUser_id());
        System.out.println(allLoginLog);
        return allLoginLog;
    }
}
