// 
// 
// 

package com.dao;

import java.io.Serializable;
import java.util.List;
import com.pojo.Language;
import com.dao.base.GenericDaoImpl;

public class LanguageDao extends GenericDaoImpl<Language, Integer>
{
    @Override
    public Language selectById(final Integer id) {
        return super.selectById(id);
    }
    
    @Override
    public List<Language> selectAll() {
        return super.selectAll();
    }
    
    @Override
    public void insert(final Language language) {
        super.insert(language);
    }
    
    @Override
    public void delete(final Language language) {
        super.delete(language);
    }
    
    @Override
    public void update(final Language language) {
        super.update(language);
    }
}
