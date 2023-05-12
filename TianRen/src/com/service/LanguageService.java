// 
// 
// 

package com.service;

import com.pojo.Language;
import java.util.List;
import com.dao.LanguageDao;

public class LanguageService
{
    private LanguageDao languageDao;
    
    public void setLanguageDao(final LanguageDao languageDao) {
        this.languageDao = languageDao;
    }
    
    public List<Language> findAll() {
        return this.languageDao.selectAll();
    }
    
    public void create(final Language language) {
        this.languageDao.insert(language);
    }
}
