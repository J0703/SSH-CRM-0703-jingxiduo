package com.lanou.human_resource.util;

import java.util.List;

/**
 * Created by jbtms940317 on 17/10/31.
 */
public class PageBean<T> {
    //必选项
    private int pageNum;//第几页
    private int pageSize;//每页显示的条目数
    private int totalRecord;//总的记录数

    //计算项
    private int startIndex;//开始索引
    private int totalPage;//总分页数

    //数据
    private List<T> data;//分页数据

    public PageBean(int pageNum, int pageSize, int totalRecord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
    }


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getStartIndex() {
        int startIndex = (pageNum - 1) * pageSize;
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalPage() {
        int totalPage = totalRecord / pageSize;
        if (totalRecord % pageSize > 0) {
            return totalPage + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalRecord=" + totalRecord +
                ", startIndex=" + startIndex +
                ", totalPage=" + totalPage +
                ", data=" + data +
                '}';
    }
}
