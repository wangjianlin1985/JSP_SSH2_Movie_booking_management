// 
// 
// 

package com.util;

import java.util.Map;
import com.pojo.Admin;
import com.pojo.Member;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class close extends ActionSupport
{
    public void closeClient() throws Exception {
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Map<String, Object> application = (Map<String, Object>)ActionContext.getContext().getApplication();
        if (session.get("curMember") != null) {
            final List<Member> onlineMemberList =  (List<Member>)application.get("onlineMemberList");
            onlineMemberList.remove(session.get("curMember"));
        }
        if (session.get("curAdmin") != null) {
            final List<Admin> onlineAdminList = (List<Admin>)application.get("onlineAdminList");
            onlineAdminList.remove(session.get("curAdmin"));
        }
        session.clear();
    }
}
