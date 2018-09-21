package cn.sisyphe.reader.domain.user;

import cn.sisyphe.common.ddd.domain.annotations.DomainService;
import cn.sisyphe.reader.domain.user.model.User;
import cn.sisyphe.reader.infrastructure.user.UserRepository;
import cn.sisyphe.reader.utils.YHPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/10/20.
 */
@Service
@DomainService
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    /**
     * 用户查询
     *
     * @param name
     * @return
     */
    @Override
    public User queryUser(String name) {
        if (StringUtils.isEmpty(name)) {
            //没有用户只查询归属站点
            return userRepository.queryBelongsStation();
        }
        return userRepository.queryUser(name);


    }


    /**
     * 查询密码
     *
     * @param pwd
     * @return
     */
    @Override
    public String queryPwd(String pwd) {
        return YHPasswordUtil.decryptionForOperator(userRepository.queryPwd(pwd));
    }
}
