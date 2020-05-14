package com.example.reader.infrastructure.produc.jdbc;

import com.example.reader.domain.product.enums.SaleStatusValueEnum;
import com.example.reader.viewmodel.ProductDTO;
import com.example.reader.viewmodel.ProductTypeDTO;
import com.example.reader.viewmodel.StockDTO;
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
 * Created by Administrator on 2017/10/16.
 */
@Repository
public class JdbcProductRepository {


    private final static Logger log = LoggerFactory.getLogger(JdbcProductRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * @param hId
     * @return
     */
    public List<StockDTO> queryStocks(String hId) {
        List<StockDTO> stockDTOList = new ArrayList<>();

        // StockDTO stockDTO = new StockDTO();
        StringBuilder sb = new StringBuilder();
        sb.append(" \r\n");
        sb.append(" SELECT db_product.h_id, db_stocks.s_id AS stock_id,  ");
        sb.append(" SUM(db_stocks_amount.h_amount) AS sum_amount,  ");
        sb.append(" db_station_total.station_name, db_stocks.s_attribute ");
        sb.append(" FROM db_product WITH (nolock)  ");
        sb.append(" INNER JOIN db_stocks_amount WITH (nolock) ON db_product.h_id = db_stocks_amount.h_id ");
        sb.append(" INNER JOIN db_stocks WITH (nolock) ON db_stocks_amount.s_id = db_stocks.s_id ");
        sb.append(" INNER JOIN db_station_total WITH (nolock) ON db_stocks.station_id = db_station_total.station_id ");
        sb.append(" WHERE  1=1   ");
        //有库
        sb.append(" AND (db_product.h_id = '" + hId + "')   ");
        sb.append(" GROUP BY db_stocks.s_id, db_product.h_id, db_station_total.station_name, db_stocks.s_attribute, db_product.h_type ");
        //正常库
        sb.append(" HAVING (db_stocks.s_attribute = '正常') ");
        //AND (SUM(db_stocks_amount.h_amount) > 0)
        sb.append(" ORDER BY sum_amount DESC  ");
        sb.append("  ");

        sb.append(" \r\n");
        String sql = sb.toString();
        //log.info("SQL:{}\r\n", sql);
        jdbcTemplate.query(sql, new RowMapper<StockDTO>() {
            @Override
            public StockDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

                StockDTO stockDTO = new StockDTO();
                stockDTO.sethId(rs.getString("h_id"));
                stockDTO.setsId(rs.getString("stock_id"));
                //库存
                stockDTO.setSumAmount(rs.getInt("sum_amount"));
                stockDTO.setStationName(rs.getString("station_name"));
                stockDTO.setStockAttribute(rs.getString("s_attribute"));

                stockDTOList.add(stockDTO);
                return null;
            }
        });
        return stockDTOList;

    }

    /***
     *
     * @param hId
     * @return
     */
    public ProductTypeDTO queryProductType(String hId) {
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();

        StringBuilder sb = new StringBuilder();
        sb.append(" \r\n");
        sb.append(" SELECT a.h_id, a.h_isbn, a.h_name,   ");
        sb.append(" t4.type_name AS typeName_04, t4.type_id AS typeId_04,  ");
        sb.append(" t3.type_name AS typeName_03, t3.type_id AS typeId_03, ");
        sb.append(" t2.type_name AS typeName_02, t2.type_id AS typeId_02, ");
        sb.append(" t1.type_name AS typeName_01, t1.type_id AS typeId_01  ");
        sb.append(" FROM db_product AS a WITH (NOLOCK) LEFT OUTER JOIN ");
        sb.append(" \r\n");
        sb.append(" dz_type AS t4 WITH (NOLOCK) ON a.h_type = t4.h_type LEFT OUTER JOIN  ");
        sb.append(" dz_type AS t3 WITH (NOLOCK) ON t3.h_type = t4.type_id LEFT OUTER JOIN  ");
        sb.append(" dz_type AS t2 WITH (NOLOCK) ON t2.h_type = t3.type_id LEFT OUTER JOIN   ");
        sb.append("  dz_type AS t1 WITH (NOLOCK) ON t1.h_type = t2.type_id ");
        sb.append(" \r\n");
        sb.append(" WHERE (a.h_id = '" + hId + "')  ");
        sb.append(" \r\n");
        String sql = sb.toString();
        //log.info("SQL:{}\r\n", sql);
        jdbcTemplate.query(sql, new RowMapper<StockDTO>() {
            @Override
            public StockDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                productTypeDTO.sethId(rs.getString("h_id"));
                productTypeDTO.setFirstTypeId(rs.getString("typeId_01"));
                productTypeDTO.setFirstTypeName(rs.getString("typeName_01"));
                //
                productTypeDTO.setSecondTypeId(rs.getString("typeId_02"));
                productTypeDTO.setSecondTypeName(rs.getString("typeName_02"));
                //
                productTypeDTO.setThirdTypeId(rs.getString("typeId_03"));
                productTypeDTO.setThirdTypeName(rs.getString("typeName_03"));
                //
                productTypeDTO.setFourthTypeId(rs.getString("typeId_04"));
                productTypeDTO.setFourthTypeName(rs.getString("typeName_04"));
                return null;
            }
        }).get(0);
        return productTypeDTO;

    }


    /**
     * @param sId
     * @param hIsbn
     * @param hName
     * @param hWriter
     * @return
     */
    public int totalPage(String sId, String hIsbn, String hName, String hWriter, Float hOutPutPrice) {
        StringBuilder sb = new StringBuilder();

        sb.append(" select COUNT(*) as  totalPage ");
        sb.append(" \r\n");
        sb.append("  from   db_product a with(nolock) INNER JOIN db_stocks_amount b with(nolock)  ");
        sb.append(" \r\n");
        sb.append("  ON a.h_id = b.h_id  ");
        sb.append(" \r\n");
        sb.append(" WHERE b.s_id = '" + sId + "' ");
        sb.append(" \r\n");
        if (!StringUtils.isEmpty(hIsbn)) {
            //sb.append(" AND(h_isbn = '" + hIsbn + "')  ");
            sb.append(" AND ( h_isbn='" + hIsbn.trim() + "' or h_isbn=dbo.Uf_GetIsbnOrTm ('" + hIsbn.trim() + "')  ) ");
            //sb.append(" AND ( h_isbn like '%" + hIsbn.trim() + "' or h_isbn=dbo.Uf_GetIsbnOrTm ('" + hIsbn.trim() + "')  ) ");
            sb.append(" \r\n");
        }
        if (!StringUtils.isEmpty(hName)) {
            //sb.append(" AND(h_name like '%" + hName + "')  ");
            sb.append(" AND(h_name like '%" + hName.trim() + "%')  ");
            sb.append(" \r\n");
        }
        if (!StringUtils.isEmpty(hWriter)) {
            sb.append(" AND (h_writer like '%" + hWriter + "%')  ");
            sb.append(" \r\n");
        }
        if (hOutPutPrice != null) {
            //价格
            sb.append(" AND (h_output_price='" + hOutPutPrice + "')  ");
            sb.append(" \r\n");
        }
        sb.append(" \r\n");
        String sql = sb.toString();
        //log.info("SQL:{}\r\n", sql);
        //获取总页数
        return jdbcTemplate.queryForObject(sql, Integer.class);


    }


    /**
     * @param pageIndex
     * @param pageSize
     * @param sId
     * @param hIsbn
     * @param hName
     * @param hWriter
     * @return
     */
    public List<ProductDTO> queryProductsByCondition(int pageIndex, int pageSize,
                                                     String sId, String hIsbn, String hName, String hWriter, Float hOutPutPrice) {

        final List<ProductDTO> products = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" \r\n");
        sb.append(" select tt.*,d.at_name from (   ");
        sb.append(" \r\n");
        sb.append(" SELECT  ROW_NUMBER() OVER( order by stock_amount desc) AS row_num,\n ");
        //sb.append(" SELECT  ROW_NUMBER() OVER( order by CURRENT_TIMESTAMP ) AS row_num,\n ");
        // sb.append(" SELECT  top 100  \n ");
        sb.append(" \r\n");
        sb.append("  t.*,t4.type_name tn4,t4.type_id   ti4  FROM (  SELECT ");
        sb.append(" \r\n");
        sb.append("  a.h_id,h_isbn, a.h_type ,h_name, h_output_price,h_writer,stock_amount = b.h_amount,pub_name = dbo.uf_getpubname(pub_id) ");
        sb.append(" \r\n");
        sb.append(" from  db_product a with(nolock) ");
        sb.append(" \r\n");
        sb.append(" INNER JOIN db_stocks_amount b with(nolock) ON a.h_id = b.h_id  ");
        sb.append(" \r\n");

        sb.append(" WHERE b.s_id = '" + sId + "' ");
        sb.append(" \r\n");
        if (!StringUtils.isEmpty(hIsbn)) {
            // 长短书号转换
            sb.append(" AND ( h_isbn='" + hIsbn.trim() + "' or h_isbn=dbo.Uf_GetIsbnOrTm ('" + hIsbn.trim() + "')  ) ");
            // sb.append(" AND(h_isbn = '" + hIsbn + "')  ");
            sb.append(" \r\n");
        }
        if (!StringUtils.isEmpty(hName)) {
            sb.append(" AND(h_name like '%" + hName.trim() + "%')  ");
            //sb.append(" AND(h_name like '%" + hName + "')  ");
            sb.append(" \r\n");
        }
        if (!StringUtils.isEmpty(hWriter)) {
            sb.append(" AND (h_writer like '%" + hWriter + "%')  ");
            //sb.append(" AND (h_writer='" + hWriter + "')  ");
            sb.append(" \r\n");
        }
        if (hOutPutPrice != null) {
            //价格
            sb.append(" AND (h_output_price='" + hOutPutPrice + "')  ");
            sb.append(" \r\n");
        }
        sb.append(" ) t LEFT JOIN dz_type t4 WITH (NOLOCK) ON t.h_type = t4.h_type ) tt ");
        sb.append(" \r\n");
        sb.append(" LEFT JOIN db_product_info c WITH (NOLOCK) ON tt.h_id = c.h_id  ");
        sb.append(" \r\n");
        sb.append(" LEFT JOIN db_product_area d WITH (NOLOCK) ON c.PA_id = d.pa_id  ");
        sb.append(" \r\n");
        sb.append(" where 1=1 ");
        sb.append(" \r\n");
        sb.append(" and row_num BETWEEN  " + pageSize * (pageIndex - 1) + " AND " + pageSize * pageIndex + "  ");
        //
        sb.append(" order by row_num asc ");
        sb.append(" \r\n");

        String sql = sb.toString();

        if (!StringUtils.isEmpty(hName)) {
            log.info("query-hName:{}", hName);
        }
        jdbcTemplate.query(sql, new RowMapper<Void>() {

            @Override
            public Void mapRow(ResultSet rs, int rowNum) throws SQLException {

                ProductDTO product = new ProductDTO();
                //row_num

                product.setRowNum(rs.getInt("row_num"));// rowNumber
                product.sethId(rs.getString("h_id"));
                //
                product.sethIsbn(rs.getString("h_isbn"));
                product.sethName(rs.getString("h_name"));
                //
                product.sethOutPutPrice(rs.getFloat("h_output_price"));
                //作者
                String hWriter = rs.getString("h_writer");//

                product.sethWriter(StringUtils.isEmpty(hWriter) ? "" : hWriter.trim());
                //库存
                product.setStockAmount(rs.getInt("stock_amount"));
                //是否销售
                if (product.getStockAmount() == 0) {
                    product.setSaleStatus(SaleStatusValueEnum.not_sale.name());
                } else {
                    product.setSaleStatus(SaleStatusValueEnum.is_sale.name());
                }
                //区位
                String atName = rs.getString("at_name");//
                product.setAtName(StringUtils.isEmpty(atName) ? "" : atName.trim());
                //
                String pubName = rs.getString("pub_name");//
                product.setPubName(StringUtils.isEmpty(pubName) ? "" : pubName.trim());
                //
                String fourthTypeId = rs.getString("ti4");
                product.setFourthTypeId(StringUtils.isEmpty(fourthTypeId) ? "" : fourthTypeId.trim());
                //四级分类
                String fourthTypeName = rs.getString("tn4");
                //四级分类
                product.setFourthTypeName(StringUtils.isEmpty(fourthTypeName) ? "" : fourthTypeName.trim());

                products.add(product);

                return null;
            }
        });

        return products;

    }
}

