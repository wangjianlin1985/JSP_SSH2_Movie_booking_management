// 
// 
// 

package com.util;

import java.util.ArrayList;
import java.util.List;

public class PageBean
{
    private int totalRows;
    private int totalPages;
    private int currentPage;
    private int pageSize;
    private List data;
    
    public PageBean() {
        this.data = new ArrayList();
    }
    
    public int getTotalRows() {
        return this.totalRows;
    }
    
    public void setTotalRows(final int totalRows) {
        this.totalRows = totalRows;
    }
    
    public int getTotalPages() {
        return (this.totalRows % this.pageSize == 0) ? (this.totalRows / this.pageSize) : (this.totalRows / this.pageSize + 1);
    }
    
    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }
    
    public int getCurrentPage() {
        return this.currentPage;
    }
    
    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }
    
    public List getData() {
        return this.data;
    }
    
    public void setData(final List data) {
        this.data = data;
    }
}
