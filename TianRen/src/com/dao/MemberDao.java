// 
// 
// 

package com.dao;

import java.io.Serializable;
import com.util.PageBean;
import java.util.List;
import com.pojo.Member;
import com.dao.base.GenericDaoImpl;

public class MemberDao extends GenericDaoImpl<Member, Integer>
{
    @Override
    public Member selectById(final Integer id) {
        return super.selectById(id);
    }
    
    @Override
    public List<Member> selectAll() {
        return super.selectAll();
    }
    
    @Override
    public void insert(final Member member) {
        super.insert(member);
    }
    
    @Override
    public void delete(final Member member) {
        super.delete(member);
    }
    
    @Override
    public void update(final Member member) {
        super.update(member);
        this.getHibernateTemplate().flush();
    }
    
    @Override
    public List<Member> selectByExample(final Member member) {
        return super.selectByExample(member);
    }
    
    @Override
    public PageBean selectLikeByEntityByPage(final Member member, final String[] propertyNames, final int currentPage, final int pageSize) {
        return super.selectLikeByEntityByPage(member, propertyNames, currentPage, pageSize);
    }
}
