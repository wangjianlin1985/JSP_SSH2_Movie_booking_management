// 
// 
// 

package com.pojo;

import java.io.Serializable;

public class Admin implements Serializable
{
    private Integer adminId;
    private String adminName;
    private String adminPwd;
    private String adminPrivilege;
    
    public Admin() {
    }
    
    public Admin(final String adminName, final String adminPwd, final String adminPrivilege) {
        this.adminName = adminName;
        this.adminPwd = adminPwd;
        this.adminPrivilege = adminPrivilege;
    }
    
    public Integer getAdminId() {
        return this.adminId;
    }
    
    public void setAdminId(final Integer adminId) {
        this.adminId = adminId;
    }
    
    public String getAdminName() {
        return this.adminName;
    }
    
    public void setAdminName(final String adminName) {
        this.adminName = adminName;
    }
    
    public String getAdminPwd() {
        return this.adminPwd;
    }
    
    public void setAdminPwd(final String adminPwd) {
        this.adminPwd = adminPwd;
    }
    
    public String getAdminPrivilege() {
        return this.adminPrivilege;
    }
    
    public void setAdminPrivilege(final String adminPrivilege) {
        this.adminPrivilege = adminPrivilege;
    }
}
