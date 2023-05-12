// 
// 
// 

package com.pojo;

import java.io.Serializable;

public class Ad implements Serializable
{
    private Integer adId;
    private String adImg;
    private String adHref;
    
    public Ad() {
    }
    
    public Ad(final String adImg, final String adHref) {
        this.adImg = adImg;
        this.adHref = adHref;
    }
    
    public Integer getAdId() {
        return this.adId;
    }
    
    public void setAdId(final Integer adId) {
        this.adId = adId;
    }
    
    public String getAdImg() {
        return this.adImg;
    }
    
    public void setAdImg(final String adImg) {
        this.adImg = adImg;
    }
    
    public String getAdHref() {
        return this.adHref;
    }
    
    public void setAdHref(final String adHref) {
        this.adHref = adHref;
    }
}
