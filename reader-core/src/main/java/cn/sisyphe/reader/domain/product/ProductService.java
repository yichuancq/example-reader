package cn.sisyphe.reader.domain.product;

import cn.sisyphe.common.exception.DataException;
import cn.sisyphe.reader.domain.product.model.Product;
import cn.sisyphe.reader.viewmodel.ConditionQuery;
import cn.sisyphe.reader.viewmodel.ProductDTO;
import cn.sisyphe.reader.viewmodel.ProductTypeDTO;
import cn.sisyphe.reader.viewmodel.StockDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 */
public interface ProductService {


    Page<Product> queryByCondition(ConditionQuery conditionQuery) throws DataException;


    /**
     * @param conditionQuery
     * @return
     */
    Integer getTotalPage(ConditionQuery conditionQuery);

    /**
     * 原始SQL查询
     *
     * @param conditionQuery
     * @return
     * @throws DataException
     */
    List<ProductDTO> queryByConditionWithSQL(ConditionQuery conditionQuery) throws DataException;


    /**
     * 商品各级类别
     *
     * @param hId
     * @return
     */
    ProductTypeDTO queryProductType(String hId);


    /**
     * 不同的站点库存分布
     *
     * @param hId
     * @return
     */
    List<StockDTO> queryStockWithDifferStations(String hId);


}
