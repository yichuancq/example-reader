package com.example.reader.infrastructure.user;

import com.example.reader.domain.user.model.User;
import com.example.reader.infrastructure.user.jdbc.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/20.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {


    @Autowired
    private JdbcUserRepository jdbcUserRepository;

    /**
     * @param name
     * @return
     */
    @Override
    public User queryUser(String name) {
        return jdbcUserRepository.queryUser(name);
    }

    /**
     * 查询密码
     *
     * @param pwd
     * @return
     */
    @Override
    public String queryPwd(String pwd) {
        return jdbcUserRepository.queryUserPassWord(pwd);
    }


    /**
     * 归属站点
     * @return
     */
    @Override
    public User queryBelongsStation() {
        return jdbcUserRepository.queryBelongsStation();
    }
}
