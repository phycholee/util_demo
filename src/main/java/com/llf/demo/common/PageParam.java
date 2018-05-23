package com.llf.demo.common;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/5/22 16:47
 */
public class PageParam {

    private int page = 1;

    private int pageSize = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
