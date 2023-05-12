// 
// 
// 

package com.action;

import com.util.FileProcessUitl;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import java.io.File;
import com.service.AdService;
import com.pojo.Ad;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class AdAction extends ActionSupport implements ModelDriven<Ad>
{
    private Ad ad;
    private AdService adService;
    private File[] upload;
    private String[] uploadContentType;
    private String[] uploadFileName;
    
    public AdAction() {
        this.ad = new Ad();
        this.adService = new AdService();
    }
    
    public Ad getModel() {
        return this.ad;
    }
    
    public void setAdService(final AdService adService) {
        this.adService = adService;
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
    
    public String findAllAd() throws Exception {
        final Map<String, Object> application = (Map<String, Object>)ActionContext.getContext().getApplication();
        final List<Ad> lstAd = this.adService.findAll();
        application.put("lstAd", lstAd);
        return "updateAd";
    }
    
    public String modifyAd() throws Exception {
        final FileProcessUitl util = new FileProcessUitl();
        final String path = util.processFileUpload("/uploadAd", this.upload, this.uploadFileName);
        this.ad.setAdImg(path);
        this.adService.modify(this.ad);
        return this.findAllAd();
    }
}
