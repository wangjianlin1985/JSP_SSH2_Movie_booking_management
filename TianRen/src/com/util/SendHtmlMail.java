// 
// 
// 

package com.util;

import org.apache.commons.mail.EmailException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.mail.HtmlEmail;

public class SendHtmlMail
{
    public void sendHtmlMail(final String toEmail, final String HtmlMsg) {
        final HtmlEmail email = new HtmlEmail();
        email.setCharset("UTF-8");
        email.setHostName("smtp.sina.com");
        email.setSmtpPort(25);
        email.setAuthentication("tianrenemail@sina.com", "tianren");
        email.setTLS(true);
        try {
            email.addTo(toEmail);
            email.setFrom("tianrenemail@sina.com");
            email.setSubject("\u5929\u4ec1\u7535\u5f71");
            email.setMsg("\u8ba2\u7968\u4fe1\u606f");
            final URL url = new URL("http://localhost:8888/TianRen/uploadAd/31049534581071.gif");
            final String cid = email.embed(url, "TianRen Logo");
            email.setHtmlMsg("<html><a href='http://localhost:8888/TianRen/index.jsp'><img src=\"cid:" + cid + "\" height=\"75\"></a><br/>" + HtmlMsg + "</html>");
            email.send();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (EmailException e2) {
            e2.printStackTrace();
        }
    }
}
