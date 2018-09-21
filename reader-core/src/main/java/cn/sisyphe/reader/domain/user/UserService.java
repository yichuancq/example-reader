package cn.sisyphe.reader.domain.user;

import cn.sisyphe.reader.domain.user.model.User;

/**
 * Created by Administrator on 2017/10/20.
 */
public interface UserService {

    User queryUser(String name);

    String queryPwd(String pwd);
}
