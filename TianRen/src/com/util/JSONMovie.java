// 
// 
// 

package com.util;

public class JSONMovie
{
    private String moviePhoto;
    private String movieName;
    private String movieDate;
    private String movieInfo;
    private String movieActor;
    
    public JSONMovie() {
    }
    
    public JSONMovie(final String moviePhoto, final String movieName, final String movieDate, final String movieInfo, final String movieActor) {
        this.moviePhoto = moviePhoto;
        this.movieName = movieName;
        this.movieDate = movieDate;
        this.movieInfo = movieInfo;
        this.movieActor = movieActor;
    }
    
    public String getMoviePhoto() {
        return this.moviePhoto;
    }
    
    public void setMoviePhoto(final String moviePhoto) {
        this.moviePhoto = moviePhoto;
    }
    
    public String getMovieName() {
        return this.movieName;
    }
    
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }
    
    public String getMovieDate() {
        return this.movieDate;
    }
    
    public void setMovieDate(final String movieDate) {
        this.movieDate = movieDate;
    }
    
    public String getMovieInfo() {
        return this.movieInfo;
    }
    
    public void setMovieInfo(final String movieInfo) {
        this.movieInfo = movieInfo;
    }
    
    public String getMovieActor() {
        return this.movieActor;
    }
    
    public void setMovieActor(final String movieActor) {
        this.movieActor = movieActor;
    }
}
