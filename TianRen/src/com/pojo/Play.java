// 
// 
// 

package com.pojo;

import java.util.HashSet;
import java.util.Set;
import java.sql.Timestamp;
import java.io.Serializable;

public class Play implements Serializable
{
    private Integer playId;
    private Movie movie;
    private Timestamp playTime;
    private Double playPrice;
    private Set tickets;
    
    public Play() {
        this.tickets = new HashSet(0);
    }
    
    public Play(final Movie movie, final Double playPrice) {
        this.tickets = new HashSet(0);
        this.movie = movie;
        this.playPrice = playPrice;
    }
    
    public Play(final Movie movie, final Timestamp playTime, final Double playPrice, final Set tickets) {
        this.tickets = new HashSet(0);
        this.movie = movie;
        this.playTime = playTime;
        this.playPrice = playPrice;
        this.tickets = tickets;
    }
    
    public Integer getPlayId() {
        return this.playId;
    }
    
    public void setPlayId(final Integer playId) {
        this.playId = playId;
    }
    
    public Movie getMovie() {
        return this.movie;
    }
    
    public void setMovie(final Movie movie) {
        this.movie = movie;
    }
    
    public Timestamp getPlayTime() {
        return this.playTime;
    }
    
    public void setPlayTime(final Timestamp playTime) {
        this.playTime = playTime;
    }
    
    public Double getPlayPrice() {
        return this.playPrice;
    }
    
    public void setPlayPrice(final Double playPrice) {
        this.playPrice = playPrice;
    }
    
    public Set getTickets() {
        return this.tickets;
    }
    
    public void setTickets(final Set tickets) {
        this.tickets = tickets;
    }
}
