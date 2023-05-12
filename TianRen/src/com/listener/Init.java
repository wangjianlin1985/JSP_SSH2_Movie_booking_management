// 
// 
// 

package com.listener;

import com.pojo.Ad;
import com.pojo.Movie;
import com.pojo.Kind;
import com.pojo.Edition;
import com.pojo.Language;
import java.util.List;
import com.pojo.Admin;
import com.pojo.Member;
import java.util.ArrayList;
import javax.servlet.ServletContextEvent;
import com.service.AdService;
import com.service.MovieService;
import com.service.KindService;
import com.service.EditionService;
import com.service.LanguageService;
import javax.servlet.ServletContextListener;

public class Init implements ServletContextListener
{
    private static LanguageService languageService;
    private static EditionService editionService;
    private static KindService kindService;
    private static MovieService movieService;
    private static AdService adService;
    
    public void setLanguageService(final LanguageService languageService) {
        Init.languageService = languageService;
        System.out.println("Init\u4e2d\u7684languageService\u6ce8\u5165\u6210\u529f");
    }
    
    public void setEditionService(final EditionService editionService) {
        Init.editionService = editionService;
        System.out.println("Init\u4e2d\u7684editionService\u6ce8\u5165\u6210\u529f");
    }
    
    public void setKindService(final KindService kindService) {
        Init.kindService = kindService;
        System.out.println("Init\u4e2d\u7684kindService\u6ce8\u5165\u6210\u529f");
    }
    
    public void setMovieService(final MovieService movieService) {
        Init.movieService = movieService;
        System.out.println("Init\u4e2d\u7684movieService\u6ce8\u5165\u6210\u529f");
    }
    
    public void setAdService(final AdService adService) {
        Init.adService = adService;
        System.out.println("Init\u4e2d\u7684adService\u6ce8\u5165\u6210\u529f");
    }
    
    public void contextDestroyed(final ServletContextEvent sce) {
    }
    
    public void contextInitialized(final ServletContextEvent sce) {
        System.out.println("\u76d1\u542c\u5668\u5f00\u59cb\u5de5\u4f5c\u4e86\uff01");
        final List<Language> lstLanguage = Init.languageService.findAll();
        sce.getServletContext().setAttribute("lstLanguage", (Object)lstLanguage);
        System.out.println("Language\u521d\u59cb\u5316\u5b8c\u6210\uff01");
        final List<Edition> lstEdition = Init.editionService.findAll();
        sce.getServletContext().setAttribute("lstEdition", (Object)lstEdition);
        System.out.println("Edition\u521d\u59cb\u5316\u5b8c\u6210\uff01");
        final List<Kind> lstKind = Init.kindService.findAll();
        sce.getServletContext().setAttribute("lstKind", (Object)lstKind);
        System.out.println("Kind\u521d\u59cb\u5316\u5b8c\u6210\uff01");
        final List<Movie> lstMovie = Init.movieService.findAll();
        sce.getServletContext().setAttribute("lstMovie", (Object)lstMovie);
        System.out.println("Movie\u521d\u59cb\u5316\u5b8c\u6210\uff01");
        final List<Ad> lstAd = Init.adService.findAll();
        sce.getServletContext().setAttribute("lstAd", (Object)lstAd);
        System.out.println("Ad\u521d\u59cb\u5316\u5b8c\u6210\uff01");
        final List<Member> onlineMemberList = new ArrayList<Member>();
        sce.getServletContext().setAttribute("onlineMemberList", (Object)onlineMemberList);
        System.out.println("\u7528\u6237\u5728\u7ebf\u5217\u8868\u521d\u59cb\u5316\u5b8c\u6210\uff01");
        final List<Admin> onlineAdminList = new ArrayList<Admin>();
        sce.getServletContext().setAttribute("onlineAdminList", (Object)onlineAdminList);
        System.out.println("\u7ba1\u7406\u5458\u5728\u7ebf\u5217\u8868\u521d\u59cb\u5316\u5b8c\u6210\uff01");
    }
}
