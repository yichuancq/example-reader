package product;

import cn.sisyphe.common.exception.DataException;
import cn.sisyphe.reader.ReaderApplication;
import cn.sisyphe.reader.domain.product.ProductService;
import cn.sisyphe.reader.domain.product.model.Product;
import cn.sisyphe.reader.infrastructure.produc.ProductRepository;
import cn.sisyphe.reader.infrastructure.produc.jdbc.JdbcProductRepository;
import cn.sisyphe.reader.viewmodel.ConditionQuery;
import cn.sisyphe.reader.viewmodel.ProductDTO;
import cn.sisyphe.reader.viewmodel.ProductTypeDTO;
import cn.sisyphe.reader.viewmodel.StockDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReaderApplication.class)
public class TestProduct {


    private final static Logger log = LoggerFactory.getLogger(TestProduct.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcProductRepository jdbcProductRepository;


    @Autowired
    ProductService productService;


    @Test
    public void testTypeName() {

        String hId = "71153597730099000000";
        ProductTypeDTO productTypeDTO = jdbcProductRepository.queryProductType(hId);

        List<StockDTO> stockDTOList = jdbcProductRepository.queryStocks(hId);

        System.out.println(productTypeDTO);
        System.out.println(stockDTOList);

    }


    /**
     * @company 西西弗文化传播
     * @author yichuan
     * @Date 2017/10/23 14:23
     **/
    @Test
    public void testOrgPage() {

        int totalPage = jdbcProductRepository.totalPage("CQ11000001",
                "",
                "朝花夕拾",
                "鲁迅", null);
        System.out.println(" totalPageL:" + totalPage);

        List<ProductDTO> productDTOList = jdbcProductRepository.queryProductsByCondition(
                1, 10,
                "CQ11000001",
                "",
                "朝花夕拾",
                "鲁迅", null);
        for (ProductDTO productDTO : productDTOList) {
            System.out.println("" + productDTO.toString());
        }

    }


    /**
     *
     */
    @Test
    public void findByName() {

        List<Product> products = productRepository.findByNameLike("酒店管理实例与问答");
        for (Product product : products) {
            // Set<ProductType> children = (Set<ProductType>) product.getProductType();
            // System.out.println(" size: " + children.size());
        }
    }

    @Test
    public void testPage() {


        double startTime = System.currentTimeMillis();
        //
        ConditionQuery conditionQuery = new ConditionQuery();
        conditionQuery.setCurrentPage(1);
        conditionQuery.setPageSize(50);
        //conditionQuery.setIsbn("9787530211175");
        conditionQuery.sethName("酒店");
        try {

            Page<Product> productPage = productService.queryByCondition(conditionQuery);
            int total = productPage.getTotalPages();
            log.info("total page{} ", total);
            for (Product product : productPage) {

            }
        } catch (DataException e) {

            e.printStackTrace();
        }
        double endTime = System.currentTimeMillis();
        System.out.println("time cost:" + (endTime - startTime) + " 毫秒");
    }
}
