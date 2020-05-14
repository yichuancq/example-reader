package com.example.reader.application;

import com.example.reader.domain.goodsCirculation.GoodsCirculationService;
import com.example.reader.domain.goodsCirculation.model.GoodsCirculation;
import com.example.reader.domain.goodsCirculation.model.LastGoodsCirculation;
import com.example.reader.viewmodel.CirculationConditionQuery;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品流查询
 * Created by Administrator on 2017/10/19.
 */
@Service
public class GoodsCirculationManager {

    @Autowired
    private GoodsCirculationService goodsCirculationService;

    /**
     * 末位商品总记录数查询
     *
     * @param circulationConditionQuery
     * @return
     */
    public Integer queryTotalPageLastGoodsCirculation(CirculationConditionQuery circulationConditionQuery) {

        return goodsCirculationService.queryTotalPageLastGoodsCirculation(circulationConditionQuery);
    }

    /**
     * 获取商品流转的总页码
     *
     * @param circulationConditionQuery
     * @return
     */
    public Integer queryTotalPageGoodsCirculation(CirculationConditionQuery circulationConditionQuery) {
        return goodsCirculationService.queryTotalPageGoodsCirculation(circulationConditionQuery);

    }


    /**
     * 商品流查询
     *
     * @param circulationConditionQuery
     * @return
     * @throws DataException
     */
    public List<GoodsCirculation> queryByCondition(CirculationConditionQuery circulationConditionQuery) throws DataException {

        return goodsCirculationService.queryByCondition(circulationConditionQuery);
    }


    /**
     * 商品末位查询
     *
     * @param circulationConditionQuery
     * @return
     * @throws DataException
     */
    public List<LastGoodsCirculation> queryLastGoodsCirculationByCondition(CirculationConditionQuery circulationConditionQuery) throws DataException {

        return goodsCirculationService.queryLastGoodsCirculationByCondition(circulationConditionQuery);
    }


}
