// 
// 
// 

package com.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.MatchMode;
import java.util.Calendar;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import java.sql.Timestamp;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Order;
import com.util.PageBean;
import java.util.List;
import com.pojo.Play;
import com.dao.base.GenericDaoImpl;

public class PlayDao extends GenericDaoImpl<Play, Integer>
{
    @Override
    public Play selectById(final Integer id) {
        return super.selectById(id);
    }
    
    public List<Play> select() {
        return super.selectAll();
    }
    
    @Override
    public void insert(final Play play) {
        super.insert(play);
    }
    
    @Override
    public void delete(final Play play) {
        super.delete(play);
    }
    
    @Override
    public void update(final Play play) {
        super.update(play);
        this.getHibernateTemplate().flush();
    }
    
    @Override
    public PageBean selectByPage(final int currentPage, final int pageSize) {
        final Criteria criteria_data = this.createCriteria();
        criteria_data.addOrder(Order.desc("playTime"));
        criteria_data.setFirstResult((currentPage - 1) * pageSize);
        criteria_data.setMaxResults(pageSize);
        final List<Play> lstPlay = (List<Play>)criteria_data.list();
        final Criteria criteria_totalRows = this.createCriteria();
        criteria_totalRows.setProjection(Projections.rowCount());
        final int totalRows = Integer.parseInt(criteria_totalRows.uniqueResult().toString());
        final PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRows(totalRows);
        pageBean.setData(lstPlay);
        return pageBean;
    }
    
    public PageBean selectByTimeByPage(final Timestamp beginTime, final Timestamp endTime, final int currentPage, final int pageSize) {
        final Criteria criteria_data = this.createCriteria();
        if (beginTime != null) {
            criteria_data.add((Criterion)Restrictions.ge("playTime", (Object)beginTime));
        }
        if (endTime != null) {
            criteria_data.add((Criterion)Restrictions.le("playTime", (Object)endTime));
        }
        criteria_data.addOrder(Order.desc("playTime"));
        criteria_data.setFirstResult((currentPage - 1) * pageSize);
        criteria_data.setMaxResults(pageSize);
        final List<Play> lstPlay = (List<Play>)criteria_data.list();
        final Criteria criteria_totalRows = this.createCriteria();
        if (beginTime != null) {
            criteria_totalRows.add((Criterion)Restrictions.ge("playTime", (Object)beginTime));
        }
        if (endTime != null) {
            criteria_totalRows.add((Criterion)Restrictions.le("playTime", (Object)endTime));
        }
        criteria_totalRows.setProjection(Projections.rowCount());
        final int totalRows = Integer.parseInt(criteria_totalRows.uniqueResult().toString());
        final PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRows(totalRows);
        pageBean.setData(lstPlay);
        return pageBean;
    }
    
    public Map<String, List> selectByCondition(final String movieName, final String time, final String editionId, final int TabbedPanel) {
        final Calendar cd = Calendar.getInstance();
        final Timestamp now = new Timestamp(cd.getTimeInMillis());
        final int year = cd.get(1);
        final int month = cd.get(2);
        final int day = cd.get(5);
        cd.set(year, month, day, 0, 0, 0);
        final Timestamp todayBegin = new Timestamp(cd.getTimeInMillis());
        cd.set(year, month, day, 12, 0, 0);
        final Timestamp todayNoon = new Timestamp(cd.getTimeInMillis());
        cd.set(year, month, day, 18, 0, 0);
        final Timestamp todayPmEnd = new Timestamp(cd.getTimeInMillis());
        cd.set(year, month, day + 1, 0, 0, 0);
        final Timestamp tomorrowBegin = new Timestamp(cd.getTimeInMillis());
        cd.set(year, month, day + 1, 12, 0, 0);
        final Timestamp tomorrowNoon = new Timestamp(cd.getTimeInMillis());
        cd.set(year, month, day + 1, 18, 0, 0);
        final Timestamp tomorrowPmEnd = new Timestamp(cd.getTimeInMillis());
        cd.set(year, month, day + 1, 23, 59, 59);
        final Timestamp tomorrowEnd = new Timestamp(cd.getTimeInMillis());
        Timestamp beginTime = null;
        Timestamp endTime = null;
        final Criteria criteria = this.createCriteria();
        if ("".equalsIgnoreCase(time) && TabbedPanel == 1) {
            beginTime = now;
            endTime = tomorrowBegin;
        }
        else if ("am".equalsIgnoreCase(time) && TabbedPanel == 1) {
            beginTime = todayBegin;
            endTime = todayNoon;
        }
        else if ("pm".equalsIgnoreCase(time) && TabbedPanel == 1) {
            beginTime = todayNoon;
            endTime = todayPmEnd;
        }
        else if ("night".equalsIgnoreCase(time) && TabbedPanel == 1) {
            beginTime = todayPmEnd;
            endTime = tomorrowBegin;
        }
        else if ("".equalsIgnoreCase(time) && TabbedPanel == 2) {
            beginTime = tomorrowBegin;
            endTime = tomorrowEnd;
        }
        else if ("am".equalsIgnoreCase(time) && TabbedPanel == 2) {
            beginTime = tomorrowBegin;
            endTime = tomorrowNoon;
        }
        else if ("pm".equalsIgnoreCase(time) && TabbedPanel == 2) {
            beginTime = tomorrowNoon;
            endTime = tomorrowPmEnd;
        }
        else if ("night".equalsIgnoreCase(time) && TabbedPanel == 2) {
            beginTime = tomorrowPmEnd;
            endTime = tomorrowEnd;
        }
        criteria.add((Criterion)Restrictions.gt("playTime", (Object)now));
        criteria.add((Criterion)Restrictions.gt("playTime", (Object)beginTime));
        criteria.add((Criterion)Restrictions.le("playTime", (Object)endTime));
        final Criteria criteria_movie = criteria.createAlias("movie", "movie");
        criteria_movie.add((Criterion)Restrictions.like("movie.movieName", movieName, MatchMode.ANYWHERE));
        criteria_movie.addOrder(Order.asc("movie.movieName"));
        if (!"".equals(editionId.trim()) && editionId.trim().length() != 0) {
            final Criteria criteria_edition = criteria_movie.createAlias("movie.edition", "edition");
            criteria_edition.add((Criterion)Restrictions.eq("edition.editionId", (Object)Integer.valueOf(editionId.trim())));
        }
        criteria.addOrder(Order.asc("playTime"));
        final List<Play> lstPlaies = (List<Play>)criteria.list();
        final Criteria criteria_kind = criteria_movie.createAlias("movie.kind", "kind");
        criteria_kind.setProjection((Projection)Projections.projectionList().add((Projection)Projections.groupProperty("movie.movieName")).add((Projection)Projections.property("kind.kindName")).add((Projection)Projections.property("movie.movieLong")).add((Projection)Projections.property("movie.movieActor")).add((Projection)Projections.property("movie.movieDate")).add((Projection)Projections.property("movie.movieDirector")).add((Projection)Projections.property("movie.movieInfo")).add((Projection)Projections.property("movie.moviePhoto")));
        final List list = criteria.list();
        final List<Map<String, Object>> lstMapMovies = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list.size(); ++i) {
            final Object[] obj = (Object[]) list.get(i);
            final Map<String, Object> mapMovies = new HashMap<String, Object>();
            mapMovies.put("movieName", obj[0].toString().trim());
            mapMovies.put("movieKindName", obj[1].toString().trim());
            mapMovies.put("movieLong", Integer.parseInt(obj[2].toString().trim()));
            mapMovies.put("movieActor", obj[3].toString().trim());
            mapMovies.put("movieDate", obj[4]);
            mapMovies.put("movieDirector", obj[5].toString().trim());
            mapMovies.put("movieInfo", obj[6].toString().trim());
            mapMovies.put("moviePhoto", obj[7].toString().trim());
            lstMapMovies.add(mapMovies);
        }
        final Map<String, List> mapTime = new HashMap<String, List>();
        mapTime.put("lstPlaies", lstPlaies);
        mapTime.put("lstMapMovies", lstMapMovies);
        return mapTime;
    }
    
    public Play selectSeatByPlayId(final int playId) {
        final Criteria criteria = this.createCriteria();
        criteria.add(Restrictions.idEq((Object)playId));
        final Play play = (Play)criteria.uniqueResult();
        return play;
    }
}
