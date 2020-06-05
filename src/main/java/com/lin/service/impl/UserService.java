package com.lin.service.impl;

import com.lin.dao.IUserDao;
import com.lin.entity.LoginLog;
import com.lin.entity.User;
import com.lin.entity.UserLoginLog;
import com.lin.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Resource
    private IUserDao userDao;

    /**
     * 获取用户的所有登录记录
     * @param user_id
     * @return
     */
    public List<LoginLog> getAllLoginLog(Integer user_id) {
        System.out.println("业务层");
        List<LoginLog> allLoginLog = userDao.getAllLoginLog(user_id);
        return allLoginLog;
    }

    public void UserRegist(User user) {
        userDao.UserRegist(user);
    }

    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    public Integer userLogin(String account, String password) {
        Integer n = userDao.userLogin(account, password);
        if(n==0){
            return 0;
        }else{
            //根据账号查询用户id
            Integer userId = userDao.getUserId(account);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(date);
            UserLoginLog ull = new UserLoginLog();
            ull.setUser_id(userId);
            ull.setLogin_time(format);
            userDao.saveLoginLog(ull);
            return userId;
        }

    }

    /**
     * 根据用户账号查询用户信息
     * @param account
     * @return
     */
    public User getUser(String account) {
        return userDao.getUser(account);
    }

    /**
     * 保存用户登录信息
     * @param ull
     */
    public void saveLoginLog(UserLoginLog ull) {
        userDao.saveLoginLog(ull);
    }
}
