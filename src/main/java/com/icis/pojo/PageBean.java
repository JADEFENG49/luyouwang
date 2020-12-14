package com.icis.pojo;

import java.util.List;

//分页类
public class PageBean<T> {
    //当前页数
    private Integer currentPage;
    //一页多少条记录
    private Integer pageSize;
    //数据集合
    private List<T> dataList;
    //总记录数
    private Integer totalCount;
    //最大页数
    private Integer totalPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", dataList=" + dataList +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                '}';
    }
}
