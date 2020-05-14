package com.example.reader.infrastructure.goodsCirculation;

import com.example.reader.domain.goodsCirculation.model.GoodsCirculation;
import com.example.reader.domain.goodsCirculation.model.LastGoodsCirculation;
import com.example.reader.viewmodel.CirculationConditionQuery;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */

public interface GoodsCirculationRepository {
    /**
     * 末位商品总记录数查询
     *
     * @param circulationConditionQuery
     * @return
     */
    Integer queryTotalPageLastGoodsCirculation(CirculationConditionQuery circulationConditionQuery);


    /**
     * 获取商品流转的总页码
     *
     * @param circulationConditionQuery
     * @return
     */
    Integer queryTotalPageGoodsCirculation(CirculationConditionQuery circulationConditionQuery);

    /**
     * @param circulationConditionQuery
     * @return
     */
    List<GoodsCirculation> queryByCondition(CirculationConditionQuery circulationConditionQuery);


    /**
     * @param circulationConditionQuery
     * @return
     */
    List<LastGoodsCirculation> queryLastGoodsCirculationByCondition(CirculationConditionQuery circulationConditionQuery);
}
