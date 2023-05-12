// 
// 
// 

package com.interceptors;

import java.util.Map;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class MemberSecurityInterceptor extends MethodFilterInterceptor
{
    private String name;
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void init() {
        super.init();
        System.out.println("\u524d\u53f0\u7528\u6237\u5b89\u5168\u62e6\u622a\u5668" + this.name + "\u521d\u59cb\u5316\u4e86\uff01");
    }
    
    public void destroy() {
        super.destroy();
        System.out.println("\u524d\u53f0\u7528\u6237\u5b89\u5168\u62e6\u622a\u5668" + this.name + "\u9500\u6bc1\u4e86\uff01");
    }
    
    protected String doIntercept(final ActionInvocation invocation) throws Exception {
        System.out.println("\u524d\u53f0\u7528\u6237\u5b89\u5168\u62e6\u622a\u5668" + this.name + "\u5de5\u4f5c\u4e86\uff01");
        final Map<String, Object> session = (Map<String, Object>)invocation.getInvocationContext().getSession();
        if (session.get("curMember") != null) {
            return invocation.invoke();
        }
        return "login";
    }
}
