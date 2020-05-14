package com.example.reader.infrastructure.user;

import com.example.reader.domain.user.model.User;

/**
 * Created by Administrator on 2017/10/20.
 */
public interface UserRepository {

    /**
     * 用户
     *
     * @param name
     * @return
     */
    User queryUser(String name);


    /**
     * 查询密码
     *
     * @param pwd
     * @return
     */
    String queryPwd(String pwd);


    /**
     * 获取所属站点
     *
     * @return
     */
    User queryBelongsStation();
}
