package cn.sisyphe.reader.controller;

import cn.sisyphe.common.ResponseResult;
import cn.sisyphe.common.exception.DataException;
import cn.sisyphe.reader.application.ProductManager;
import cn.sisyphe.reader.viewmodel.ConditionQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/10/15.
 */

@RestController
@Api(description = "图书查询")
@CrossOrigin(origins = "*")
@RequestMapping("/api/reader/productController")
public class ProductController {

    private final static Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductManager dailyReportManager;


    /**
     * @company 西西弗文化传播
     * @author yichuan
     * @Date 2017/10/18 19:02
     **/
    @ApiOperation(value = "拼接SQL图书查询")
    @RequestMapping(path = "/queryByCondition", method = RequestMethod.POST)
    public ResponseResult queryByCondition(@RequestBody ConditionQuery conditionQuery,

                                           HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult();
        double startTime = System.currentTimeMillis();
        try {

            responseResult.put("totalPages", dailyReportManager.getTotalPage(conditionQuery));
            responseResult.put("productList", dailyReportManager.queryByConditionWithSQL(conditionQuery));

        } catch (DataException e) {
            responseResult.putException(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseResult.putException(e);
        }
        double endTime = System.currentTimeMillis();
        log.info("costTime:{} ms", (endTime - startTime));
        responseResult.put("costTime", (endTime - startTime) + "ms");
        return responseResult;

    }


    /**
     * @company 西西弗文化传播
     * @author yichuan
     * @Date 2017/10/18 19:02
     **/
    @ApiOperation(value = "拼接SQL图书查询")
    @RequestMapping(path = "/showDetailsById", method = RequestMethod.POST)
    public ResponseResult showDetailsById(@RequestParam String hId, HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult();
        double startTime = System.currentTimeMillis();
        try {

            responseResult.put("productType", dailyReportManager.queryProductType(hId));
            responseResult.put("stockList", dailyReportManager.queryStockWithDifferStations(hId));

        } catch (Exception e) {
            responseResult.putException(e);
        }
        double endTime = System.currentTimeMillis();
        log.info("hId: {}", hId);
        log.info("costTime :{} ms", (endTime - startTime));
        responseResult.put("costTime", (endTime - startTime) + "ms");
        return responseResult;

    }

}
