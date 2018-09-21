package cn.sisyphe.reader.viewmodel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/16.
 */
public class ProductDTO implements Serializable {


    private int rowNum;//

    private int stockAmount;

    private String hId;

    private String hName;

    private Float hOutPutPrice;

    private String hIsbn;

    private String hWriter;

    private String atName;

    private String pubName;

    private String saleStatus;
    //四级分类
    private String fourthTypeId;
    //四级分类
    private String fourthTypeName;

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
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

    public Float gethOutPutPrice() {
        return hOutPutPrice;
    }

    public void sethOutPutPrice(Float hOutPutPrice) {
        this.hOutPutPrice = hOutPutPrice;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }


    public String gethWriter() {

        return hWriter;
    }

    public void sethWriter(String hWriter) {
        this.hWriter = hWriter;
    }

    public String getPubName() {

        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
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

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getFourthTypeId() {

        return fourthTypeId;
    }

    public void setFourthTypeId(String fourthTypeId) {
        this.fourthTypeId = fourthTypeId;
    }

    public String getFourthTypeName() {
        return fourthTypeName;
    }

    public void setFourthTypeName(String fourthTypeName) {
        this.fourthTypeName = fourthTypeName;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;

    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "rowNum=" + rowNum +
                ", stockAmount=" + stockAmount +
                ", hId='" + hId + '\'' +
                ", hName='" + hName + '\'' +
                ", hOutPutPrice=" + hOutPutPrice +
                ", hIsbn='" + hIsbn + '\'' +
                ", hWriter='" + hWriter + '\'' +
                ", atName='" + atName + '\'' +
                ", pubName='" + pubName + '\'' +
                ", saleStatus='" + saleStatus + '\'' +
                ", fourthTypeId='" + fourthTypeId + '\'' +
                ", fourthTypeName='" + fourthTypeName + '\'' +
                '}';
    }
}
