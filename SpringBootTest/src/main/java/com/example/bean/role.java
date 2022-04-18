package com.example.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "role")
public class role {
    @TableId(value = "role_id")
    private int roleId;
    private String roleName;
    private String roleFunction;
    private int rolePower;

    public role() {
    }

    public role(int roleId, String roleName, String roleFunction, int rolePower) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleFunction = roleFunction;
        this.rolePower = rolePower;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleFunction() {
        return roleFunction;
    }

    public void setRoleFunction(String roleFunction) {
        this.roleFunction = roleFunction;
    }

    public int getRolePower() {
        return rolePower;
    }

    public void setRolePower(int rolePower) {
        this.rolePower = rolePower;
    }

    @Override
    public String toString() {
        return "role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleFunction='" + roleFunction + '\'' +
                ", rolePower=" + rolePower +
                '}';
    }
}
