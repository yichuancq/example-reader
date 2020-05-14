package com.example.reader.controller;

import com.example.reader.application.GoodsCirculationManager;
import com.example.reader.viewmodel.CirculationConditionQuery;
import com.example.reader.viewmodel.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 商品流
 *
 * @author yichuan
 * @Date 2017/10/19 11:02
 **/
@RestController
@Api(description = "商品流查询")
@CrossOrigin(origins = "*")
@RequestMapping("/api/reader/GoodsCirculationController")
public class GoodsCirculationController {

    private final static Logger log = LoggerFactory.getLogger(GoodsCirculationController.class);

    @Autowired
    private GoodsCirculationManager circulationManager;


    /**
     * 商品流查询
     *
     * @company
     * @author yichuan
     * @Date 2017/10/19 10:58
     **/
    @ApiOperation(value = "商品流查询")
    @RequestMapping(path = "/queryByCondition", method = RequestMethod.POST)
    public ResponseResult queryByCondition(@RequestBody CirculationConditionQuery circulationConditionQuery,
                                           HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult();
        double startTime = System.currentTimeMillis();
        try {

            responseResult.put("totalPages", circulationManager.queryTotalPageGoodsCirculation(circulationConditionQuery));
            responseResult.put("goodsCirculationList", circulationManager.queryByCondition(circulationConditionQuery));
        } catch (DataException e) {
            responseResult.putException(e);
        } catch (Exception e) {
            responseResult.putException((DataException) e);
        }
        double endTime = System.currentTimeMillis();
        //totalPages
        log.info("circulationConditionQuery :{}", circulationConditionQuery);
        log.info("costTime: {} ms", (endTime - startTime));
        responseResult.put("costTime", (endTime - startTime) + "ms");
        return responseResult;

    }

    /**
     * 末位商品流查询
     *
     * @company
     * @author yichuan
     * @Date 2017/10/19 15:09
     **/
    @ApiOperation(value = "末位商品流查询")
    @RequestMapping(path = "/queryLastGoodsCirculationByCondition", method = RequestMethod.POST)
    public ResponseResult queryLastGoodsCirculationByCondition(@RequestBody CirculationConditionQuery circulationConditionQuery,
                                                               HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult();
        double startTime = System.currentTimeMillis();
        try {

            responseResult.put("totalPages", circulationManager.queryTotalPageLastGoodsCirculation(circulationConditionQuery));
            responseResult.put("lastGoodsCirculationList", circulationManager.queryLastGoodsCirculationByCondition(circulationConditionQuery));
        } catch (DataException e) {
            responseResult.putException(e);
        } catch (Exception e) {
            responseResult.putException((DataException) e);
        }
        double endTime = System.currentTimeMillis();
        //totalPages
        log.info("queryLastGoodsCirculationByCondition :{}", circulationConditionQuery);
        log.info("costTime: {} ms", (endTime - startTime));
        responseResult.put("costTime", (endTime - startTime) + "ms");
        return responseResult;

    }


}
