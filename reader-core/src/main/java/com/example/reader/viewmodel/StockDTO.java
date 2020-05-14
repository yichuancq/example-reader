package com.example.reader.viewmodel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/18.
 */
public class StockDTO implements Serializable {

    private String hId;
    private String sId; //库位id
    private Integer sumAmount;// 库存
    private String stationName; //站点
    private String stockAttribute; //库位名称

    public String gethId() {

        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public Integer getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(Integer sumAmount) {
        this.sumAmount = sumAmount;
    }

    public String getStockAttribute() {
        return stockAttribute;
    }

    public void setStockAttribute(String stockAttribute) {
        this.stockAttribute = stockAttribute;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }


    @Override
    public String toString() {
        return "StockDTO{" +
                "hId='" + hId + '\'' +
                ", sId='" + sId + '\'' +
                ", sumAmount=" + sumAmount +
                ", stationName='" + stationName + '\'' +
                ", stockAttribute='" + stockAttribute + '\'' +
                '}';
    }
}
