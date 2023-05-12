// 
// 
// 

package com.dao.base;

import com.util.PageBean;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import java.util.List;
import java.io.Serializable;

public interface IGenericDao<T, PK extends Serializable>
{
    T selectById(PK p0);
    
    List<T> selectAll();
    
    void insert(T p0);
    
    void update(T p0);
    
    void delete(T p0);
    
    DetachedCriteria createDetachedCriteria();
    
    Criteria createCriteria();
    
    PageBean selectLikeByEntityByPage(T p0, String[] p1, int p2, int p3);
    
    List<T> selectByExample(T p0);
    
    PageBean selectByPage(int p0, int p1);
}
