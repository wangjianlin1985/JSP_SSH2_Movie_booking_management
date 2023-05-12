// 
// 
// 

package com.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import com.util.JSONMovie;
import org.hibernate.Criteria;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import java.util.Date;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import com.util.PageBean;
import java.util.List;
import com.pojo.Movie;
import com.dao.base.GenericDaoImpl;

public class MovieDao extends GenericDaoImpl<Movie, Integer>
{
    @Override
    public Movie selectById(final Integer id) {
        return super.selectById(id);
    }
    
    @Override
    public List<Movie> selectAll() {
        return super.selectAll();
    }
    
    @Override
    public void insert(final Movie movie) {
        super.insert(movie);
        this.getHibernateTemplate().clear();
    }
    
    @Override
    public void delete(final Movie movie) {
        super.delete(movie);
    }
    
    @Override
    public void update(final Movie movie) {
        super.update(movie);
    }
    
    @Override
    public PageBean selectLikeByEntityByPage(final Movie movie, final String[] propertyNames, final int currentPage, final int pageSize) {
        return super.selectLikeByEntityByPage(movie, propertyNames, currentPage, pageSize);
    }
    
    @Override
    public PageBean selectByPage(final int currentPage, final int pageSize) {
        return super.selectByPage(currentPage, pageSize);
    }
    
    public PageBean selectBeforeByPage(final int currentPage, final int pageSize) {
        final Criteria criteria_data = this.createCriteria();
        criteria_data.setProjection((Projection)Projections.projectionList().add(Projections.distinct((Projection)Projections.property("movieName"))).add((Projection)Projections.property("movieActor")).add((Projection)Projections.property("movieDate")).add((Projection)Projections.property("moviePhoto")).add((Projection)Projections.property("movieInfo")));
        criteria_data.add((Criterion)Restrictions.le("movieDate", (Object)new Date()));
        criteria_data.addOrder(Order.desc("movieDate"));
        criteria_data.setFirstResult((currentPage - 1) * pageSize);
        criteria_data.setMaxResults(pageSize);
        final List list = criteria_data.list();
        final List<Movie> beforeMovies = new ArrayList<Movie>();
        for (int i = 0; i < list.size(); ++i) {
            final Object[] obj = (Object[]) list.get(i);
            final Movie movie = new Movie();
            movie.setMovieName(obj[0].toString());
            movie.setMovieActor(obj[1].toString());
            movie.setMovieDate((Timestamp)obj[2]);
            movie.setMoviePhoto(obj[3].toString());
            movie.setMovieInfo(obj[4].toString());
            beforeMovies.add(movie);
        }
        final Criteria criteria_totalRows = this.createCriteria();
        criteria_totalRows.setProjection((Projection)Projections.countDistinct("movieName"));
        criteria_totalRows.add((Criterion)Restrictions.le("movieDate", (Object)new Date()));
        final int totalRows = Integer.parseInt(criteria_totalRows.uniqueResult().toString());
        final PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRows(totalRows);
        pageBean.setData(beforeMovies);
        return pageBean;
    }
    
    public PageBean selectAfterByPage(final int currentPage, final int pageSize) {
        final Criteria criteria_data = this.createCriteria();
        criteria_data.setProjection((Projection)Projections.projectionList().add(Projections.distinct((Projection)Projections.property("movieName"))).add((Projection)Projections.property("movieActor")).add((Projection)Projections.property("movieDate")).add((Projection)Projections.property("moviePhoto")).add((Projection)Projections.property("movieInfo")));
        criteria_data.add((Criterion)Restrictions.gt("movieDate", (Object)new Date()));
        criteria_data.addOrder(Order.asc("movieDate"));
        criteria_data.setFirstResult((currentPage - 1) * pageSize);
        criteria_data.setMaxResults(pageSize);
        final List list = criteria_data.list();
        final List<Movie> afterMovies = new ArrayList<Movie>();
        for (int i = 0; i < list.size(); ++i) {
            final Object[] obj = (Object[]) list.get(i);
            final Movie movie = new Movie();
            movie.setMovieName(obj[0].toString());
            movie.setMovieActor(obj[1].toString());
            movie.setMovieDate((Timestamp)obj[2]);
            movie.setMoviePhoto(obj[3].toString());
            movie.setMovieInfo(obj[4].toString());
            afterMovies.add(movie);
        }
        final Criteria criteria_totalRows = this.createCriteria();
        criteria_totalRows.setProjection((Projection)Projections.countDistinct("movieName"));
        criteria_totalRows.add((Criterion)Restrictions.gt("movieDate", (Object)new Date()));
        final int totalRows = Integer.parseInt(criteria_totalRows.uniqueResult().toString());
        final PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRows(totalRows);
        pageBean.setData(afterMovies);
        return pageBean;
    }
    
    public PageBean selectBeforeByPageByJson(final int currentPage, final int pageSize) {
        final Criteria criteria_data = this.createCriteria();
        criteria_data.setProjection((Projection)Projections.projectionList().add(Projections.distinct((Projection)Projections.property("movieName"))).add((Projection)Projections.property("movieActor")).add((Projection)Projections.property("movieDate")).add((Projection)Projections.property("moviePhoto")).add((Projection)Projections.property("movieInfo")));
        criteria_data.add((Criterion)Restrictions.le("movieDate", (Object)new Date()));
        criteria_data.addOrder(Order.desc("movieDate"));
        criteria_data.setFirstResult((currentPage - 1) * pageSize);
        criteria_data.setMaxResults(pageSize);
        final List list = criteria_data.list();
        final List<JSONMovie> JSONbeforeMovies = new ArrayList<JSONMovie>();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5");
        for (int i = 0; i < list.size(); ++i) {
            final Object[] obj = (Object[]) list.get(i);
            final JSONMovie movie = new JSONMovie();
            movie.setMovieName(obj[0].toString());
            movie.setMovieActor(obj[1].toString());
            movie.setMovieDate(sdf.format(new Date(((Timestamp)obj[2]).getTime())));
            movie.setMoviePhoto(obj[3].toString());
            movie.setMovieInfo(obj[4].toString());
            JSONbeforeMovies.add(movie);
        }
        final Criteria criteria_totalRows = this.createCriteria();
        criteria_totalRows.setProjection((Projection)Projections.countDistinct("movieName"));
        criteria_totalRows.add((Criterion)Restrictions.le("movieDate", (Object)new Date()));
        final int totalRows = Integer.parseInt(criteria_totalRows.uniqueResult().toString());
        final PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRows(totalRows);
        pageBean.setData(JSONbeforeMovies);
        return pageBean;
    }
    
    public PageBean selectAfterByPageByJson(final int currentPage, final int pageSize) {
        final Criteria criteria_data = this.createCriteria();
        criteria_data.setProjection((Projection)Projections.projectionList().add(Projections.distinct((Projection)Projections.property("movieName"))).add((Projection)Projections.property("movieActor")).add((Projection)Projections.property("movieDate")).add((Projection)Projections.property("moviePhoto")).add((Projection)Projections.property("movieInfo")));
        criteria_data.add((Criterion)Restrictions.gt("movieDate", (Object)new Date()));
        criteria_data.addOrder(Order.asc("movieDate"));
        criteria_data.setFirstResult((currentPage - 1) * pageSize);
        criteria_data.setMaxResults(pageSize);
        final List list = criteria_data.list();
        final List<JSONMovie> JSONafterMovies = new ArrayList<JSONMovie>();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5");
        for (int i = 0; i < list.size(); ++i) {
            final Object[] obj = (Object[]) list.get(i);
            final JSONMovie movie = new JSONMovie();
            movie.setMovieName(obj[0].toString());
            movie.setMovieActor(obj[1].toString());
            movie.setMovieDate(sdf.format(new Date(((Timestamp)obj[2]).getTime())));
            movie.setMoviePhoto(obj[3].toString());
            movie.setMovieInfo(String.valueOf(obj[4].toString().substring(0, 30)) + "...");
            JSONafterMovies.add(movie);
        }
        final Criteria criteria_totalRows = this.createCriteria();
        criteria_totalRows.setProjection((Projection)Projections.countDistinct("movieName"));
        criteria_totalRows.add((Criterion)Restrictions.gt("movieDate", (Object)new Date()));
        final int totalRows = Integer.parseInt(criteria_totalRows.uniqueResult().toString());
        final PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRows(totalRows);
        pageBean.setData(JSONafterMovies);
        return pageBean;
    }
}
