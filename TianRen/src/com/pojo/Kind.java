// 
// 
// 

package com.pojo;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class Kind implements Serializable
{
    private Integer kindId;
    private String kindName;
    private Set movies;
    
    public Kind() {
        this.movies = new HashSet(0);
    }
    
    public Kind(final String kindName) {
        this.movies = new HashSet(0);
        this.kindName = kindName;
    }
    
    public Kind(final String kindName, final Set movies) {
        this.movies = new HashSet(0);
        this.kindName = kindName;
        this.movies = movies;
    }
    
    public Integer getKindId() {
        return this.kindId;
    }
    
    public void setKindId(final Integer kindId) {
        this.kindId = kindId;
    }
    
    public String getKindName() {
        return this.kindName;
    }
    
    public void setKindName(final String kindName) {
        this.kindName = kindName;
    }
    
    public Set getMovies() {
        return this.movies;
    }
    
    public void setMovies(final Set movies) {
        this.movies = movies;
    }
}
