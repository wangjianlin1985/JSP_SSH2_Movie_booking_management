// 
// 
// 

package com.action;

import net.sf.json.JSONArray;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import com.pojo.Play;
import java.util.ArrayList;
import com.util.PageBean;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import java.util.Map;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.util.FileProcessUitl;
import com.service.MovieService;
import java.io.File;
import com.pojo.Movie;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class MovieAction extends ActionSupport implements ModelDriven<Movie>
{
    private Movie movie;
    private File[] upload;
    private String[] uploadContentType;
    private String[] uploadFileName;
    private MovieService movieService;
    private Integer[] languageIds;
    private Integer[] editionIds;
    private Integer kindId;
    private Integer editionId;
    private Integer languageId;
    
    public MovieAction() {
        this.movie = new Movie();
    }
    
    public Movie getModel() {
        return this.movie;
    }
    
    public void setMovie(final Movie movie) {
        this.movie = movie;
    }
    
    public File[] getUpload() {
        return this.upload;
    }
    
    public void setUpload(final File[] upload) {
        this.upload = upload;
    }
    
    public String[] getUploadContentType() {
        return this.uploadContentType;
    }
    
    public void setUploadContentType(final String[] uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    
    public String[] getUploadFileName() {
        return this.uploadFileName;
    }
    
    public void setUploadFileName(final String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    
    public void setMovieService(final MovieService movieService) {
        this.movieService = movieService;
    }
    
    public Integer[] getLanguageIds() {
        return this.languageIds;
    }
    
    public void setLanguageIds(final Integer[] languageIds) {
        this.languageIds = languageIds;
    }
    
    public Integer[] getEditionIds() {
        return this.editionIds;
    }
    
    public void setEditionIds(final Integer[] editionIds) {
        this.editionIds = editionIds;
    }
    
    public Integer getKindId() {
        return this.kindId;
    }
    
    public void setKindId(final Integer kindId) {
        this.kindId = kindId;
    }
    
    public Integer getEditionId() {
        return this.editionId;
    }
    
    public void setEditionId(final Integer editionId) {
        this.editionId = editionId;
    }
    
    public Integer getLanguageId() {
        return this.languageId;
    }
    
    public void setLanguageId(final Integer languageId) {
        this.languageId = languageId;
    }
    
    public String saveMovie() throws Exception {
        final FileProcessUitl util = new FileProcessUitl();
        final String path = util.processFileUpload("/uploadMovie", this.upload, this.uploadFileName);
        this.movie.setMoviePhoto(path);
        this.movieService.create(this.movie, this.languageIds, this.editionIds, this.kindId);
        final List<Movie> lstMovie = this.movieService.findAll();
        final Map<String, Object> application = (Map<String, Object>)ActionContext.getContext().getApplication();
        application.put("lstMovie", lstMovie);
        return this.searchMoviesByPage();
    }
    
    public String showBeforeAndAfterMovieByPageOnIndex() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final int beforeCurrentPage = 1;
        final int beforePageSize = 8;
        final PageBean beforeMovieByPage = this.movieService.findBeforeByPage(beforeCurrentPage, beforePageSize);
        request.setAttribute("beforeMovieByPage", (Object)beforeMovieByPage);
        final int afterCurrentPage = 1;
        final int afterPageSize = 8;
        final PageBean afterMovieByPage = this.movieService.findAfterByPage(afterCurrentPage, afterPageSize);
        request.setAttribute("afterMovieByPage", (Object)afterMovieByPage);
        return "index";
    }
    
    public String showBeforeAndAfterMovieByPageOnMovies() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int beforeCurrentPage = 1;
        if (request.getParameter("beforeCurrentPage") != null) {
            beforeCurrentPage = Integer.parseInt(request.getParameter("beforeCurrentPage").toString());
        }
        int beforePageSize = 8;
        if (request.getParameter("beforePageSize") != null) {
            beforePageSize = Integer.parseInt(request.getParameter("beforePageSize").toString());
        }
        final PageBean beforeMovieByPage = this.movieService.findBeforeByPage(beforeCurrentPage, beforePageSize);
        request.setAttribute("beforeMovieByPage", (Object)beforeMovieByPage);
        int afterCurrentPage = 1;
        if (request.getParameter("afterCurrentPage") != null) {
            afterCurrentPage = Integer.parseInt(request.getParameter("afterCurrentPage").toString());
        }
        int afterPageSize = 8;
        if (request.getParameter("afterPageSize") != null) {
            afterPageSize = Integer.parseInt(request.getParameter("afterPageSize").toString());
        }
        final PageBean afterMovieByPage = this.movieService.findAfterByPage(afterCurrentPage, afterPageSize);
        request.setAttribute("afterMovieByPage", (Object)afterMovieByPage);
        return "movies";
    }
    
    public String searchMoviesByPage() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        if (request.getParameter("searchWord") != null) {
            this.movie.setMovieName(new String(request.getParameter("searchWord").toString().trim().getBytes("ISO8859-1"), "UTF-8"));
        }
        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage").toString());
        }
        int pageSize = 8;
        if (request.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize").toString());
        }
        final PageBean searchMoviesByPage = this.movieService.searchByPage(this.movie, new String[] { "movieName" }, currentPage, pageSize);
        request.setAttribute("searchMoviesByPage", (Object)searchMoviesByPage);
        request.setAttribute("searchWord", (Object)this.movie.getMovieName());
        return "showMovies";
    }
    
    public String removeMovie() throws Exception {
        this.movieService.remove(this.movie);
        final List<Movie> lstMovie = this.movieService.findAll();
        final Map<String, Object> application = (Map<String, Object>)ActionContext.getContext().getApplication();
        application.put("lstMovie", lstMovie);
        return this.searchMoviesByPage();
    }
    
    public String showMovie() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Movie oneMovie = this.movieService.findById(this.movie);
        final List<Play> lstPlaies = new ArrayList<Play>();
        for (final Object play : oneMovie.getPlaies()) {
            lstPlaies.add((Play)play);
        }
        Collections.sort(lstPlaies, new Comparator<Play>() {
            @Override
            public int compare(final Play play1, final Play play2) {
                return play2.getPlayTime().compareTo(play1.getPlayTime());
            }
        });
        request.setAttribute("lstPlaies", (Object)lstPlaies);
        request.setAttribute("movie", (Object)oneMovie);
        return "updateMovie";
    }
    
    public String modifyMovie() throws Exception {
        final FileProcessUitl util = new FileProcessUitl();
        final String path = util.processFileUpload("/uploadMovie", this.upload, this.uploadFileName);
        this.movie.setMoviePhoto(path);
        this.movieService.modify(this.movie, this.editionId, this.kindId, this.languageId);
        return this.searchMoviesByPage();
    }
    
    public void findMovieById() throws Exception {
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        out.print(this.movieService.findById(this.movie).getMovieLong());
    }
    
    public void findBeforeByPageByJson() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        final PrintWriter out = response.getWriter();
        int beforeCurrentPage = 1;
        if (request.getParameter("beforeCurrentPage") != null) {
            beforeCurrentPage = Integer.parseInt(request.getParameter("beforeCurrentPage").toString());
        }
        int beforePageSize = 8;
        if (request.getParameter("beforePageSize") != null) {
            beforePageSize = Integer.parseInt(request.getParameter("beforePageSize").toString());
        }
        final PageBean beforeMovieByPageByJson = this.movieService.findBeforeByPageByJson(beforeCurrentPage, beforePageSize);
        final JSONArray array = JSONArray.fromObject((Object)beforeMovieByPageByJson);
        out.print(array.toString());
        out.flush();
        out.close();
    }
    
    public void findAfterByPageByJson() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        final PrintWriter out = response.getWriter();
        int afterCurrentPage = 1;
        if (request.getParameter("afterCurrentPage") != null) {
            afterCurrentPage = Integer.parseInt(request.getParameter("afterCurrentPage").toString());
        }
        int afterPageSize = 8;
        if (request.getParameter("afterPageSize") != null) {
            afterPageSize = Integer.parseInt(request.getParameter("afterPageSize").toString());
        }
        final PageBean afterMovieByPageByJson = this.movieService.findAfterByPageByJson(afterCurrentPage, afterPageSize);
        final JSONArray array = JSONArray.fromObject((Object)afterMovieByPageByJson);
        out.print(array.toString());
        out.flush();
        out.close();
    }
}
