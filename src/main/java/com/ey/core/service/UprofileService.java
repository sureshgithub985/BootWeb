package com.ey.core.service;

import java.util.List;

import com.ey.core.entity.UProfile;

public interface UprofileService {

	public void addUserProfile(UProfile uprofile);

	public void updateUserProfile(UProfile uprofile, Integer id);

	public UProfile getUserProfile(Integer id);

	public void deleteUserProfileById(Integer id);

	public List<UProfile> getAllCustomers();

}
