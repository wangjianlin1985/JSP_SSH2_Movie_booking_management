// 
// 
// 

package com.pojo;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class Language implements Serializable
{
    private Integer languageId;
    private String languageName;
    private Set movies;
    
    public Language() {
        this.movies = new HashSet(0);
    }
    
    public Language(final String languageName) {
        this.movies = new HashSet(0);
        this.languageName = languageName;
    }
    
    public Language(final String languageName, final Set movies) {
        this.movies = new HashSet(0);
        this.languageName = languageName;
        this.movies = movies;
    }
    
    public Integer getLanguageId() {
        return this.languageId;
    }
    
    public void setLanguageId(final Integer languageId) {
        this.languageId = languageId;
    }
    
    public String getLanguageName() {
        return this.languageName;
    }
    
    public void setLanguageName(final String languageName) {
        this.languageName = languageName;
    }
    
    public Set getMovies() {
        return this.movies;
    }
    
    public void setMovies(final Set movies) {
        this.movies = movies;
    }
}
