package com.example.reader.viewmodel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/18.
 */
public class ProductTypeDTO implements Serializable {
    private String hId;
    //1级分类
    private String firstTypeId;
    private String firstTypeName;
    //2级分类
    private String secondTypeId;
    private String secondTypeName;
    // 3级分类
    private String thirdTypeId;
    private String thirdTypeName;

    //4级分类
    private String fourthTypeId;
    private String fourthTypeName;

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String getFirstTypeId() {
        return firstTypeId;
    }

    public void setFirstTypeId(String firstTypeId) {
        this.firstTypeId = firstTypeId;
    }

    public String getFirstTypeName() {
        return firstTypeName;
    }

    public void setFirstTypeName(String firstTypeName) {
        this.firstTypeName = firstTypeName;
    }

    public String getSecondTypeId() {
        return secondTypeId;
    }

    public void setSecondTypeId(String secondTypeId) {
        this.secondTypeId = secondTypeId;
    }

    public String getSecondTypeName() {
        return secondTypeName;
    }

    public void setSecondTypeName(String secondTypeName) {
        this.secondTypeName = secondTypeName;
    }

    public String getThirdTypeId() {
        return thirdTypeId;
    }

    public void setThirdTypeId(String thirdTypeId) {
        this.thirdTypeId = thirdTypeId;
    }

    public String getThirdTypeName() {
        return thirdTypeName;
    }

    public void setThirdTypeName(String thirdTypeName) {
        this.thirdTypeName = thirdTypeName;
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

    @Override
    public String toString() {
        return "ProductTypeDTO{" +
                "hId='" + hId + '\'' +
                ", firstTypeId='" + firstTypeId + '\'' +
                ", firstTypeName='" + firstTypeName + '\'' +
                ", secondTypeId='" + secondTypeId + '\'' +
                ", secondTypeName='" + secondTypeName + '\'' +
                ", thirdTypeId='" + thirdTypeId + '\'' +
                ", thirdTypeName='" + thirdTypeName + '\'' +
                ", fourthTypeId='" + fourthTypeId + '\'' +
                ", fourthTypeName='" + fourthTypeName + '\'' +
                '}';
    }
}
