package com.example.reader.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/10/23.
 */
@Component
public class MyPropertiesUtil {


    @Value("${stock.stockId}")
    private String stockId;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    @Override
    public String toString() {
        return "MyPropertiesUtil{" +
                "stockId='" + stockId + '\'' +
                '}';
    }
}
