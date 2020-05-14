package com.example.reader.domain.product.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/10/13 17:31
 **/
@Entity
@Table(name = "db_product")
public class Product implements Serializable {

    @Id
    @Column(name = "h_id")
    private String hId;

    //isbn
    @Column(name = "h_isbn")
    private String hIsbn;

    // 名称
    @Column(name = "h_name")
    private String hName;


    @Column(name = "h_type")
    private String hType;// 类别

    //出版社编码
    @Column(name = "pub_id")
    private String pubId;

    @Column(name = "h_output_price")
    private Float hOutPutPrice;

    //作者
    @Column(name = "h_writer")
    private String hWriter;

    //销售状态
    @Transient
    private String saleStatus;

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

    public String gethType() {
        return hType;
    }

    public String gethIsbn() {
        return hIsbn;
    }

    public void sethIsbn(String hIsbn) {
        this.hIsbn = hIsbn;
    }

    public void sethType(String hType) {
        this.hType = hType;
    }

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId;
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


    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }


    @Override
    public String toString() {
        return "Product{" +
                "hId='" + hId + '\'' +
                ", hIsbn='" + hIsbn + '\'' +
                ", hName='" + hName + '\'' +
                ", hType='" + hType + '\'' + hType +
                ", hOutPutPrice=" + hOutPutPrice +
                ", hWriter='" + hWriter + '\'' +
                ", saleStatus='" + saleStatus + '\'' +
                '}';
    }
}
