// 
// 
// 

package com.service;

import com.dao.base.GenericDaoImpl;
import java.util.List;
import com.pojo.Admin;
import com.dao.AdminDao;

public class AdminService
{
    private AdminDao adminDao;
    
    public void setAdminDao(final AdminDao adminDao) {
        this.adminDao = adminDao;
    }
    
    public Admin login(final Admin admin) {
        final List<Admin> lstAdmin =  this.adminDao.selectByExample(admin);
        if (lstAdmin.size() == 0) {
            return null;
        }
        return lstAdmin.get(0);
    }
    
    public List<Admin> findAll() {
        return this.adminDao.selectAll();
    }
    
    public void create(final Admin admin) {
        this.adminDao.insert(admin);
    }
    
    public boolean findByName(final String adminName) {
        final Admin admin = new Admin();
        admin.setAdminName(adminName);
        final List<Admin> lstAdmin =  this.adminDao.selectByExample(admin);
        return lstAdmin.size() > 0;
    }
    
    public Admin findById(final Admin admin) {
        return this.adminDao.selectById(admin.getAdminId());
    }
    
    public void modifyAdmin(final Admin admin) {
        this.adminDao.update(admin);
    }
    
    public void remove(final Admin admin) {
        this.adminDao.delete(this.adminDao.selectById(admin.getAdminId()));
    }
}
