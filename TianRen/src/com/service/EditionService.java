// 
// 
// 

package com.service;

import com.pojo.Edition;
import java.util.List;
import com.dao.EditionDao;

public class EditionService
{
    private EditionDao editionDao;
    
    public void setEditionDao(final EditionDao editionDao) {
        this.editionDao = editionDao;
    }
    
    public List<Edition> findAll() {
        return this.editionDao.selectAll();
    }
    
    public void create(final Edition edition) {
        this.editionDao.insert(edition);
    }
}
