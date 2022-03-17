package com.example.bean;

public class AdminBean {
    private String admin_id;
    private String admin_name;
    private int admin_sex;
    private String admin_pwd;
    private int admin_phone;
    private String admin_email;
    private String admin_address;
    private int admin_identity;
    private int admin_state;

    public AdminBean() {
    }

    public AdminBean(String admin_id, String admin_name, int admin_sex, String admin_pwd, int admin_phone, String admin_email, String admin_address, int admin_identity, int admin_state) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_sex = admin_sex;
        this.admin_pwd = admin_pwd;
        this.admin_phone = admin_phone;
        this.admin_email = admin_email;
        this.admin_address = admin_address;
        this.admin_identity = admin_identity;
        this.admin_state = admin_state;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public int getAdmin_sex() {
        return admin_sex;
    }

    public void setAdmin_sex(int admin_sex) {
        this.admin_sex = admin_sex;
    }

    public String getAdmin_pwd() {
        return admin_pwd;
    }

    public void setAdmin_pwd(String admin_pwd) {
        this.admin_pwd = admin_pwd;
    }

    public int getAdmin_phone() {
        return admin_phone;
    }

    public void setAdmin_phone(int admin_phone) {
        this.admin_phone = admin_phone;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_address() {
        return admin_address;
    }

    public void setAdmin_address(String admin_address) {
        this.admin_address = admin_address;
    }

    public int getAdmin_identity() {
        return admin_identity;
    }

    public void setAdmin_identity(int admin_identity) {
        this.admin_identity = admin_identity;
    }

    public int getAdmin_state() {
        return admin_state;
    }

    public void setAdmin_state(int admin_state) {
        this.admin_state = admin_state;
    }

    @Override
    public String toString() {
        return "AdminBean{" +
                "admin_id='" + admin_id + '\'' +
                ", admin_name='" + admin_name + '\'' +
                ", admin_sex=" + admin_sex +
                ", admin_pwd='" + admin_pwd + '\'' +
                ", admin_phone=" + admin_phone +
                ", admin_email='" + admin_email + '\'' +
                ", admin_address='" + admin_address + '\'' +
                ", admin_identity=" + admin_identity +
                ", admin_state=" + admin_state +
                '}';
    }
}
