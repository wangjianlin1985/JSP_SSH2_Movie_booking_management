// 
// 
// 

package com.service;

import com.pojo.Ad;
import java.util.List;
import com.dao.AdDao;

public class AdService
{
    private AdDao adDao;
    
    public void setAdDao(final AdDao adDao) {
        this.adDao = adDao;
    }
    
    public List<Ad> findAll() {
        return this.adDao.selectAll();
    }
    
    public void modify(final Ad ad) {
        final Ad OldAd = this.adDao.selectById(ad.getAdId());
        OldAd.setAdHref(ad.getAdHref());
        if (ad.getAdImg() != null) {
            OldAd.setAdImg(ad.getAdImg());
        }
        this.adDao.update(OldAd);
    }
}
