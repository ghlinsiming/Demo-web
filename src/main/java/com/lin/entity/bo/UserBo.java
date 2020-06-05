package com.lin.entity.bo;

public class UserBo {

    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserBo{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
