package com.study.service;

import com.study.model.User;

/**
 * Created by liqing on 2017/4/11 0011.
 */
public interface UserService {

    /**
     * 根据名字查询用户
     * @param userName
     * @return
     */
    User findByName(String userName);

    /**
     * 保存用户
     * @param userName
     * @param cityName
     * @return
     */
    User saveUser(String userName, String cityName);
}
