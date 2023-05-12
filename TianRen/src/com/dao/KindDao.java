// 
// 
// 

package com.dao;

import java.io.Serializable;
import java.util.List;
import com.pojo.Kind;
import com.dao.base.GenericDaoImpl;

public class KindDao extends GenericDaoImpl<Kind, Integer>
{
    @Override
    public Kind selectById(final Integer id) {
        return super.selectById(id);
    }
    
    @Override
    public List<Kind> selectAll() {
        return super.selectAll();
    }
    
    @Override
    public void insert(final Kind kind) {
        super.insert(kind);
    }
    
    @Override
    public void delete(final Kind kind) {
        super.delete(kind);
    }
    
    @Override
    public void update(final Kind kind) {
        super.update(kind);
    }
}
