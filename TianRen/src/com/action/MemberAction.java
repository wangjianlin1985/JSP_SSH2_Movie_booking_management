// 
// 
// 

package com.action;

import com.util.FileProcessUitl;
import com.util.PageBean;
import java.util.Iterator;
import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionContext;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import java.io.File;
import com.service.MemberService;
import com.pojo.Member;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class MemberAction extends ActionSupport implements ModelDriven<Member>
{
    private Member member;
    private String checkCode;
    private MemberService memberService;
    private File[] upload;
    private String[] uploadContentType;
    private String[] uploadFileName;
    
    public MemberAction() {
        this.member = new Member();
    }
    
    public Member getModel() {
        return this.member;
    }
    
    public String getCheckCode() {
        return this.checkCode;
    }
    
    public void setCheckCode(final String checkCode) {
        this.checkCode = checkCode;
    }
    
    public void setMemberService(final MemberService memberService) {
        this.memberService = memberService;
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
    
    public void IsExistMemberEmail() throws Exception {
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        out.print(this.memberService.isExistEmail(this.member));
        out.flush();
        out.close();
    }
    
    public String register() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String checkCode = request.getParameter("checkCode").toString().trim();
        final String rand = (String)ActionContext.getContext().getSession().get("rand");
        if (checkCode.equals(rand)) {
            this.memberService.create(this.member);
            final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
            session.put("curMember", this.memberService.login(this.member));
            return "index";
        }
        return null;
    }
    
    public void checkCode() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String checkCode = request.getParameter("checkCode").toString().trim();
        final String rand = (String)ActionContext.getContext().getSession().get("rand");
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        if (checkCode.equals(rand)) {
            out.print(true);
        }
        else {
            out.print(false);
        }
        out.flush();
        out.close();
    }
    
    public void login() throws Exception {
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Boolean auto = Boolean.parseBoolean(request.getParameter("auto"));
        final Member curMember = this.memberService.login(this.member);
        boolean flagOnline = false;
        if (curMember != null) {
            final Map<String, Object> application = (Map<String, Object>)ActionContext.getContext().getApplication();
            final List<Member> onlineMemberList = (List<Member>)application.get("onlineMemberList");
            for (final Member member : onlineMemberList) {
                if (member.getMemberId() == (int)curMember.getMemberId()) {
                    flagOnline = true;
                }
            }
            if (session.get("curMember") != null && curMember.getMemberId() == (int)((Member)session.get("curMember")).getMemberId()) {
                flagOnline = false;
            }
            if (!flagOnline) {
                if (session.get("curMember") != null && curMember.getMemberId() != (int)((Member)session.get("curMember")).getMemberId()) {
                    onlineMemberList.add(curMember);
                }
                session.put("curMember", curMember);
                if (auto) {
                    final Cookie cookie = new Cookie("TianRenInfo", String.valueOf(this.member.getMemberEmail()) + "," + this.member.getMemberPwd());
                    cookie.setMaxAge(2592000);
                    response.addCookie(cookie);
                }
                else {
                    final Cookie[] cookies = request.getCookies();
                    Cookie[] array;
                    for (int length = (array = cookies).length, i = 0; i < length; ++i) {
                        final Cookie cookie2 = array[i];
                        if ("TianRenInfo".equals(cookie2.getName())) {
                            cookie2.setMaxAge(0);
                            response.addCookie(cookie2);
                            break;
                        }
                    }
                }
                out.print(true);
            }
            else {
                out.print("online");
            }
        }
        else {
            out.print(false);
        }
        out.flush();
        out.close();
    }
    
    public String logout() throws Exception {
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Map<String, Object> application = (Map<String, Object>)ActionContext.getContext().getApplication();
        final List<Member> onlineMemberList = (List<Member>)application.get("onlineMemberList");
        onlineMemberList.remove(session.get("curMember"));
        session.clear();
        return "index";
    }
    
    public String searchMembersByPage() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        if (request.getParameter("searchWord") != null) {
            this.member.setMemberName(new String(request.getParameter("searchWord").toString().trim().getBytes("ISO8859-1"), "UTF-8"));
        }
        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage").toString());
        }
        int pageSize = 8;
        if (request.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize").toString());
        }
        final PageBean searchMembersByPage = this.memberService.findLikeByEntityByPage(this.member, new String[] { "memberName" }, currentPage, pageSize);
        request.setAttribute("searchMembersByPage", (Object)searchMembersByPage);
        request.setAttribute("searchWord", (Object)this.member.getMemberName());
        return "showMembers";
    }
    
    public String modifyMemberInfo() throws Exception {
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Member curMember = (Member)session.get("curMember");
        session.put("curMember", this.memberService.modifyInfo(curMember, this.member));
        return "updateInfo";
    }
    
    public String modifyMemberPhoto() throws Exception {
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Member curMember = (Member)session.get("curMember");
        final FileProcessUitl util = new FileProcessUitl();
        final String path = util.processFileUpload("/uploadMember", this.upload, this.uploadFileName);
        this.member.setMemberPhoto(path);
        session.put("curMember", this.memberService.modifyPhoto(curMember, this.member));
        return "updateInfo";
    }
    
    public String modifyMemberPwd() throws Exception {
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Member curMember = (Member)session.get("curMember");
        session.put("curMember", this.memberService.modifyPwd(curMember, this.member));
        return "updatePwd";
    }
    
    public void checkOldMemberPwd() throws Exception {
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        out.print(this.memberService.checkOldPwd((Member)session.get("curMember"), this.member));
        out.flush();
        out.close();
    }
    
    public String saveMember() throws Exception {
        this.memberService.create(this.member);
        return this.searchMembersByPage();
    }
    
    public String removeMember() throws Exception {
        this.memberService.remove(this.member);
        return this.searchMembersByPage();
    }
    
    public String findMemberById() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("member", (Object)this.memberService.findById(this.member));
        return "updateMember";
    }
    
    public String modifyMember() throws Exception {
        this.memberService.modify(this.member);
        return this.searchMembersByPage();
    }
    
    public String prepareLogin() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Cookie[] cookies = request.getCookies();
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        String memberEmail = null;
        String memberPwd = null;
        Cookie[] array;
        for (int length = (array = cookies).length, i = 0; i < length; ++i) {
            final Cookie cookie = array[i];
            if ("TianRenInfo".equals(cookie.getName())) {
                final String[] studentInfo = cookie.getValue().split(",");
                memberEmail = studentInfo[0];
                memberPwd = studentInfo[1];
                break;
            }
        }
        if (memberEmail != null && memberPwd != null) {
            this.member.setMemberEmail(memberEmail);
            this.member.setMemberPwd(memberPwd);
            final Member curMember = this.memberService.login(this.member);
            session.put("curMember", curMember);
        }
        session.put("cookieFlag", true);
        return "index";
    }
}
