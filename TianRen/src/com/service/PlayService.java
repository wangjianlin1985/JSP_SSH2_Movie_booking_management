// 
// 
// 

package com.service;

import com.pojo.Member;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Collection;
import com.pojo.Ticket;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.sql.Timestamp;
import com.util.PageBean;
import com.pojo.Play;
import com.dao.MovieDao;
import com.dao.PlayDao;

public class PlayService
{
    private PlayDao playDao;
    private MovieDao movieDao;
    
    public PlayService() {
        this.playDao = new PlayDao();
        this.movieDao = new MovieDao();
    }
    
    public void setPlayDao(final PlayDao playDao) {
        this.playDao = playDao;
    }
    
    public void setMovieDao(final MovieDao movieDao) {
        this.movieDao = movieDao;
    }
    
    public void create(final Play play, final Integer movieId) {
        play.setMovie(this.movieDao.selectById(movieId));
        this.playDao.insert(play);
    }
    
    public PageBean findByPage(final int currentPage, final int pageSize) {
        return this.playDao.selectByPage(currentPage, pageSize);
    }
    
    public PageBean findByTimeByPage(final Timestamp beginTime, final Timestamp endTime, final int currentPage, final int pageSize) {
        return this.playDao.selectByTimeByPage(beginTime, endTime, currentPage, pageSize);
    }
    
    public void remove(final Play play) {
        this.playDao.delete(this.playDao.selectById(play.getPlayId()));
    }
    
    public Play find(final Play play) {
        return this.playDao.selectById(play.getPlayId());
    }
    
    public Play findSeat(final Play play) {
        return this.playDao.selectSeatByPlayId(play.getPlayId());
    }
    
    public void modify(final Play play) {
        final Play newPlay = this.playDao.selectById(play.getPlayId());
        newPlay.setPlayPrice(play.getPlayPrice());
        newPlay.setPlayTime(play.getPlayTime());
        this.playDao.update(newPlay);
    }
    
    public List<Map<String, Object>> findByCondition(final String movieName, final String time, final String editionId, final Integer TabbedPanel) {
        final Map<String, List> mapTime = this.playDao.selectByCondition(movieName, time, editionId, TabbedPanel);
        final List<Play> lstPlaies = mapTime.get("lstPlaies");
        final List<Map<String, Object>> lstMapMovies = mapTime.get("lstMapMovies");
        final List<Map<String, Object>> lstMapPlay = new ArrayList<Map<String, Object>>();
        for (final Map<String, Object> mapMovie : lstMapMovies) {
            final Map<String, Object> mapPlay = new HashMap<String, Object>();
            final List<Play> lstOneMoviePlaies = new ArrayList<Play>();
            for (final Play play : lstPlaies) {
                if (mapMovie.get("movieName").equals(play.getMovie().getMovieName())) {
                    final List<Ticket> delList = new ArrayList<Ticket>();
                    for (final Object ticket : play.getTickets()) {
                        if (!((Ticket)ticket).getTicketFlag()) {
                            delList.add((Ticket)ticket);
                        }
                    }
                    play.getTickets().removeAll(delList);
                    lstOneMoviePlaies.add(play);
                }
            }
            mapPlay.put("lstPlaies", lstOneMoviePlaies);
            mapPlay.put("mapMovie", mapMovie);
            lstMapPlay.add(mapPlay);
        }
        return lstMapPlay;
    }
    
    public List<Map<String, Object>> findByConditionByJson(final String movieName, final String time, final String editionId, final Integer TabbedPanel) {
        final Map<String, List> mapTime = this.playDao.selectByCondition(movieName, time, editionId, TabbedPanel);
        final List<Play> lstPlaies = mapTime.get("lstPlaies");
        final List<Map<String, Object>> lstMapMovies = mapTime.get("lstMapMovies");
        final List<Map<String, Object>> lstMapPlay = new ArrayList<Map<String, Object>>();
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (final Map<String, Object> mapMovie : lstMapMovies) {
            final Map<String, Object> mapPlay = new HashMap<String, Object>();
            final List<Map<String, Object>> lstOneMoviePlaies = new ArrayList<Map<String, Object>>();
            for (final Play play : lstPlaies) {
                if (mapMovie.get("movieName").equals(play.getMovie().getMovieName())) {
                    final Map<String, Object> mapMovies = new HashMap<String, Object>();
                    mapMovies.put("playId", play.getPlayId());
                    mapMovies.put("playTime", sdf.format(new Date(play.getPlayTime().getTime())));
                    final List<Ticket> delList = new ArrayList<Ticket>();
                    for (final Object ticket : play.getTickets()) {
                        if (!((Ticket)ticket).getTicketFlag()) {
                            delList.add((Ticket)ticket);
                        }
                    }
                    play.getTickets().removeAll(delList);
                    mapMovies.put("ticketNum", play.getTickets().size());
                    mapMovies.put("language", play.getMovie().getLanguage().getLanguageName());
                    mapMovies.put("edition", play.getMovie().getEdition().getEditionName());
                    mapMovies.put("playPrice", play.getPlayPrice());
                    lstOneMoviePlaies.add(mapMovies);
                }
            }
            mapPlay.put("lstPlaies", lstOneMoviePlaies);
            mapPlay.put("mapMovie", mapMovie);
            lstMapPlay.add(mapPlay);
        }
        return lstMapPlay;
    }
    
    public Double getTicketPrice(final Member curMember, final Play play) {
        final Play curPlay = this.playDao.selectById(play.getPlayId());
        final Double ticketPrice = curPlay.getPlayPrice();
        return ticketPrice;
    }
}
