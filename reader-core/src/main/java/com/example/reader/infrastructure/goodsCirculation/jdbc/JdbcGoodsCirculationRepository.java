package com.example.reader.infrastructure.goodsCirculation.jdbc;

import com.example.reader.domain.goodsCirculation.model.GoodsCirculation;
import com.example.reader.domain.goodsCirculation.model.LastGoodsCirculation;
import com.example.reader.viewmodel.CirculationConditionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 末位商品
 */
@Repository
public class JdbcGoodsCirculationRepository {

    private final static Logger log = LoggerFactory.getLogger(JdbcGoodsCirculationRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 末位商品总记录数查询
     *
     * @param circulationConditionQuery
     * @return
     */
    public Integer queryTotalPageLastGoodsCirculation(CirculationConditionQuery circulationConditionQuery) {

        StringBuilder sb = new StringBuilder();
        sb.append(" \r\n");
        sb.append(" SELECT COUNT(*) as total_count ");
        sb.append(" FROM  dz_type AS dz_type_1  ");
        sb.append(" RIGHT OUTER JOIN db_product_area  AS C ON dz_type_1.h_type = C.pro_type_real ");
        sb.append(" RIGHT OUTER JOIN dz_type INNER JOIN db_product_CF_info AS A ");
        sb.append(" INNER JOIN db_product AS B ON A.h_id = B.h_id ON dz_type.h_type = B.h_type ON C.pa_id = A.from_area ");
        sb.append(" WHERE (1 = 1)  ");
        sb.append(" \r\n");
        //name
        if (!StringUtils.isEmpty(circulationConditionQuery.gethName())) {
            sb.append(" AND  (a.h_name LIKE '%" + circulationConditionQuery.gethName() + "%') ");
        }
        //isbn
        if (!StringUtils.isEmpty(circulationConditionQuery.gethIsbn())) {
            sb.append(" AND  (a.h_isbn = '" + circulationConditionQuery.gethIsbn() + "') ");
        }
        //时间不为空
        if (!StringUtils.isEmpty(circulationConditionQuery.getStartTime()) && !StringUtils.isEmpty(circulationConditionQuery.getEndTime())) {
            sb.append(" AND  (a.cf_datetime  between convert(datetime,'" + circulationConditionQuery.getStartTime() + "') " +
                    "  AND convert(datetime,'" + circulationConditionQuery.getEndTime() + "'))");
        }
        String sql = sb.toString();
        log.info("SQL:{}\r\n", sql);
        //获取总页数
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


    /**
     * 末位商品查询
     *
     * @param circulationConditionQuery
     * @return
     */
    public List<LastGoodsCirculation> queryLastGoodsCirculationByCondition(CirculationConditionQuery circulationConditionQuery) {

        int pageIndex = circulationConditionQuery.getCurrentPage();
        int pageSize = circulationConditionQuery.getPageSize();

        final List<LastGoodsCirculation> lastGoodsCirculations = new ArrayList<>();
        StringBuilder sb = new StringBuilder();


        sb.append(" \r\n");
        sb.append(" select  *from (  SELECT ROW_NUMBER() OVER( order by CURRENT_TIMESTAMP) AS row_num,  A.h_id, B.h_output_price, C.at_name AS shelId, A.h_isbn," +
                " A.h_type, A.h_type_real, A.h_name, A.total_amount, A.stock_h_amount, A.cf_datetime, A.from_area,  ");
        sb.append(" A.to_area, A.memo, A.station_id, C.pa_name, dz_type.type_name, dz_type_1.type_name AS type_name_real ");
        sb.append(" \r\n");
        sb.append(" FROM  dz_type AS dz_type_1  ");
        sb.append(" RIGHT OUTER JOIN db_product_area  AS C ON dz_type_1.h_type = C.pro_type_real ");
        sb.append(" RIGHT OUTER JOIN dz_type INNER JOIN db_product_CF_info AS A ");
        sb.append(" INNER JOIN db_product AS B ON A.h_id = B.h_id ON dz_type.h_type = B.h_type ON C.pa_id = A.from_area ");
        sb.append(" WHERE (1 = 1)  ");
        sb.append(" \r\n");
        //name
        if (!StringUtils.isEmpty(circulationConditionQuery.gethName())) {
            sb.append(" AND  (a.h_name LIKE '%" + circulationConditionQuery.gethName() + "%') ");
        }
        //isbn
        if (!StringUtils.isEmpty(circulationConditionQuery.gethIsbn())) {
            sb.append(" AND  (a.h_isbn = '" + circulationConditionQuery.gethIsbn() + "') ");
        }
        //时间不为空
        if (!StringUtils.isEmpty(circulationConditionQuery.getStartTime()) && !StringUtils.isEmpty(circulationConditionQuery.getEndTime())) {
            sb.append(" AND  (a.cf_datetime  between convert(datetime,'" + circulationConditionQuery.getStartTime() + "') " +
                    "  AND convert(datetime,'" + circulationConditionQuery.getEndTime() + "'))");
        }
        sb.append(" ) t where 1=1 ");
        sb.append(" and row_num BETWEEN  " + pageSize * (pageIndex - 1) + " AND " + pageSize * pageIndex + "  ");
        sb.append(" order by row_num asc ");
        /// sb.append(" ORDER BY a.sale_date");
        String sql = sb.toString();
        log.info("SQL:{}\r\n", sql);
        jdbcTemplate.query(sql, new RowMapper<Void>() {
            @Override
            public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
                LastGoodsCirculation temp = new LastGoodsCirculation();
                temp.setRowNum(rs.getInt("row_num"));// row_num
                temp.sethId(rs.getString("h_id"));
                temp.sethIsbn(rs.getString("h_isbn"));
                temp.sethName(rs.getString("h_name"));
                temp.setOutPutPrice(rs.getFloat("h_output_price"));// 价格
                //
                temp.sethType(rs.getString("h_type"));
                temp.sethTypeReal(rs.getString("h_type_real"));// 最终分类
                //
                temp.setPaName(rs.getString("pa_name"));
                temp.setPaIdForm(rs.getString("from_area"));//进入
                temp.setPaIdTo(rs.getString("to_area"));//到的区位
                //
                temp.setTotalAmount(rs.getInt("total_amount"));
                temp.setStockHAmount(rs.getInt("stock_h_amount"));
                //
                String typeName = rs.getString("type_name");// 获取分类名称
                temp.setTypeName(StringUtils.isEmpty(typeName) ? "" : typeName.trim());
                String typeNameReal = rs.getString("type_name_real");// 获取分类名称
                temp.sethTypeRealName(StringUtils.isEmpty(typeNameReal) ? "" : typeNameReal.trim());
                //
                temp.setPaNameFrom(rs.getString("from_area"));// 设置进入分区名称
                temp.setPaNameTo(rs.getString("to_area"));// 设置到达分区名称
                //
                temp.setStationId(rs.getString("station_id"));
                temp.setCfDateTime(rs.getDate("cf_datetime"));// 操作时间
                String shelfId = rs.getString("shelId");// 架號
                temp.setShelfId(StringUtils.isEmpty(shelfId) ? "" : shelfId.trim());

                temp.setMemo(rs.getString("memo"));

                lastGoodsCirculations.add(temp);// /

                return null;
            }
        });

        return lastGoodsCirculations;
    }


    /**
     * 获取商品流转的总页码
     *
     * @param circulationConditionQuery
     * @return
     */
    public Integer queryTotalPageGoodsCirculation(CirculationConditionQuery circulationConditionQuery) {
        StringBuilder sb = new StringBuilder();
        sb.append(" \r\n");
        sb.append(" select COUNT(*) as  totalPage ");
        sb.append(" \r\n");
        sb.append(" FROM  db_product_area  AS c RIGHT OUTER JOIN   dz_type AS dz_type_1   RIGHT OUTER JOIN  ");
        sb.append(" db_product_info AS a  INNER JOIN ");
        sb.append(" dz_type INNER JOIN ");
        sb.append(" db_product AS b with(nolock) ON dz_type.h_type = b.h_type ");
        sb.append(" ON a.h_id = b.h_id ON   ");
        sb.append(" dz_type_1.h_type = a.h_type ON c.pa_id = a.PA_id LEFT OUTER JOIN ");
        sb.append(" db_area_type AS d ON c.at_id = d.at_id ");
        sb.append(" \r\n");
        sb.append(" WHERE  1=1 ");
        sb.append(" \r\n");
        if (!StringUtils.isEmpty(circulationConditionQuery.gethName())) {
            sb.append(" AND  (b.h_name LIKE '%" + circulationConditionQuery.gethName() + "%') ");
        }
        //isbn
        if (!StringUtils.isEmpty(circulationConditionQuery.gethIsbn())) {
            sb.append(" AND  (b.h_isbn = '" + circulationConditionQuery.gethIsbn() + "') ");
        }
        //区位
        if (!StringUtils.isEmpty(circulationConditionQuery.getAtName())) {
            sb.append(" AND  (d.at_name LIKE '%" + circulationConditionQuery.getAtName() + "%') ");
        }
        sb.append(" ");
        String sql = sb.toString();
        log.info("SQL:{}\r\n", sql);
        //获取总页数
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


    /**
     * 商品流转
     *
     * @param circulationConditionQuery
     * @return
     */
    public List<GoodsCirculation> queryByCondition(CirculationConditionQuery circulationConditionQuery) {

        final List<GoodsCirculation> goodsCirculationList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int pageIndex = circulationConditionQuery.getCurrentPage();
        int pageSize = circulationConditionQuery.getPageSize();
        sb.append("\r\n");
        sb.append(" select  *from (  SELECT ROW_NUMBER() OVER( order by CURRENT_TIMESTAMP) AS row_num, " +
                "   b.h_name, b.h_output_price,c.pa_name, d.at_name,  ");
        sb.append(" a.h_id, a.h_type, dz_type_1.type_name as type_name, a.h_type_real,dz_type.type_name as type_name_real,");
        sb.append(" a.h_isbn, a.station_id,  a.PA_id, a.In_time, a.h_input_date, a.sale_amount, a.last_total_amount,  ");
        sb.append(" a.total_amount, a.stock_h_amount, a.is_shelves, a.is_sale_off, a.sale_date, a.last_mod_date,  ");
        sb.append(" a.memo, a.pa_id_old ");
        sb.append(" \r\n");
        sb.append(" FROM db_product_area  AS c RIGHT OUTER JOIN   ");
        sb.append(" dz_type AS dz_type_1  RIGHT OUTER JOIN ");
        sb.append(" db_product_info AS a  INNER JOIN  dz_type INNER JOIN");
        sb.append(" db_product AS b with(nolock) ON dz_type.h_type = b.h_type ON a.h_id = b.h_id ON ");
        sb.append(" dz_type_1.h_type = a.h_type ON c.pa_id = a.PA_id LEFT OUTER JOIN ");
        sb.append(" db_area_type AS d ON c.at_id = d.at_id  ");
        sb.append(" \r\n");
        sb.append(" WHERE 1=1  ");
        sb.append(" \r\n");
        // name
        if (!StringUtils.isEmpty(circulationConditionQuery.gethName())) {
            sb.append(" AND  (b.h_name LIKE '%" + circulationConditionQuery.gethName() + "%') ");
        }
        //isbn
        if (!StringUtils.isEmpty(circulationConditionQuery.gethIsbn())) {
            sb.append(" AND  (b.h_isbn = '" + circulationConditionQuery.gethIsbn() + "') ");
        }
        //区位
        if (!StringUtils.isEmpty(circulationConditionQuery.getAtName())) {
            sb.append(" AND  (d.at_name LIKE '%" + circulationConditionQuery.getAtName() + "%') ");
        }
        sb.append(" ) t where 1=1 ");
        sb.append(" and row_num BETWEEN  " + pageSize * (pageIndex - 1) + " AND " + pageSize * pageIndex + "  ");
        sb.append(" order by row_num asc ");
        /// sb.append(" ORDER BY a.sale_date");
        sb.append(" ");
        String sql = sb.toString();
        log.info("SQL:{}\r\n", sql);
        jdbcTemplate.query(sql, new RowMapper<Void>() {
            @Override
            public Void mapRow(ResultSet rs, int rowNum) throws SQLException {

                GoodsCirculation temp = new GoodsCirculation();
                temp.setRowNum(rs.getInt("row_num"));// row_num
                temp.setSaleAmount(rs.getInt("sale_amount"));// 销售数量
                temp.setTotalAmount(rs.getInt("total_amount"));// 到货数量
                temp.setLastTotalAmount(rs.getInt("last_total_amount"));// 上次库存数量
                temp.setStockHAmount(rs.getInt("stock_h_amount"));// 库存
                if (rs.getString("is_shelves").trim().equals("N")) {
                    temp.setIsShelves("否");// 是否下架
                } else {
                    temp.setIsShelves("是");// 是否下架
                }
                if (rs.getString("is_sale_off").trim().equals("N")) {
                    temp.setIsSaleOff("否");// 是否售完
                } else {
                    temp.setIsSaleOff("是");// 是否售完
                }
                temp.setIsSaleOff(rs.getString("is_sale_off"));//
                temp.setInTime(rs.getDate("In_time"));// 进入时间
                temp.sethInputDate(rs.getDate("h_input_date"));// 建档时间
                temp.sethId(rs.getString("h_id"));
                temp.sethIsbn(rs.getString("h_isbn"));
                temp.sethName(rs.getString("h_name"));//
                // h_output_price
                temp.setOutPutPrice(rs.getFloat("h_output_price"));// 价格

                temp.setPaId(rs.getString("pa_id"));// 区位编码
                temp.setPaName(rs.getString("pa_name"));// 区名
                temp.setAtName(rs.getString("at_name")); //  区位描述(架号)
                temp.sethType(rs.getString("h_type"));
                temp.sethTypeName(rs.getString("type_name"));//获取分类名称
                //
                temp.sethTypeReal(rs.getString("h_type_real"));// 最终分类
                String typeNameReal = rs.getString("type_name_real");// 最终分类
                temp.sethTypeRealName(StringUtils.isEmpty(typeNameReal) ? "" : typeNameReal.trim());
                temp.setStationId(rs.getString("station_id"));
                temp.setLastModDate(rs.getDate("last_mod_date"));// 最后修改时间
                temp.setSaleDate(rs.getDate("sale_date"));// 销售日期
                temp.setMemo(rs.getString("memo"));
                goodsCirculationList.add(temp);
                return null;
            }
        });
        return goodsCirculationList;

    }

}
