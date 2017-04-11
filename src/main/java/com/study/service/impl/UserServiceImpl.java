package com.study.service.impl;

import com.study.dao.cluster.CityDao;
import com.study.dao.master.UserDao;
import com.study.model.City;
import com.study.model.User;
import com.study.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liqing on 2017/4/11 0011.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CityDao cityDao;

    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        City city = cityDao.findByName("beijing");
        user.setCity(city);
        return user;
    }

    @Transactional
    @Override
    public User saveUser(String userName, String cityName) {
        City city = City.builder().provinceId("010").cityName("beijing").description("beijing").build();
        cityDao.saveCity(city);
        User user = User.builder().userName("tom").description("tom").city(city).build();
        userDao.saveUser(user);
        throw new RuntimeException();
    }

}
