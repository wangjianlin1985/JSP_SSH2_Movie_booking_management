// 
// 
// 

package com.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import com.service.EditionService;
import com.pojo.Edition;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class EditionAction extends ActionSupport implements ModelDriven<Edition>
{
    private Edition edition;
    private EditionService editionService;
    
    public EditionAction() {
        this.edition = new Edition();
    }
    
    public Edition getModel() {
        return this.edition;
    }
    
    public void setEdition(final Edition edition) {
        this.edition = edition;
    }
    
    public void setEditionService(final EditionService editionService) {
        this.editionService = editionService;
    }
    
    public String saveEdition() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        this.edition.setEditionName(request.getParameter("editionName"));
        this.editionService.create(this.edition);
        final Map<String, Object> appliction = (Map<String, Object>)ActionContext.getContext().getApplication();
        appliction.put("lstEdition", this.editionService.findAll());
        return "saveMovie";
    }
}
