package com.sungwoo.app.dao;

import java.util.List;

import com.sungwoo.app.entity.User;

public interface UserDao {
	 
    User findById(int id);
     
    User findBySSO(String sso);
     
    void save(User user);
     
    void deleteBySSO(String sso);
     
    List<User> findAllUsers();
 
}