package com.sungwoo.app.service;

import java.util.List;

import com.sungwoo.app.entity.UserProfile;

public interface UserProfileService {
	 
    UserProfile findById(int id);
 
    UserProfile findByType(String type);
     
    List<UserProfile> findAll();
     
}