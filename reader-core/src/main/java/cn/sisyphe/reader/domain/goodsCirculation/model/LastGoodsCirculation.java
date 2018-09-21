package cn.sisyphe.reader.domain.goodsCirculation.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * 末位商品流信息
 * Created by Administrator on 2017/10/19.
 */
public class LastGoodsCirculation implements Serializable {

    private int rowNum;
    private String hId;
    private String shelfId;//架号
    private String stationId;// 站点编码
    private String hIsbn;//
    private String hType;// 商品分类编号
    private String typeName;// 分类名称
    private String hTypeReal;//
    private String hTypeRealName;// 末位分类
    private String hName;// 商品名称
    private int totalAmount;// 进入数量
    private int stockHAmount;// 当前库存
    private String paIdForm;// 分区ID
    private String paNameFrom;// 分区名称
    private String paName;//
    private String paIdTo;// 分区ID
    private String paNameTo;// 分区名称
    private String memo;// 备注
    private float outPutPrice;// 售价


    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cfDateTime;// 操作时间


    @Override
    public String toString() {
        return "LastGoodsCirculation{" +
                "rowNum=" + rowNum +
                ", hId='" + hId + '\'' +
                ", shelfId='" + shelfId + '\'' +
                ", stationId='" + stationId + '\'' +
                ", hIsbn='" + hIsbn + '\'' +
                ", hType='" + hType + '\'' +
                ", typeName='" + typeName + '\'' +
                ", hTypeReal='" + hTypeReal + '\'' +
                ", hTypeRealName='" + hTypeRealName + '\'' +
                ", hName='" + hName + '\'' +
                ", totalAmount=" + totalAmount +
                ", stockHAmount=" + stockHAmount +
                ", paIdForm='" + paIdForm + '\'' +
                ", paNameFrom='" + paNameFrom + '\'' +
                ", paName='" + paName + '\'' +
                ", paIdTo='" + paIdTo + '\'' +
                ", paNameTo='" + paNameTo + '\'' +
                ", memo='" + memo + '\'' +
                ", outPutPrice=" + outPutPrice +
                ", cfDateTime=" + cfDateTime +
                '}';
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getPaName() {
        return paName;
    }

    public void setPaName(String paName) {
        this.paName = paName;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getStockHAmount() {
        return stockHAmount;
    }

    public void setStockHAmount(int stockHAmount) {
        this.stockHAmount = stockHAmount;
    }

    public Date getCfDateTime() {
        return cfDateTime;
    }

    public void setCfDateTime(Date cfDateTime) {
        this.cfDateTime = cfDateTime;
    }

    public String getPaIdForm() {
        return paIdForm;
    }

    public void setPaIdForm(String paIdForm) {
        this.paIdForm = paIdForm;
    }

    public String getPaNameFrom() {
        return paNameFrom;
    }

    public void setPaNameFrom(String paNameFrom) {
        this.paNameFrom = paNameFrom;
    }

    public String getPaIdTo() {
        return paIdTo;
    }

    public void setPaIdTo(String paIdTo) {
        this.paIdTo = paIdTo;
    }

    public String getPaNameTo() {
        return paNameTo;
    }

    public void setPaNameTo(String paNameTo) {
        this.paNameTo = paNameTo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public float getOutPutPrice() {
        return outPutPrice;
    }

    public void setOutPutPrice(float outPutPrice) {
        this.outPutPrice = outPutPrice;
    }
}


