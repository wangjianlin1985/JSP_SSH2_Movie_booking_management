// 
// 
// 

package com.service;

import com.dao.base.GenericDaoImpl;
import java.util.List;
import com.pojo.Edition;
import com.pojo.Language;
import com.pojo.Kind;
import com.util.PageBean;
import com.pojo.Movie;
import com.dao.KindDao;
import com.dao.EditionDao;
import com.dao.LanguageDao;
import com.dao.MovieDao;

public class MovieService
{
    private MovieDao movieDao;
    private LanguageDao languageDao;
    private EditionDao editionDao;
    private KindDao kindDao;
    
    public void setMovieDao(final MovieDao movieDao) {
        this.movieDao = movieDao;
    }
    
    public void setLanguageDao(final LanguageDao languageDao) {
        this.languageDao = languageDao;
    }
    
    public void setEditionDao(final EditionDao editionDao) {
        this.editionDao = editionDao;
    }
    
    public void setKindDao(final KindDao kindDao) {
        this.kindDao = kindDao;
    }
    
    public PageBean searchByPage(final Movie movie, final String[] propertyNames, final int currentPage, final int pageSize) {
        return this.movieDao.selectLikeByEntityByPage(movie, propertyNames, currentPage, pageSize);
    }
    
    public void create(final Movie movie, final Integer[] languageIds, final Integer[] editionIds, final Integer kindId) {
        for (final Integer languageId : languageIds) {
            final Kind kind = this.kindDao.selectById(kindId);
            movie.setKind(kind);
            final Language language = this.languageDao.selectById(languageId);
            movie.setLanguage(language);
            for (final Integer editionId : editionIds) {
                final Edition edition = this.editionDao.selectById(editionId);
                movie.setEdition(edition);
                this.movieDao.insert(movie);
            }
        }
    }
    
    public List<Movie> findAll() {
        return this.movieDao.selectAll();
    }
    
    public PageBean findByPage(final int currentPage, final int pageSize) {
        return this.movieDao.selectByPage(currentPage, pageSize);
    }
    
    public PageBean findBeforeByPage(final int currentPage, final int pageSize) {
        return this.movieDao.selectBeforeByPage(currentPage, pageSize);
    }
    
    public PageBean findAfterByPage(final int currentPage, final int pageSize) {
        return this.movieDao.selectAfterByPage(currentPage, pageSize);
    }
    
    public void remove(final Movie movie) {
        this.movieDao.delete(this.movieDao.selectById(movie.getMovieId()));
    }
    
    public Movie findByExample(final Movie movie) {
        final List<Movie> lstMovies =  this.movieDao.selectByExample(movie);
        if (lstMovies.size() == 0) {
            return null;
        }
        return lstMovies.get(0);
    }
    
    public void modify(final Movie movie, final Integer editionId, final Integer kindId, final Integer languageId) {
        final Movie oldMovie = this.movieDao.selectById(movie.getMovieId());
        oldMovie.setEdition(this.editionDao.selectById(editionId));
        oldMovie.setKind(this.kindDao.selectById(kindId));
        oldMovie.setLanguage(this.languageDao.selectById(languageId));
        oldMovie.setMovieActor(movie.getMovieActor());
        oldMovie.setMovieDate(movie.getMovieDate());
        oldMovie.setMovieDirector(movie.getMovieDirector());
        oldMovie.setMovieInfo(movie.getMovieInfo());
        oldMovie.setMovieLong(movie.getMovieLong());
        oldMovie.setMovieName(movie.getMovieName());
        if (movie.getMoviePhoto() != null && movie.getMoviePhoto() != "") {
            oldMovie.setMoviePhoto(movie.getMoviePhoto());
        }
        this.movieDao.update(oldMovie);
    }
    
    public Movie findById(final Movie movie) {
        return this.movieDao.selectById(movie.getMovieId());
    }
    
    public PageBean findBeforeByPageByJson(final int currentPage, final int pageSize) {
        return this.movieDao.selectBeforeByPageByJson(currentPage, pageSize);
    }
    
    public PageBean findAfterByPageByJson(final int currentPage, final int pageSize) {
        return this.movieDao.selectAfterByPageByJson(currentPage, pageSize);
    }
}
