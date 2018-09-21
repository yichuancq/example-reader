package cn.sisyphe.reader.application;

import cn.sisyphe.common.ddd.annotations.ApplicationService;
import cn.sisyphe.common.exception.DataException;
import cn.sisyphe.reader.domain.goodsCirculation.GoodsCirculationService;
import cn.sisyphe.reader.domain.goodsCirculation.model.GoodsCirculation;
import cn.sisyphe.reader.domain.goodsCirculation.model.LastGoodsCirculation;
import cn.sisyphe.reader.viewmodel.CirculationConditionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品流查询
 * Created by Administrator on 2017/10/19.
 */
@ApplicationService
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
