// 
// 
// 

package com.dao;

import java.io.Serializable;
import java.util.List;
import com.pojo.Edition;
import com.dao.base.GenericDaoImpl;

public class EditionDao extends GenericDaoImpl<Edition, Integer>
{
    @Override
    public Edition selectById(final Integer id) {
        return super.selectById(id);
    }
    
    @Override
    public List<Edition> selectAll() {
        return super.selectAll();
    }
    
    @Override
    public void insert(final Edition edition) {
        super.insert(edition);
    }
    
    @Override
    public void delete(final Edition edition) {
        super.delete(edition);
    }
    
    @Override
    public void update(final Edition edition) {
        super.update(edition);
    }
}
