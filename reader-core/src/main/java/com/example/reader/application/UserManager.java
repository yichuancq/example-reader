package com.example.reader.application;

import com.example.reader.domain.user.UserService;
import com.example.reader.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/10/20.
 */

@Service
public class UserManager {

    @Autowired
    private UserService userService;


    /**
     * @param user
     * @return
     */
    public User checkUser(User user) {
        //
        User dbUser = userService.queryUser(user.getUserName());

        String newPwd = userService.queryPwd(dbUser.getUserId());//
        //
        System.out.println("解密用户数据库的密码:" + newPwd);
        //
        if (user == null || StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(user.getUserPwd())) {
            dbUser.setLegal(false);
        } else {
            if (newPwd.equals(user.getUserPwd())) {
                dbUser.setLegal(true);
            }
            //输入的字符密码
            dbUser.setUserPwd(user.getUserPwd());
        }
        return dbUser;
    }


}
