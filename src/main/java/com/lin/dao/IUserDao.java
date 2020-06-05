package com.lin.dao;

import com.lin.entity.LoginLog;
import com.lin.entity.User;
import com.lin.entity.UserLoginLog;

import java.util.List;

public interface IUserDao {

    List<LoginLog> getAllLoginLog(Integer user_id);

    void UserRegist(User user);

    Integer userLogin(String account, String password);

    Integer getUserId(String account);



    User getUser(String account);

    void saveLoginLog(UserLoginLog ull);
}
