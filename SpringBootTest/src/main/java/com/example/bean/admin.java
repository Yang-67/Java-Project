package com.example.bean;

import com.baomidou.mybatisplus.annotation.*;

@TableName(value = "admin")
public class admin {
    @TableId(value = "admin_id")
    private String adminId;
    private String adminName;
    private int adminAge;
    private int adminSex;
    private String adminPwd;
    private String adminPhone;
    private String adminEmail;
    private String adminAddress;
    private int adminIdentity;
    private int adminState;
    private String adminUrl;

    public admin() {
    }

    public admin(String adminId, String adminName, int adminAge, int adminSex, String adminPwd, String adminPhone, String adminEmail, String adminAddress, int adminIdentity, int adminState, String adminUrl) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminAge = adminAge;
        this.adminSex = adminSex;
        this.adminPwd = adminPwd;
        this.adminPhone = adminPhone;
        this.adminEmail = adminEmail;
        this.adminAddress = adminAddress;
        this.adminIdentity = adminIdentity;
        this.adminState = adminState;
        this.adminUrl = adminUrl;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getAdminAge() {
        return adminAge;
    }

    public void setAdminAge(int adminAge) {
        this.adminAge = adminAge;
    }

    public int getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(int adminSex) {
        this.adminSex = adminSex;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public int getAdminIdentity() {
        return adminIdentity;
    }

    public void setAdminIdentity(int adminIdentity) {
        this.adminIdentity = adminIdentity;
    }

    public int getAdminState() {
        return adminState;
    }

    public void setAdminState(int adminState) {
        this.adminState = adminState;
    }

    public String getAdminUrl() {
        return adminUrl;
    }

    public void setAdminUrl(String adminUrl) {
        this.adminUrl = adminUrl;
    }

    @Override
    public String toString() {
        return "admin{" +
                "adminId='" + adminId + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminAge=" + adminAge +
                ", adminSex=" + adminSex +
                ", adminPwd='" + adminPwd + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminAddress='" + adminAddress + '\'' +
                ", adminIdentity=" + adminIdentity +
                ", adminState=" + adminState +
                ", adminUrl='" + adminUrl + '\'' +
                '}';
    }
}
