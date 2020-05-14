package com.example.reader.viewmodel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/15.
 */
public class ConditionQuery implements Serializable{

    /**
     * 页码
     */
    private int currentPage;
    /**
     * 每页大小
     */
    private int pageSize;

    private String isbn;
    //
    private String hName;

    //价格
    private Float hOutPutPrice;//

    // 作者
    private String hWriter;


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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public Float gethOutPutPrice() {
        return hOutPutPrice;
    }

    public void sethOutPutPrice(Float hOutPutPrice) {
        this.hOutPutPrice = hOutPutPrice;
    }

    public String gethWriter() {
        return hWriter;
    }

    public void sethWriter(String hWriter) {
        this.hWriter = hWriter;
    }

    @Override
    public String toString() {
        return "ConditionQuery{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", isbn='" + isbn + '\'' +
                ", hName='" + hName + '\'' +
                ", hOutPutPrice=" + hOutPutPrice +
                ", hWriter='" + hWriter + '\'' +
                '}';
    }
}
