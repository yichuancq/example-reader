package cn.sisyphe.reader.infrastructure.produc.jpa;

import cn.sisyphe.reader.domain.product.model.Product;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 */
@CacheConfig(cacheNames = "product")
public interface JPAProductRepository extends JpaRepository<Product, String> {

    @Override
    Product findOne(String s);


    // 名称查找
    @Cacheable
    List<Product> findByHNameLike(String hName);

    //
    @Cacheable
    @Query(value = "select A from Product A where  1=1 and A.hName like  %?1% ", nativeQuery = false)
    List<Product> findByNameLike(String name);

    /**
     * @param specification
     * @param pageable
     * @return
     */
    @Cacheable
    Page<Product> findAll(Specification<Product> specification, Pageable pageable);
}
