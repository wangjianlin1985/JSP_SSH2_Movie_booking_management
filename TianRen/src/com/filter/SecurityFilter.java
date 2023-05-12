// 
// 
// 

package com.filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import com.pojo.Admin;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.Filter;

public class SecurityFilter implements Filter
{
    public void destroy() {
        System.out.println("\u5b89\u5168\u8fc7\u6ee4\u5668\u9500\u6bc1\u4e86\uff01");
    }
    
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        System.out.println("\u5b89\u5168\u8fc7\u6ee4\u5668\u5de5\u4f5c\u4e86\uff01");
        final HttpServletRequest httpRequest = (HttpServletRequest)request;
        final HttpServletResponse httpResponse = (HttpServletResponse)response;
        final HttpSession session = httpRequest.getSession();
        final String uri = httpRequest.getRequestURI();
        if (!uri.contains("/admin/")) {
            if ((uri.contains("/memberCenter.jsp") || uri.contains("/myOrder.jsp") || uri.contains("/pay.jsp") || uri.contains("/seat.jsp") || uri.contains("/updateInfo.jsp") || uri.contains("/updatePwd.jsp")) && session.getAttribute("curMember") == null) {
                httpResponse.sendRedirect(String.valueOf(httpRequest.getContextPath()) + "/login.jsp");
            }
        }
        else if (!uri.contains("/adminLogin.jsp")) {
            if (session.getAttribute("curAdmin") == null) {
                httpResponse.sendRedirect(String.valueOf(httpRequest.getContextPath()) + "/admin/adminLogin.jsp");
            }
            else {
                final String privilege = ((Admin)session.getAttribute("curAdmin")).getAdminPrivilege();
                if (uri.contains("Movie") && !privilege.contains("1")) {
                    httpResponse.sendRedirect(String.valueOf(httpRequest.getContextPath()) + "/admin/adminIndex.jsp");
                }
                if (uri.contains("Play") && !privilege.contains("2")) {
                    httpResponse.sendRedirect(String.valueOf(httpRequest.getContextPath()) + "/admin/adminIndex.jsp");
                }
                if (uri.contains("Member") && !privilege.contains("3")) {
                    httpResponse.sendRedirect(String.valueOf(httpRequest.getContextPath()) + "/admin/adminIndex.jsp");
                }
                if (uri.contains("Ticket") && !privilege.contains("4")) {
                    httpResponse.sendRedirect(String.valueOf(httpRequest.getContextPath()) + "/admin/adminIndex.jsp");
                }
                if (uri.contains("Ad") && !privilege.contains("5")) {
                    httpResponse.sendRedirect(String.valueOf(httpRequest.getContextPath()) + "/admin/adminIndex.jsp");
                }
                if (uri.contains("Admin") && !privilege.contains("6")) {
                    httpResponse.sendRedirect(String.valueOf(httpRequest.getContextPath()) + "/admin/adminIndex.jsp");
                }
            }
        }
        chain.doFilter(request, response);
    }
    
    public void init(final FilterConfig filterConfig) throws ServletException {
        System.out.println("\u5b89\u5168\u8fc7\u6ee4\u5668\u521d\u59cb\u5316\u4e86\uff01");
    }
}
