package com.lin.entity;

public class LoginLog {
    private String user_name;
    private String login_time;
    private String login_ip;

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "user_name='" + user_name + '\'' +
                ", login_time='" + login_time + '\'' +
                ", login_ip='" + login_ip + '\'' +
                '}';
    }
}
