package com.github.charlotte3517.hotelbooking.dao;

import com.github.charlotte3517.hotelbooking.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {

    List<User> getAllUsers();

    User getUserById(@Param("userId") Integer userId);

    void insertUser(@Param("user") User user);

    void updateUser(@Param("user") User user);

    void deleteUser(@Param("userId") Integer userId);
}