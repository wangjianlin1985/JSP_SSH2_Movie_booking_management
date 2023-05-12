// 
// 
// 

package com.pojo;

import java.util.HashSet;
import java.util.Set;
import java.sql.Timestamp;
import java.io.Serializable;

public class Movie implements Serializable
{
    private Integer movieId;
    private Language language;
    private Edition edition;
    private Kind kind;
    private String movieName;
    private String movieDirector;
    private String movieActor;
    private String movieInfo;
    private String moviePhoto;
    private Integer movieLong;
    private Timestamp movieDate;
    private Set plaies;
    
    public Movie() {
        this.plaies = new HashSet(0);
    }
    
    public Movie(final Language language, final Edition edition, final Kind kind, final String movieName, final String movieDirector, final String movieActor, final String movieInfo, final String moviePhoto, final Integer movieLong) {
        this.plaies = new HashSet(0);
        this.language = language;
        this.edition = edition;
        this.kind = kind;
        this.movieName = movieName;
        this.movieDirector = movieDirector;
        this.movieActor = movieActor;
        this.movieInfo = movieInfo;
        this.moviePhoto = moviePhoto;
        this.movieLong = movieLong;
    }
    
    public Movie(final Language language, final Edition edition, final Kind kind, final String movieName, final String movieDirector, final String movieActor, final String movieInfo, final String moviePhoto, final Integer movieLong, final Timestamp movieDate, final Set plaies) {
        this.plaies = new HashSet(0);
        this.language = language;
        this.edition = edition;
        this.kind = kind;
        this.movieName = movieName;
        this.movieDirector = movieDirector;
        this.movieActor = movieActor;
        this.movieInfo = movieInfo;
        this.moviePhoto = moviePhoto;
        this.movieLong = movieLong;
        this.movieDate = movieDate;
        this.plaies = plaies;
    }
    
    public Integer getMovieId() {
        return this.movieId;
    }
    
    public void setMovieId(final Integer movieId) {
        this.movieId = movieId;
    }
    
    public Language getLanguage() {
        return this.language;
    }
    
    public void setLanguage(final Language language) {
        this.language = language;
    }
    
    public Edition getEdition() {
        return this.edition;
    }
    
    public void setEdition(final Edition edition) {
        this.edition = edition;
    }
    
    public Kind getKind() {
        return this.kind;
    }
    
    public void setKind(final Kind kind) {
        this.kind = kind;
    }
    
    public String getMovieName() {
        return this.movieName;
    }
    
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }
    
    public String getMovieDirector() {
        return this.movieDirector;
    }
    
    public void setMovieDirector(final String movieDirector) {
        this.movieDirector = movieDirector;
    }
    
    public String getMovieActor() {
        return this.movieActor;
    }
    
    public void setMovieActor(final String movieActor) {
        this.movieActor = movieActor;
    }
    
    public String getMovieInfo() {
        return this.movieInfo;
    }
    
    public void setMovieInfo(final String movieInfo) {
        this.movieInfo = movieInfo;
    }
    
    public String getMoviePhoto() {
        return this.moviePhoto;
    }
    
    public void setMoviePhoto(final String moviePhoto) {
        this.moviePhoto = moviePhoto;
    }
    
    public Integer getMovieLong() {
        return this.movieLong;
    }
    
    public void setMovieLong(final Integer movieLong) {
        this.movieLong = movieLong;
    }
    
    public Timestamp getMovieDate() {
        return this.movieDate;
    }
    
    public void setMovieDate(final Timestamp movieDate) {
        this.movieDate = movieDate;
    }
    
    public Set getPlaies() {
        return this.plaies;
    }
    
    public void setPlaies(final Set plaies) {
        this.plaies = plaies;
    }
}
