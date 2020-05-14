package com.example.reader.domain.goodsCirculation;

import com.example.reader.domain.goodsCirculation.model.GoodsCirculation;
import com.example.reader.domain.goodsCirculation.model.LastGoodsCirculation;
import com.example.reader.infrastructure.goodsCirculation.GoodsCirculationRepository;
import com.example.reader.viewmodel.CirculationConditionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
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
        }
        return circulationRepository.queryLastGoodsCirculationByCondition(circulationConditionQuery);
    }
}
