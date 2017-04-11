package com.study.dao.cluster;

import com.study.model.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by liqing on 2017/4/11 0011.
 */
@Mapper
public interface CityDao {

    /**
     * 根据名字查找城市
     * @param cityName
     * @return
     */
    City findByName(@Param("cityName")String cityName);

    void saveCity(City city);
}
