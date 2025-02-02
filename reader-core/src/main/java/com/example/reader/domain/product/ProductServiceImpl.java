package com.example.reader.domain.product;

import com.example.reader.domain.product.model.Product;
import com.example.reader.infrastructure.produc.ProductRepository;
import com.example.reader.utils.MyPropertiesUtil;
import com.example.reader.viewmodel.ConditionQuery;
import com.example.reader.viewmodel.ProductDTO;
import com.example.reader.viewmodel.ProductTypeDTO;
import com.example.reader.viewmodel.StockDTO;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    /**
     * 自定义属性配置文件
     */
    @Autowired
    private MyPropertiesUtil myProperties;

    /**
     * 总页数
     *
     * @param conditionQuery
     * @return
     */
    @Override
    public Integer getTotalPage(ConditionQuery conditionQuery) {
        return productRepository.totalPage(
                myProperties.getStockId(),
                conditionQuery.getIsbn(),
                conditionQuery.gethName(),
                conditionQuery.gethWriter(), conditionQuery.gethOutPutPrice());
    }


    /**
     * @param conditionQuery
     * @return
     * @throws DataException
     */
    @Override
    public List<ProductDTO> queryByConditionWithSQL(ConditionQuery conditionQuery) throws DataException {

        if (conditionQuery.getCurrentPage() <= 1) {
            conditionQuery.setCurrentPage(1);
        }
        List<ProductDTO> productDTOList = productRepository.queryProductsByCondition(
                conditionQuery.getCurrentPage(), conditionQuery.getPageSize(),
                myProperties.getStockId(),
                conditionQuery.getIsbn(),
                conditionQuery.gethName(),
                conditionQuery.gethWriter(),
                conditionQuery.gethOutPutPrice()
        );
        return productDTOList;
    }


    /**
     * 类别
     *
     * @param hId
     * @return
     */
    @Override
    public ProductTypeDTO queryProductType(String hId) {

        return productRepository.queryProductType(hId);
    }

    /**
     * @param hId
     * @return
     */

    @Override
    public List<StockDTO> queryStockWithDifferStations(String hId) {
        return productRepository.queryStocks(hId);
    }


    /**
     * @author yichuan
     * @Date 2017/10/15 15:58
     **/
    @Override
    public Page<Product> queryByCondition(ConditionQuery conditionQuery) throws DataException {
        // 组装页面
        Pageable pageable = new PageRequest(conditionQuery.getCurrentPage() - 1,
                conditionQuery.getPageSize(), null);
        // 组装开始时间和结束时间
        return this.queryByParams(conditionQuery, pageable);
    }


    /**
     * 查询参数拼接
     *
     * @param conditionQuery
     * @param pageable
     * @return
     */
    private Page<Product> queryByParams(final ConditionQuery conditionQuery, Pageable pageable) {

        Page<Product> productPage = productRepository.findByCondition(new Specification<Product>() {

            public javax.persistence.criteria.Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                javax.persistence.criteria.Predicate predicate = cb.conjunction();
                List<Expression<Boolean>> expressions = predicate.getExpressions();

                if (conditionQuery.getIsbn() != null) {
                    expressions.add(cb.equal(root.get("hIsbn"), conditionQuery.getIsbn()));
                }
                //图书名称匹配
                if (conditionQuery.gethName() != null) {

                    expressions.add(cb.like(root.get("hName"), "%" + conditionQuery.gethName() + "%"));
                }
                // 作者
                if (conditionQuery.gethWriter() != null) {
                    expressions.add(cb.equal(root.get("hWriter"), conditionQuery.gethWriter()));

                }
                // 价格
                if (conditionQuery.gethOutPutPrice() != null) {
                    expressions.add(cb.equal(root.get("hOutPutPrice"), conditionQuery.gethOutPutPrice()));
                }
                return predicate;
            }
        }, pageable);

        return productPage;
    }

}
