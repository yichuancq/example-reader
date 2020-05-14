package com.example.reader.infrastructure.goodsCirculation;

import com.example.reader.domain.goodsCirculation.model.GoodsCirculation;
import com.example.reader.domain.goodsCirculation.model.LastGoodsCirculation;
import com.example.reader.infrastructure.goodsCirculation.jdbc.JdbcGoodsCirculationRepository;
import com.example.reader.viewmodel.CirculationConditionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */
@Repository
public class GoodsCirculationRepositoryImpl implements GoodsCirculationRepository {
    /**
     * 商品流
     */

    @Autowired
    private JdbcGoodsCirculationRepository jdbcGoodsCirculationRepository;


    /**
     * 末位商品总记录数查询
     *
     * @param circulationConditionQuery
     * @return
     */

    @Override
    public Integer queryTotalPageLastGoodsCirculation(CirculationConditionQuery circulationConditionQuery) {
        return jdbcGoodsCirculationRepository.queryTotalPageLastGoodsCirculation(circulationConditionQuery);
    }


    /**
     * 获取商品流转的总页码
     *
     * @param circulationConditionQuery
     * @return
     */
    @Override
    public Integer queryTotalPageGoodsCirculation(CirculationConditionQuery circulationConditionQuery) {
        return jdbcGoodsCirculationRepository.queryTotalPageGoodsCirculation(circulationConditionQuery);
    }

    /**
     * 商品流
     *
     * @param circulationConditionQuery
     * @return
     */
    @Override
    public List<GoodsCirculation> queryByCondition(CirculationConditionQuery circulationConditionQuery) {
        return jdbcGoodsCirculationRepository.queryByCondition(circulationConditionQuery);
    }

    /**
     * 末位商品查询
     *
     * @param circulationConditionQuery
     * @return
     */
    @Override
    public List<LastGoodsCirculation> queryLastGoodsCirculationByCondition(CirculationConditionQuery circulationConditionQuery) {
        return jdbcGoodsCirculationRepository.queryLastGoodsCirculationByCondition(circulationConditionQuery);
    }
}
