package cn.sisyphe.reader.application;

import cn.sisyphe.common.ddd.annotations.ApplicationService;
import cn.sisyphe.common.exception.DataException;
import cn.sisyphe.reader.domain.product.ProductService;
import cn.sisyphe.reader.domain.product.model.Product;
import cn.sisyphe.reader.viewmodel.ConditionQuery;
import cn.sisyphe.reader.viewmodel.ProductDTO;
import cn.sisyphe.reader.viewmodel.ProductTypeDTO;
import cn.sisyphe.reader.viewmodel.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 */
@ApplicationService
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
     * @company 西西弗文化传播
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
