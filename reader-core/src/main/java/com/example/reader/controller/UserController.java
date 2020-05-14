package com.example.reader.controller;

import com.example.reader.application.UserManager;
import com.example.reader.domain.user.model.User;
import com.example.reader.utils.ClientOSUtil;
import com.example.reader.viewmodel.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/10/20
 */

@Api(description = "用户登录")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/reader/UserController")
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private UserManager userManager;

    @ApiOperation(value = "用户登录")
    @RequestMapping(path = "/userLogin", method = RequestMethod.POST)
    public ResponseResult userLogin(@RequestBody User user,
                                    HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult();

        // 获取客户端操作系统类型
        String userAgent = ClientOSUtil.getClientOS(request.getHeader("user-agent"));
        request.getSession().setAttribute("userAgent", userAgent);

        User currentUser = userManager.checkUser(user);//

        responseResult.put("user", currentUser);
        request.getSession().setAttribute("user", currentUser);
        // 把客户端的操作系统类型写入响应结果
        log.info("conditionQuery :{}", user);
        return responseResult;

    }
}
