package com.study.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.study.model.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static Map<Long, User> users = Maps.newHashMap();

    @ApiOperation(value = "查询所有用户")
    @GetMapping(value =  "getAll")
    public List<User> getAll(){
        return Lists.newArrayList(users.values());
    }

    @ApiOperation(value = "增加一个用户")
    @ApiImplicitParam(name = "user", value = "用户信息实体", required = true, dataType = "User")
    @PostMapping(value = "add")
    public String addUser(User user){
        users.put(user.getId(), user);
        return "success";
    }

}
