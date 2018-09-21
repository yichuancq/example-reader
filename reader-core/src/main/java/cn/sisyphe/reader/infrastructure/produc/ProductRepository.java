package cn.sisyphe.reader.infrastructure.produc;

import cn.sisyphe.common.ddd.domain.annotations.DomainRepository;
import cn.sisyphe.reader.domain.product.model.Product;
import cn.sisyphe.reader.viewmodel.ProductDTO;
import cn.sisyphe.reader.viewmodel.ProductTypeDTO;
import cn.sisyphe.reader.viewmodel.StockDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 */
@DomainRepository
public interface ProductRepository {

    List<Product> findByNameLike(String name);

    /**
     * @param specification
     * @param pageable
     * @return
     */
    Page<Product> findByCondition(Specification<Product> specification, Pageable pageable);




    //商品查询
    List<ProductDTO> queryProductsByCondition(int pageIndex, int pageSize,
                                              String sId, String hIsbn,
                                              String hName, String hWriter, Float hOutPutPrice);

    /**
     * @param hId
     * @return
     */
    ProductTypeDTO queryProductType(String hId);

    /**
     * @param sId
     * @param hIsbn
     * @param hName
     * @param hWriter
     * @return
     */
    Integer totalPage(String sId, String hIsbn, String hName, String hWriter, Float hOutPutPrice);

    /**
     * 库存
     *
     * @param hId
     * @return
     */
    List<StockDTO> queryStocks(String hId);
}
