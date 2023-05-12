// 
// 
// 

package com.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import com.service.KindService;
import com.pojo.Kind;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class KindAction extends ActionSupport implements ModelDriven<Kind>
{
    private Kind kind;
    private KindService kindService;
    
    public KindAction() {
        this.kind = new Kind();
    }
    
    public Kind getModel() {
        return this.kind;
    }
    
    public void setKind(final Kind kind) {
        this.kind = kind;
    }
    
    public void setKindService(final KindService kindService) {
        this.kindService = kindService;
    }
    
    public String saveKind() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        this.kind.setKindName(request.getParameter("kindName"));
        this.kindService.create(this.kind);
        final Map<String, Object> appliction = (Map<String, Object>)ActionContext.getContext().getApplication();
        appliction.put("lstKind", this.kindService.findAll());
        return "saveMovie";
    }
}
