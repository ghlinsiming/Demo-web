package com.lin.service;

import com.lin.entity.LoginLog;
import com.lin.entity.User;
import com.lin.entity.UserLoginLog;

import java.util.List;

public interface IUserService {


    List<LoginLog> getAllLoginLog(Integer user_id);

    void UserRegist(User user);

    Integer userLogin(String account, String password);

    User getUser(String account);

    void saveLoginLog(UserLoginLog ull);
}
