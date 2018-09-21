package cn.sisyphe.reader.domain.goodsCirculation;

import cn.sisyphe.common.ddd.domain.annotations.DomainService;
import cn.sisyphe.reader.domain.goodsCirculation.model.GoodsCirculation;
import cn.sisyphe.reader.domain.goodsCirculation.model.LastGoodsCirculation;
import cn.sisyphe.reader.infrastructure.goodsCirculation.GoodsCirculationRepository;
import cn.sisyphe.reader.viewmodel.CirculationConditionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */
@Service
@DomainService
public class GoodsCirculationServiceImpl implements GoodsCirculationService {


    @Autowired
    private GoodsCirculationRepository circulationRepository;

    /**
     * @param circulationConditionQuery
     * @return
     */

    @Override
    public Integer queryTotalPageLastGoodsCirculation(CirculationConditionQuery circulationConditionQuery) {
        return circulationRepository.queryTotalPageLastGoodsCirculation(circulationConditionQuery);
    }

    /**
     * @param circulationConditionQuery
     * @return
     */
    @Override
    public Integer queryTotalPageGoodsCirculation(CirculationConditionQuery circulationConditionQuery) {
        return circulationRepository.queryTotalPageGoodsCirculation(circulationConditionQuery);
    }


    /**
     * @param circulationConditionQuery
     * @return
     */
    @Override
    public List<GoodsCirculation> queryByCondition(CirculationConditionQuery circulationConditionQuery) {

        if (circulationConditionQuery.getCurrentPage() <= 1) {
            circulationConditionQuery.setCurrentPage(1);
            //conditionQuery.setPageSize(50);
        }
        return circulationRepository.queryByCondition(circulationConditionQuery);
    }


    /**
     * 商品末位查询
     *
     * @param circulationConditionQuery
     * @return
     */

    @Override
    public List<LastGoodsCirculation> queryLastGoodsCirculationByCondition(CirculationConditionQuery circulationConditionQuery) {

        if (circulationConditionQuery.getCurrentPage() <= 1) {
            circulationConditionQuery.setCurrentPage(1);
            //conditionQuery.setPageSize(50);
        }
        return circulationRepository.queryLastGoodsCirculationByCondition(circulationConditionQuery);
    }
}
