// 
// 
// 

package com.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import com.pojo.Admin;
import com.pojo.Member;
import java.util.List;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineMemberListener implements HttpSessionListener
{
    public void sessionCreated(final HttpSessionEvent se) {
    }
    
    public void sessionDestroyed(final HttpSessionEvent se) {
        final HttpSession session = se.getSession();
        final ServletContext application = session.getServletContext();
        if (session.getAttribute("curMember") != null) {
            final List<Member> onlineMemberList = (List<Member>)application.getAttribute("onlineMemberList");
            final Member curMember = (Member)session.getAttribute("curMember");
            onlineMemberList.remove(curMember);
            System.out.println("\u7528\u6237\uff1a" + curMember.getMemberEmail() + "\u8d85\u65f6\u9000\u51fa\u3002");
        }
        if (session.getAttribute("curAdmin") != null) {
            final List<Admin> onlineAdminList = (List<Admin>)application.getAttribute("onlineAdminList");
            final Admin curAdmin = (Admin)session.getAttribute("curAdmin");
            onlineAdminList.remove(curAdmin);
            System.out.println("\u7ba1\u7406\u5458\uff1a" + curAdmin.getAdminName() + "\u8d85\u65f6\u9000\u51fa\u3002");
        }
    }
}
