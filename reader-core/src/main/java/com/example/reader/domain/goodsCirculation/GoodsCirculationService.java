package com.example.reader.domain.goodsCirculation;

import com.example.reader.domain.goodsCirculation.model.GoodsCirculation;
import com.example.reader.domain.goodsCirculation.model.LastGoodsCirculation;
import com.example.reader.viewmodel.CirculationConditionQuery;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */
public interface GoodsCirculationService {


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



    //商品流查询
    List<GoodsCirculation> queryByCondition(CirculationConditionQuery circulationConditionQuery);


    /**
     * 商品末位查询
     *
     * @param circulationConditionQuery
     * @return
     */
    List<LastGoodsCirculation> queryLastGoodsCirculationByCondition(CirculationConditionQuery circulationConditionQuery);
}
