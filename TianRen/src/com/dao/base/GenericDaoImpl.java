// 
// 
// 

package com.dao.base;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;
import org.apache.commons.beanutils.PropertyUtils;
import com.util.PageBean;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import java.util.List;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.io.Serializable;

public class GenericDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements IGenericDao<T, PK>
{
    private Class<T> entityClass;
    
    public GenericDaoImpl() {
        this.entityClass = null;
        final Class c = this.getClass();
        final Type t = c.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            final Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            this.entityClass = (Class<T>)p[0];
        }
    }
    
    public T selectById(final PK id) {
        return (T)this.getHibernateTemplate().get((Class)this.entityClass, (Serializable)id);
    }
    
    public List<T> selectAll() {
        return (List<T>)this.getHibernateTemplate().loadAll((Class)this.entityClass);
    }
    
    public void insert(final T entity) {
        this.getHibernateTemplate().save((Object)entity);
    }
    
    public void update(final T entity) {
        this.getHibernateTemplate().update((Object)entity);
    }
    
    public void delete(final T entity) {
        this.getHibernateTemplate().delete((Object)entity);
    }
    
    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass((Class)this.entityClass);
    }
    
    public Criteria createCriteria() {
        return this.createDetachedCriteria().getExecutableCriteria(this.getSession());
    }
    
    public PageBean selectLikeByEntityByPage(final T entity, final String[] propertyNames, final int currentPage, final int pageSize) {
        final Criteria criteria = this.createCriteria();
        for (final String property : propertyNames) {
            try {
                final Object value = PropertyUtils.getProperty((Object)entity, property);
                if (value instanceof String) {
                    criteria.add((Criterion)Restrictions.like(property, (String)value, MatchMode.ANYWHERE));
                    criteria.addOrder(Order.asc(property));
                }
                criteria.setFirstResult((currentPage - 1) * pageSize);
                criteria.setMaxResults(pageSize);
            }
            catch (Exception ex) {}
        }
        final List<T> lstLikeEntity = (List<T>)criteria.list();
        final Criteria criteria_totalRows = this.createCriteria();
        for (final String property2 : propertyNames) {
            try {
                final Object value2 = PropertyUtils.getProperty((Object)entity, property2);
                if (value2 instanceof String) {
                    criteria_totalRows.add((Criterion)Restrictions.like(property2, (String)value2, MatchMode.ANYWHERE));
                }
            }
            catch (Exception ex2) {}
        }
        criteria_totalRows.setProjection(Projections.rowCount());
        final int totalRows = Integer.parseInt(criteria_totalRows.uniqueResult().toString());
        final PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRows(totalRows);
        pageBean.setData(lstLikeEntity);
        return pageBean;
    }
    
    public List<T> selectByExample(final T entity) {
        final Criteria criteria = this.createCriteria();
        criteria.add((Criterion)Example.create((Object)entity));
        return (List<T>)criteria.list();
    }
    
    public PageBean selectByPage(final int currentPage, final int pageSize) {
        final Criteria criteria_data = this.createCriteria();
        criteria_data.setFirstResult((currentPage - 1) * pageSize);
        criteria_data.setMaxResults(pageSize);
        final List<T> entityClass = (List<T>)criteria_data.list();
        final Criteria criteria_totalRows = this.createCriteria();
        criteria_totalRows.setProjection(Projections.rowCount());
        final int totalRows = Integer.parseInt(criteria_totalRows.uniqueResult().toString());
        final PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRows(totalRows);
        pageBean.setData(entityClass);
        return pageBean;
    }
}
