package com.example.reader.domain.goodsCirculation.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品流转类
 * Created by Administrator on 2017/10/19.
 */
public class GoodsCirculation implements Serializable {


    private int rowNum;//
    private String paId;// 区位编码
    private String hId;
    private String hName;//
    private String hIsbn;//
    private String hType;// 一级分类
    private String hTypeName;// 一级分类名称

    private String hTypeReal;// 末级分类
    private String hTypeRealName;//
    private String stationId;//
    private String paName;// 区名
    private String atName;// 区位描述(架号)
    private String memo;// 备注
    private int stockHAmount;// 库存数量
    private int saleAmount;// 销售数量
    private int totalAmount;// 到货数量
    private int lastTotalAmount;// 上次库存数量
    private String isSaleOff;// 是否断货
    private String isShelves;// 是否下架

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date hInputDate;// 建档时间


    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inTime; // 进入时间


    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastModDate;// 最后修改时间


    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date saleDate;// 销售日期

    public float outPutPrice;// 售价

    @Override
    public String toString() {
        return "GoodsCirculation{" +
                "rowNum=" + rowNum +
                ", paId='" + paId + '\'' +
                ", hId='" + hId + '\'' +
                ", hName='" + hName + '\'' +
                ", hIsbn='" + hIsbn + '\'' +
                ", hType='" + hType + '\'' +
                ", hTypeName='" + hTypeName + '\'' +
                ", hTypeReal='" + hTypeReal + '\'' +
                ", hTypeRealName='" + hTypeRealName + '\'' +
                ", stationId='" + stationId + '\'' +
                ", paName='" + paName + '\'' +
                ", atName='" + atName + '\'' +
                ", memo='" + memo + '\'' +
                ", stockHAmount=" + stockHAmount +
                ", saleAmount=" + saleAmount +
                ", totalAmount=" + totalAmount +
                ", lastTotalAmount=" + lastTotalAmount +
                ", isSaleOff='" + isSaleOff + '\'' +
                ", isShelves='" + isShelves + '\'' +
                ", hInputDate=" + hInputDate +
                ", inTime=" + inTime +
                ", lastModDate=" + lastModDate +
                ", saleDate=" + saleDate +
                ", outPutPrice=" + outPutPrice +
                '}';
    }

    public String getPaId() {

        return paId;
    }

    public void setPaId(String paId) {
        this.paId = paId;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

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

    public String gethType() {
        return hType;
    }

    public void sethType(String hType) {
        this.hType = hType;
    }

    public String gethTypeName() {
        return hTypeName;
    }

    public void sethTypeName(String hTypeName) {
        this.hTypeName = hTypeName;
    }

    public String gethTypeReal() {
        return hTypeReal;
    }

    public void sethTypeReal(String hTypeReal) {
        this.hTypeReal = hTypeReal;
    }

    public String gethTypeRealName() {
        return hTypeRealName;
    }

    public void sethTypeRealName(String hTypeRealName) {
        this.hTypeRealName = hTypeRealName;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getPaName() {
        return paName;
    }

    public void setPaName(String paName) {
        this.paName = paName;
    }

    public String getAtName() {
        return atName;
    }

    public void setAtName(String atName) {
        this.atName = atName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getStockHAmount() {
        return stockHAmount;
    }

    public void setStockHAmount(int stockHAmount) {
        this.stockHAmount = stockHAmount;
    }

    public int getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(int saleAmount) {
        this.saleAmount = saleAmount;
    }


    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getLastTotalAmount() {
        return lastTotalAmount;
    }

    public void setLastTotalAmount(int lastTotalAmount) {
        this.lastTotalAmount = lastTotalAmount;
    }

    public String getIsSaleOff() {
        return isSaleOff;
    }

    public void setIsSaleOff(String isSaleOff) {
        this.isSaleOff = isSaleOff;
    }

    public String getIsShelves() {
        return isShelves;
    }

    public void setIsShelves(String isShelves) {
        this.isShelves = isShelves;
    }

    public Date gethInputDate() {
        return hInputDate;
    }

    public void sethInputDate(Date hInputDate) {
        this.hInputDate = hInputDate;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Date lastModDate) {
        this.lastModDate = lastModDate;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public float getOutPutPrice() {
        return outPutPrice;
    }

    public void setOutPutPrice(float outPutPrice) {
        this.outPutPrice = outPutPrice;
    }
}
