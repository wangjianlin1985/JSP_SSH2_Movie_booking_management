// 
// 
// 

package com.dao;

import java.io.Serializable;
import java.util.List;
import com.pojo.Ad;
import com.dao.base.GenericDaoImpl;

public class AdDao extends GenericDaoImpl<Ad, Integer>
{
    @Override
    public Ad selectById(final Integer id) {
        return super.selectById(id);
    }
    
    @Override
    public List<Ad> selectAll() {
        return super.selectAll();
    }
    
    @Override
    public void update(final Ad ad) {
        super.update(ad);
        this.getHibernateTemplate().flush();
    }
}
