package com.wz.appplay.bean.requestbean;

/**
 * Created by wz on 17-5-15.
 */

public class LoginRequestBean {
    private String Phone;
    private String passwork;

    public LoginRequestBean() {
    }

    public LoginRequestBean(String phone, String passwork) {
        Phone = phone;
        this.passwork = passwork;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }
}
