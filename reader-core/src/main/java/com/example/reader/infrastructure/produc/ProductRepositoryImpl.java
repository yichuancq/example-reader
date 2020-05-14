package com.example.reader.infrastructure.produc;

import com.example.reader.domain.product.model.Product;
import com.example.reader.infrastructure.produc.jdbc.JdbcProductRepository;
import com.example.reader.infrastructure.produc.jpa.JPAProductRepository;
import com.example.reader.viewmodel.ProductDTO;
import com.example.reader.viewmodel.ProductTypeDTO;
import com.example.reader.viewmodel.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private JPAProductRepository jpaProductRepository;

    @Autowired
    private JdbcProductRepository jdbcProductRepository;


    @Override
    public List<Product> findByNameLike(String name) {
        return jpaProductRepository.findByHNameLike(name);
    }


    @Override
    public Page<Product> findByCondition(Specification<Product> specification, Pageable pageable) {
        return jpaProductRepository.findAll(specification, pageable);
    }

    /**
     * 商品查询
     *
     * @param pageIndex
     * @param pageSize
     * @param sId
     * @param hIsbn
     * @param hName
     * @param hWriter
     * @return
     */
    @Override
    public List<ProductDTO> queryProductsByCondition(int pageIndex, int pageSize, String sId, String hIsbn, String hName,
                                                     String hWriter, Float hOutPutPrice) {
        return jdbcProductRepository.queryProductsByCondition(pageIndex, pageSize, sId, hIsbn, hName, hWriter, hOutPutPrice);
    }


    /**
     * 类别
     *
     * @param hId
     * @return
     */
    @Override
    public ProductTypeDTO queryProductType(String hId) {
        return jdbcProductRepository.queryProductType(hId);
    }

    /**
     * 总页数
     *
     * @param sId
     * @param hIsbn
     * @param hName
     * @param hWriter
     * @return
     */
    @Override
    public Integer totalPage(String sId, String hIsbn, String hName, String hWriter,Float price) {
        return jdbcProductRepository.totalPage(sId, hIsbn, hName, hWriter,price);
    }

    /**
     * 库存
     *
     * @param hId
     * @return
     */
    @Override
    public List<StockDTO> queryStocks(String hId) {
        return jdbcProductRepository.queryStocks(hId);
    }
}
