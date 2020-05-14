package com.example.reader.application;

import com.example.reader.domain.product.ProductService;
import com.example.reader.domain.product.model.Product;
import com.example.reader.viewmodel.ConditionQuery;
import com.example.reader.viewmodel.ProductDTO;
import com.example.reader.viewmodel.ProductTypeDTO;
import com.example.reader.viewmodel.StockDTO;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 */
@Service
public class ProductManager {

    @Autowired
    private ProductService productService;


    /**
     * JPA 方式查询
     *
     * @param conditionQuery
     * @return
     * @throws DataException
     */
    public Page<Product> queryByCondition(ConditionQuery conditionQuery) throws DataException {

        return productService.queryByCondition(conditionQuery);
    }


    /**
     * 总页数
     *
     * @param conditionQuery
     * @return
     */
    public Integer getTotalPage(ConditionQuery conditionQuery) {

        return productService.getTotalPage(conditionQuery);
    }


    /**
     * @company
     * @author yichuan
     * @Date 2017/10/17 9:14
     **/
    public List<ProductDTO> queryByConditionWithSQL(ConditionQuery conditionQuery) throws DataException {

        return productService.queryByConditionWithSQL(conditionQuery);
    }


    /**
     * 商品各级类别
     *
     * @param hId
     * @return
     */
    public ProductTypeDTO queryProductType(String hId) {
        return productService.queryProductType(hId);
    }


    /**
     * 不同的站点库存分布
     *
     * @param hId
     * @return
     */
    public List<StockDTO> queryStockWithDifferStations(String hId) {

        return productService.queryStockWithDifferStations(hId);
    }


}
