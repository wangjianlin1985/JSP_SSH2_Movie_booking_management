// 
// 
// 

package com.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import com.util.PageBean;
import java.util.List;
import com.pojo.Ticket;
import com.dao.base.GenericDaoImpl;

public class TicketDao extends GenericDaoImpl<Ticket, Integer>
{
    @Override
    public Ticket selectById(final Integer id) {
        return super.selectById(id);
    }
    
    @Override
    public List<Ticket> selectAll() {
        return super.selectAll();
    }
    
    @Override
    public void insert(final Ticket ticket) {
        super.insert(ticket);
        this.getHibernateTemplate().clear();
    }
    
    @Override
    public void delete(final Ticket ticket) {
        super.delete(ticket);
    }
    
    @Override
    public void update(final Ticket ticket) {
        super.update(ticket);
    }
    
    public PageBean selectByMemberIdByPage(final Integer memberId, final int currentPage, final int pageSize) {
        final Criteria criteria_data = this.createCriteria();
        final Criteria criteria_data_member = criteria_data.createAlias("member", "member");
        criteria_data_member.add((Criterion)Restrictions.eq("member.memberId", (Object)memberId));
        criteria_data.addOrder(Order.desc("ticketDate"));
        criteria_data.setFirstResult((currentPage - 1) * pageSize);
        criteria_data.setMaxResults(pageSize);
        final List<Ticket> lstTickets = (List<Ticket>)criteria_data.list();
        final Criteria criteria_totalRows = this.createCriteria();
        final Criteria criteria_totalRows_member = criteria_totalRows.createAlias("member", "member");
        criteria_totalRows_member.add((Criterion)Restrictions.eq("member.memberId", (Object)memberId));
        criteria_totalRows.setProjection(Projections.rowCount());
        final int totalRows = Integer.parseInt(criteria_totalRows.uniqueResult().toString());
        final PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRows(totalRows);
        pageBean.setData(lstTickets);
        return pageBean;
    }
    
    public List<Ticket> selectTodayOrderByMemberId(final Integer memberId) {
        final Criteria criteria = this.createCriteria();
        criteria.add((Criterion)Restrictions.eq("ticketFlag", (Object)true));
        final Criteria criteria_member = criteria.createAlias("member", "member");
        criteria_member.add((Criterion)Restrictions.eq("member.memberId", (Object)memberId));
        final Criteria criteria_play = criteria.createAlias("play", "play");
        criteria_play.add((Criterion)Restrictions.ge("play.playTime", (Object)new Timestamp(new Date().getTime())));
        criteria.addOrder(Order.desc("ticketDate"));
        final List<Ticket> lstTickets = (List<Ticket>)criteria.list();
        return lstTickets;
    }
    
    public List<Ticket> selectByCode(final String ticketCode) {
        final Criteria criteria = this.createCriteria();
        criteria.add((Criterion)Restrictions.eq("ticketCode", (Object)ticketCode));
        criteria.add((Criterion)Restrictions.eq("ticketFlag", (Object)true));
        final List<Ticket> lstTickets = (List<Ticket>)criteria.list();
        return lstTickets;
    }
    
    public List<Ticket> selectReturnByCode(final String ticketCode) {
        final Criteria criteria = this.createCriteria();
        criteria.add((Criterion)Restrictions.eq("ticketCode", (Object)ticketCode));
        criteria.add((Criterion)Restrictions.eq("ticketFlag", (Object)false));
        final List<Ticket> lstTickets = (List<Ticket>)criteria.list();
        return lstTickets;
    }
}
