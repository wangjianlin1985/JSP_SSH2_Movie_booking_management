// 
// 
// 

package com.action;

import org.apache.struts2.util.IteratorGenerator;
import java.util.Iterator;
import java.util.Map;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import com.service.AdminService;
import com.pojo.Admin;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport implements ModelDriven<Admin>
{
    private Admin admin;
    private String checkCode;
    private AdminService adminService;
    
    public AdminAction() {
        this.admin = new Admin();
    }
    
    public Admin getModel() {
        return this.admin;
    }
    
    public String getCheckCode() {
        return this.checkCode;
    }
    
    public void setCheckCode(final String checkCode) {
        this.checkCode = checkCode;
    }
    
    public void setAdminService(final AdminService adminService) {
        this.adminService = adminService;
    }
    
    public void login() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String checkCode = request.getParameter("checkCode").toString().trim();
        final String rand = (String)ActionContext.getContext().getSession().get("rand");
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        if (checkCode.equals(rand)) {
            final Admin curAdmin = this.adminService.login(this.admin);
            boolean flagOnline = false;
            if (curAdmin != null) {
                final Map<String, Object> application = (Map<String, Object>)ActionContext.getContext().getApplication();
                final List<Admin> onlineAdminList = (List<Admin>)application.get("onlineAdminList");
                for (final Admin admin : onlineAdminList) {
                    if (admin.getAdminId() == (int)curAdmin.getAdminId()) {
                        flagOnline = true;
                    }
                }
                if (session.get("curAdmin") != null && curAdmin.getAdminId() == (int)((Admin)session.get("curAdmin")).getAdminId()) {
                    flagOnline = false;
                }
                if (!flagOnline) {
                    if (session.get("curAdmin") != null && curAdmin.getAdminId() != (int)((Admin)session.get("curAdmin")).getAdminId()) {
                        onlineAdminList.add(curAdmin);
                    }
                    session.put("curAdmin", curAdmin);
                    final String[] AdminPrivileges = curAdmin.getAdminPrivilege().split(", ");
                    final List<String> lstPrivilege = Arrays.asList(AdminPrivileges);
                    session.put("lstPrivilege", lstPrivilege);
                    out.print(3);
                }
                else {
                    out.print(4);
                }
            }
            else {
                out.print(2);
            }
        }
        else {
            out.print(1);
        }
        out.flush();
        out.close();
    }
    
    public String logout() throws Exception {
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Map<String, Object> application = (Map<String, Object>)ActionContext.getContext().getApplication();
        final List<Admin> onlineAdminList = (List<Admin>)application.get("onlineAdminList");
        onlineAdminList.remove(session.get("curAdmin"));
        session.clear();
        return "login";
    }
    
    public String searchAdmin() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final List<Admin> lstAdmin = this.adminService.findAll();
        request.setAttribute("lstAdmin", (Object)lstAdmin);
        return "showAdmin";
    }
    
    public String saveAdmin() throws Exception {
        this.adminService.create(this.admin);
        return this.searchAdmin();
    }
    
    public void IsExistAdminName() throws Exception {
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        out.print(this.adminService.findByName(this.admin.getAdminName()));
        out.flush();
        out.close();
    }
    
    public String findAdminById() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Admin newAdmin = this.adminService.findById(this.admin);
        final String[] AdminPrivileges = newAdmin.getAdminPrivilege().split(", ");
        final List<String> lstPrivilege = Arrays.asList(AdminPrivileges);
        request.setAttribute("admin", (Object)newAdmin);
        request.setAttribute("lstPrivilege", (Object)lstPrivilege);
        return "updateAdmin";
    }
    
    public String modifyAdmin() throws Exception {
        this.adminService.modifyAdmin(this.admin);
        return this.searchAdmin();
    }
    
    public String removeAdmin() throws Exception {
        this.adminService.remove(this.admin);
        return this.searchAdmin();
    }
    
    public IteratorGenerator.Converter getPrivilegeConverter() {
        return (IteratorGenerator.Converter)new IteratorGenerator.Converter() {
            public Object convert(final String value) throws Exception {
                String result = "";
                switch (Integer.parseInt(value)) {
                    case 1: {
                        result = "\u5f71\u7247\u7ba1\u7406";
                        break;
                    }
                    case 2: {
                        result = "\u573a\u6b21\u7ba1\u7406";
                        break;
                    }
                    case 3: {
                        result = "\u7528\u6237\u7ba1\u7406";
                        break;
                    }
                    case 4: {
                        result = "\u7968\u52a1\u7ba1\u7406";
                        break;
                    }
                    case 5: {
                        result = "\u5e7f\u544a\u7ba1\u7406";
                        break;
                    }
                    case 6: {
                        result = "\u7ba1\u7406\u5458\u7ba1\u7406";
                        break;
                    }
                }
                return result;
            }
        };
    }
}
