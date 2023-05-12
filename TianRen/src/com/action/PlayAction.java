// 
// 
// 

package com.action;

import com.pojo.Member;
import com.opensymphony.xwork2.ActionContext;
import com.pojo.Movie;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import java.util.List;
import java.util.Date;
import java.util.Map;
import com.util.PageBean;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Calendar;
import org.apache.struts2.ServletActionContext;
import com.service.MovieService;
import com.service.PlayService;
import com.pojo.Play;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class PlayAction extends ActionSupport implements ModelDriven<Play>
{
    private Play play;
    private PlayService playService;
    private MovieService movieService;
    private String movieName;
    private String movieTime;
    private String movieEdition;
    private String movieDate;
    
    public PlayAction() {
        this.play = new Play();
        this.playService = new PlayService();
        this.movieService = new MovieService();
        this.movieName = new String();
        this.movieTime = new String();
        this.movieEdition = new String();
        this.movieDate = new String();
    }
    
    public Play getModel() {
        return this.play;
    }
    
    public void setPlayService(final PlayService playService) {
        this.playService = playService;
    }
    
    public void setMovieService(final MovieService movieService) {
        this.movieService = movieService;
    }
    
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }
    
    public void setMovieTime(final String movieTime) {
        this.movieTime = movieTime;
    }
    
    public void setMovieEdition(final String movieEdition) {
        this.movieEdition = movieEdition;
    }
    
    public void setMovieDate(final String movieDate) {
        this.movieDate = movieDate;
    }
    
    public String savePlay() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Integer movieId = Integer.parseInt(request.getParameter("movieId").toString().trim());
        final String playDay = request.getParameter("playDay").toString().trim();
        final Integer playHour = Integer.parseInt(request.getParameter("playHour").toString().trim());
        final Integer playMinute = Integer.parseInt(request.getParameter("playMinute").toString().trim());
        final String[] yearMonthDate = playDay.split("-");
        final int year = Integer.parseInt(yearMonthDate[0].trim());
        final int month = Integer.parseInt(yearMonthDate[1].trim());
        final int date = Integer.parseInt(yearMonthDate[2].trim().substring(0, 2));
        final Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, date, playHour, playMinute, 0);
        this.play.setPlayTime(new Timestamp(cd.getTimeInMillis()));
        this.playService.create(this.play, movieId);
        return this.findPlaiesByTimeByPage();
    }
    
    public String findPlaiesByPage() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage").toString());
        }
        int pageSize = 8;
        if (request.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize").toString());
        }
        final PageBean plaiesByPage = this.playService.findByPage(currentPage, pageSize);
        final Map<String, Object> session = (Map<String, Object>)ServletActionContext.getContext().getSession();
        session.put("plaiesByPage", plaiesByPage);
        return "showPlaies";
    }
    
    public String findPlaiesByTimeByPage() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        Timestamp beginTime = null;
        if (request.getParameter("beginDay") != null) {
            final String beginDay = request.getParameter("beginDay").toString().trim();
            if (beginDay != "") {
                final String[] beginYearMonthDate = beginDay.split("-");
                final int beginYear = Integer.parseInt(beginYearMonthDate[0].trim());
                final int beginMonth = Integer.parseInt(beginYearMonthDate[1].trim());
                final int beginDate = Integer.parseInt(beginYearMonthDate[2].trim().substring(0, 2));
                final Calendar beginCd = Calendar.getInstance();
                beginCd.set(beginYear, beginMonth - 1, beginDate, 0, 0, 0);
                beginTime = new Timestamp(beginCd.getTimeInMillis());
            }
        }
        Timestamp endTime = null;
        if (request.getParameter("endDay") != null) {
            final String endDay = request.getParameter("endDay").toString().trim();
            if (endDay != "") {
                final String[] endYearMonthDate = endDay.split("-");
                final int endYear = Integer.parseInt(endYearMonthDate[0].trim());
                final int endMonth = Integer.parseInt(endYearMonthDate[1].trim());
                final int endDate = Integer.parseInt(endYearMonthDate[2].trim().substring(0, 2));
                final Calendar endCd = Calendar.getInstance();
                endCd.set(endYear, endMonth - 1, endDate, 23, 59, 59);
                endTime = new Timestamp(endCd.getTimeInMillis());
            }
        }
        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage").toString());
        }
        int pageSize = 8;
        if (request.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize").toString());
        }
        System.out.println("beginTime:" + beginTime + ";endTime:" + endTime + ";pageSize:" + pageSize + ";currentPage:" + currentPage);
        final PageBean plaiesByTimeByPage = this.playService.findByTimeByPage(beginTime, endTime, currentPage, pageSize);
        request.setAttribute("plaiesByTimeByPage", (Object)plaiesByTimeByPage);
        request.setAttribute("beginTime", (Object)beginTime);
        request.setAttribute("endTime", (Object)endTime);
        return "showPlaies";
    }
    
    public String romevePlay() throws Exception {
        this.playService.remove(this.play);
        return this.findPlaiesByTimeByPage();
    }
    
    public String findPlay() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Play updatePlay = this.playService.find(this.play);
        final Calendar cd = Calendar.getInstance();
        cd.setTime(updatePlay.getPlayTime());
        request.setAttribute("hour", (Object)cd.get(11));
        request.setAttribute("minute", (Object)cd.get(12));
        request.setAttribute("play", (Object)updatePlay);
        return "updatePlay";
    }
    
    public String modifyPlay() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String playDay = request.getParameter("playDay").toString().trim();
        final Integer playHour = Integer.parseInt(request.getParameter("playHour").toString().trim());
        final Integer playMinute = Integer.parseInt(request.getParameter("playMinute").toString().trim());
        final String[] yearMonthDate = playDay.split("-");
        final int year = Integer.parseInt(yearMonthDate[0].trim());
        final int month = Integer.parseInt(yearMonthDate[1].trim());
        final int date = Integer.parseInt(yearMonthDate[2].trim().substring(0, 2));
        final Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, date, playHour, playMinute, 0);
        this.play.setPlayTime(new Timestamp(cd.getTimeInMillis()));
        this.playService.modify(this.play);
        return this.findPlaiesByTimeByPage();
    }
    
    public String showPlayTable() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        if (this.movieName == null) {
            this.movieName = "";
        }
        final List<Map<String, Object>> todayLstMapTime = this.playService.findByCondition(this.movieName, "", "", 1);
        final List<Map<String, Object>> tomorrowLstMapTime = this.playService.findByCondition(this.movieName, "", "", 2);
        request.setAttribute("todayLstMapTime", (Object)todayLstMapTime);
        request.setAttribute("tomorrowLstMapTime", (Object)tomorrowLstMapTime);
        return "time";
    }
    
    public void showPlayTableByCondition() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final HttpServletResponse response = ServletActionContext.getResponse();
        final String name = new String(request.getParameter("name").trim().getBytes("ISO8859-1"), "UTF-8");
        final String time = request.getParameter("time");
        final String edition = request.getParameter("edition");
        final int TabbedPanels = Integer.parseInt(request.getParameter("TabbedPanels").toString().trim());
        response.setContentType("text/html;charset=utf-8");
        final PrintWriter out = response.getWriter();
        final List<Map<String, Object>> lstMapTime = this.playService.findByConditionByJson(name, time, edition, TabbedPanels);
        final JSONArray array = JSONArray.fromObject((Object)lstMapTime);
        out.print(array.toString());
        out.flush();
        out.close();
    }
    
    public String showOneMoviePlaies() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Movie movie = new Movie();
        movie.setMovieName(this.movieName);
        request.setAttribute("movie", (Object)this.movieService.findByExample(movie));
        String movieTime = "";
        if (request.getParameter("movieTime") != null) {
            movieTime = request.getParameter("movieTime");
        }
        String editionId = "";
        if (request.getParameter("editionId") != null) {
            editionId = request.getParameter("editionId");
        }
        int movieDate = 1;
        if (request.getParameter("movieDate") != null) {
            movieDate = Integer.valueOf(request.getParameter("movieDate"));
        }
        final List<Map<String, Object>> lstMapOneMovieTime = this.playService.findByCondition(this.movieName, movieTime, editionId, movieDate);
        request.setAttribute("lstMapOneMovieTime", (Object)lstMapOneMovieTime);
        return "oneMovie";
    }
    
    public void showOneMovieByConditionByJson() throws Exception {
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        final PrintWriter out = response.getWriter();
        final List<Map<String, Object>> lstMapOneMovieTime = this.playService.findByConditionByJson(this.movieName, this.movieTime, this.movieEdition, Integer.valueOf(this.movieDate.trim()));
        final JSONArray array = JSONArray.fromObject((Object)lstMapOneMovieTime);
        out.print(array.toString());
        out.flush();
        out.close();
    }
    
    public String showSeat() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        System.out.println("-------------" + this.play);
        request.setAttribute("play", (Object)this.playService.findSeat(this.play));
        return "seat";
    }
    
    public String showPay() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        this.play.setPlayId(Integer.valueOf(request.getParameter("playId")));
        request.setAttribute("play", (Object)this.playService.find(this.play));
        final int[] chooseSeatsNum = new int[request.getParameter("chooseSeatsNum").split(",").length];
        for (int i = 0; i < request.getParameter("chooseSeatsNum").split(",").length; ++i) {
            chooseSeatsNum[i] = Integer.valueOf(request.getParameter("chooseSeatsNum").split(",")[i]);
        }
        request.setAttribute("chooseSeatsNum", (Object)chooseSeatsNum);
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Member curMember = (Member)session.get("curMember");
        final Double ticketPrice = this.playService.getTicketPrice(curMember, this.play);
        request.setAttribute("ticketPrice", (Object)ticketPrice);
        return "pay";
    }
}
