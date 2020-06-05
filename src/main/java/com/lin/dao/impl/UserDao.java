package com.lin.dao.impl;

import com.lin.dao.IUserDao;
import com.lin.entity.LoginLog;
import com.lin.entity.User;
import com.lin.entity.UserLoginLog;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao implements IUserDao {
    @Autowired
    public SessionFactory sessionFactory ;


    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    /**
     * 获取用户所有登录记录
     * @param user_id
     * @return
     */
    public List<LoginLog> getAllLoginLog(Integer user_id) {
        String sql = "SELECT u.user_name,ul.login_time,ul.login_ip \n" +
                "FROM USER u LEFT JOIN userloginlog ul ON u.user_id=ul.user_id where u.user_id="+user_id;
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(LoginLog.class));
        System.out.println(12);
        List<LoginLog> list = query.list();
        System.out.println(list.size());
        return list;
    }

    /**
     * 用户注册
     * @param user
     */
    public void UserRegist(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    public Integer userLogin(String account, String password) {
        String hql = "";
        if(account.contains("@")){
           hql = " from User u where u.email=? and u.password=? ";
        }else{
            hql = " from User u where u.cellphone=? and u.password=? ";
        }

        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,account);
        query.setString(1,password);
        List list = query.list();
        return list.size();
    }

    /**
     * 根据用户账号id查询用户
     * @param account
     * @return
     */
    public Integer getUserId(String account) {
        String hql = "";
        if(account.contains("@")){
            hql = " from User u where u.email=?";
        }else{
            hql = " from User u where u.cellphone=?";
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        Query query1 = query.setString(0, account);
        List list = query.list();
        User user = (User) list.get(1);

        return user.getUser_id();
    }



    /**
     * 根据用户账户查询用户信息
     * @param account
     * @return
     */
    public User getUser(String account) {
        List<User> list = null;
        String hql = "";
        if(account.contains("@")){
            hql = "from User where email=?";
        }else{
            hql = "from User where cellphone=?";
        }
        list = sessionFactory.getCurrentSession().createQuery(hql).setString(0, account).list();

        if(list.size()==0){
            return null;
        }else{
            return list.get(0);
        }

    }

    /**
     * 保存用户登录记录
     * @param ull
     */
    public void saveLoginLog(UserLoginLog ull) {

        sessionFactory.getCurrentSession().save(ull);

    }
}
