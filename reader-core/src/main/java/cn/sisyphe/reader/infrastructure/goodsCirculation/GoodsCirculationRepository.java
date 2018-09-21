package cn.sisyphe.reader.infrastructure.goodsCirculation;

import cn.sisyphe.common.ddd.domain.annotations.DomainRepository;
import cn.sisyphe.reader.domain.goodsCirculation.model.GoodsCirculation;
import cn.sisyphe.reader.domain.goodsCirculation.model.LastGoodsCirculation;
import cn.sisyphe.reader.viewmodel.CirculationConditionQuery;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */

@DomainRepository
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
