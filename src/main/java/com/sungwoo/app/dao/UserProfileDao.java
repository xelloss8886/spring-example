package com.sungwoo.app.dao;

import java.util.List;

import com.sungwoo.app.entity.UserProfile;

public interface UserProfileDao {
	 
    List<UserProfile> findAll();
     
    UserProfile findByType(String type);
     
    UserProfile findById(int id);
}