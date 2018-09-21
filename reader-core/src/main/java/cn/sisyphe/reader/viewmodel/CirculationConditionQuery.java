package cn.sisyphe.reader.viewmodel;

import java.io.Serializable;

/**
 * 商品流转查询条件
 * Created by Administrator on 2017/10/19.
 */
public class CirculationConditionQuery implements Serializable {


    private String hName;

    private String hIsbn;

    private String atName;

    private String startTime;

    private String endTime;

    /**
     * 页码
     */
    private int currentPage;
    /**
     * 每页大小
     */
    private int pageSize;


    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethIsbn() {
        return hIsbn;
    }

    public void sethIsbn(String hIsbn) {
        this.hIsbn = hIsbn;
    }

    public String getAtName() {
        return atName;
    }

    public void setAtName(String atName) {
        this.atName = atName;
    }

    public String getStartTime() {

        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "CirculationConditionQuery{" +
                "hName='" + hName + '\'' +
                ", hIsbn='" + hIsbn + '\'' +
                ", atName='" + atName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
