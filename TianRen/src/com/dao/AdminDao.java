// 
// 
// 

package com.dao;

import java.io.Serializable;
import java.util.List;
import com.pojo.Admin;
import com.dao.base.GenericDaoImpl;

public class AdminDao extends GenericDaoImpl<Admin, Integer>
{
    @Override
    public Admin selectById(final Integer id) {
        return super.selectById(id);
    }
    
    @Override
    public List<Admin> selectAll() {
        return super.selectAll();
    }
    
    @Override
    public void insert(final Admin admin) {
        super.insert(admin);
    }
    
    @Override
    public void delete(final Admin admin) {
        super.delete(admin);
    }
    
    @Override
    public void update(final Admin admin) {
        super.update(admin);
        this.getHibernateTemplate().flush();
    }
    
    public List<Admin> selectEqualByEntity(final Admin admin) {
        return super.selectByExample(admin);
    }
}
