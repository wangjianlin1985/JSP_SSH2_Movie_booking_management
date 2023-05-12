// 
// 
// 

package com.pojo;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class Edition implements Serializable
{
    private Integer editionId;
    private String editionName;
    private Set movies;
    
    public Edition() {
        this.movies = new HashSet(0);
    }
    
    public Edition(final String editionName) {
        this.movies = new HashSet(0);
        this.editionName = editionName;
    }
    
    public Edition(final String editionName, final Set movies) {
        this.movies = new HashSet(0);
        this.editionName = editionName;
        this.movies = movies;
    }
    
    public Integer getEditionId() {
        return this.editionId;
    }
    
    public void setEditionId(final Integer editionId) {
        this.editionId = editionId;
    }
    
    public String getEditionName() {
        return this.editionName;
    }
    
    public void setEditionName(final String editionName) {
        this.editionName = editionName;
    }
    
    public Set getMovies() {
        return this.movies;
    }
    
    public void setMovies(final Set movies) {
        this.movies = movies;
    }
}
