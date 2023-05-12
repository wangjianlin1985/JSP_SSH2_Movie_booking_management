// 
// 
// 

package com.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import com.service.LanguageService;
import com.pojo.Language;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class LanguageAction extends ActionSupport implements ModelDriven<Language>
{
    private Language language;
    private LanguageService languageService;
    
    public LanguageAction() {
        this.language = new Language();
    }
    
    public Language getModel() {
        return this.language;
    }
    
    public void setLanguage(final Language language) {
        this.language = language;
    }
    
    public void setLanguageService(final LanguageService languageService) {
        this.languageService = languageService;
    }
    
    public String saveLanguage() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        this.language.setLanguageName(request.getParameter("languageName"));
        this.languageService.create(this.language);
        final Map<String, Object> appliction = (Map<String, Object>)ActionContext.getContext().getApplication();
        appliction.put("lstLanguage", this.languageService.findAll());
        return "saveMovie";
    }
}
