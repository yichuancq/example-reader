package goodsCirculation;

import com.example.reader.ReaderApplication;
import com.example.reader.domain.goodsCirculation.model.GoodsCirculation;
import com.example.reader.domain.goodsCirculation.model.LastGoodsCirculation;
import com.example.reader.infrastructure.goodsCirculation.jdbc.JdbcGoodsCirculationRepository;
import com.example.reader.utils.YHPasswordUtil;
import com.example.reader.viewmodel.CirculationConditionQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReaderApplication.class)
public class TestGoodsCirculation {


    private final static Logger log = LoggerFactory.getLogger(TestGoodsCirculation.class);


    @Autowired
    private JdbcGoodsCirculationRepository jdbcGoodsCirculationRepository;


    /**
     * 密码解密
     */
    @Test
    public void testPwd() {

        String pwd = YHPasswordUtil.decryptionForOperator("YH0102YH");//
        System.out.println("pwd:" + pwd);
    }


    /**
     * 商品末位查询
     */
    @Test
    public void queryLastGoodsCirculation() {
        CirculationConditionQuery circulationConditionQuery = new CirculationConditionQuery();

        circulationConditionQuery.setCurrentPage(1);
        circulationConditionQuery.setPageSize(100);
        circulationConditionQuery.sethName("历史");
        List<LastGoodsCirculation> goodsCirculationList = jdbcGoodsCirculationRepository
                .queryLastGoodsCirculationByCondition(circulationConditionQuery);

        for (LastGoodsCirculation goodsCirculation : goodsCirculationList) {
            System.out.println("" + goodsCirculation);
        }
    }

    @Test
    public void queryGoodsCirculation() {


        CirculationConditionQuery circulationConditionQuery = new CirculationConditionQuery();
        circulationConditionQuery.setCurrentPage(1);
        circulationConditionQuery.setPageSize(100);
        circulationConditionQuery.sethName("历史");
        //
        List<GoodsCirculation> goodsCirculationList = jdbcGoodsCirculationRepository
                .queryByCondition(circulationConditionQuery);

        for (GoodsCirculation goodsCirculation : goodsCirculationList) {

            System.out.println("" + goodsCirculation);
        }

    }


}
