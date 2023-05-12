// 
// 
// 

package com.service;

import com.pojo.Kind;
import java.util.List;
import com.dao.KindDao;

public class KindService
{
    private KindDao kindDao;
    
    public void setKindDao(final KindDao kindDao) {
        this.kindDao = kindDao;
    }
    
    public List<Kind> findAll() {
        return this.kindDao.selectAll();
    }
    
    public void create(final Kind kind) {
        this.kindDao.insert(kind);
    }
}
