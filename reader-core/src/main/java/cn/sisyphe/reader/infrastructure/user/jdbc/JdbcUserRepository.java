package cn.sisyphe.reader.infrastructure.user.jdbc;

import cn.sisyphe.reader.domain.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/10/20.
 */
@Repository
public class JdbcUserRepository {


    private final static Logger log = LoggerFactory.getLogger(JdbcUserRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 查询员工密码
     *
     * @param operatorId
     * @return
     */
    public String queryUserPassWord(String operatorId) {

        String sql = String.format("SELECT Max(passwd) FROM sys_OperatorRight Where o_id ='%1$s'", operatorId);
        String strPassword = jdbcTemplate.queryForObject(sql, String.class);
        return strPassword == null ? "" : strPassword;

    }

    /**
     * 没有用户名的情况下查询归属站点
     *
     * @return
     */
    public User queryBelongsStation() {
        User user = new User();

        StringBuilder sb = new StringBuilder();

        sb.append(" select top 1 s.station_id,s.station_name ");
        sb.append(" from  db_station_total as s ");
        sb.append(" where 1=1  ");
        sb.append(" and s.local_flag='1' ");
        sb.append(" ");
        String sql = sb.toString();

        //log.info("SQL:{}\r\n", sql);
        //获取
        jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                user.setStationId(rs.getString("station_id"));
                user.setStationName(rs.getString("station_name"));
                user.setLegal(false);
                return user;
            }
        });
        return user;
    }


    /**
     * 查询当前站点的用户
     *
     * @param name
     * @return
     */
    public User queryUser(String name) {

        User user = new User();

        StringBuilder sb = new StringBuilder();
        sb.append(" select top 1 o.o_name,o.o_id,o.station_id,s.station_name ");
        sb.append(" from  db_operators as  o, db_station_total as s ");
        sb.append(" where o.station_id=s.station_id and o.o_exist='1'   ");
        sb.append(" and s.local_flag='1' ");
        sb.append(" and o.o_name='" + name + "' ");
        String sql = sb.toString();
        //log.info("SQL:{}\r\n", sql);
        //获取
        jdbcTemplate.query(sql, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {

                user.setUserId(rs.getString("o_id"));
                user.setUserName(rs.getString("o_name"));// rowNumber
                user.setStationId(rs.getString("station_id"));
                user.setStationName(rs.getString("station_name"));

                user.setLegal(false);

                return user;
            }
        });
        return user;
    }
}
