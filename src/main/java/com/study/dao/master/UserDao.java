package com.study.dao.master;

import com.study.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by liqing on 2017/4/11 0011.
 */
public interface UserDao {
    /**
     * 根据名字查找用户
     * @param userName
     * @return
     */
    User findByName(@Param("userName")String userName);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);
}
