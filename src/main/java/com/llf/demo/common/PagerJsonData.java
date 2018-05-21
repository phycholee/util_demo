package com.llf.demo.common;

/**
 * @author: Oliver.li
 * @Description: 返回分页json数据类
 * @date: 2018/5/21 15:48
 */
public class PagerJsonData extends JsonData {

    private int page;

    private int pageSize;

    private long total;

    public PagerJsonData(int code, String msg, Object data, int page, int pageSize, long total) {
        super(code, msg, data);
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
    }

    public static PagerJsonData page(int page, int pageSize, long total, Object data){
        return new PagerJsonData(SUCCESS, SUCCESS_MSG, data, page, pageSize, total);
    }

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
