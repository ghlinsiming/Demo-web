package com.lin.entity;

import javax.persistence.*;

@Entity
@Table(name = "userloginlog")
public class UserLoginLog {

    @Id
    @GeneratedValue
    @Column(name="login_id")
    private Integer login_id;
    private Integer user_id;
    private String login_time;
    private String login_ip;

    public Integer getLogin_id() {
        return login_id;
    }

    public void setLogin_id(Integer login_id) {
        this.login_id = login_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    @Override
    public String toString() {
        return "UserLoginLog{" +
                "login_id=" + login_id +
                ", user_id=" + user_id +
                ", login_time=" + login_time +
                ", login_ip='" + login_ip + '\'' +
                '}';
    }
}

