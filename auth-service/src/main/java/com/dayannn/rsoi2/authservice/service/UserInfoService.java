package com.dayannn.RSOI2.authservice.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dayannn.RSOI2.authservice.model.UserInfo;
import com.dayannn.RSOI2.authservice.repo.UserDetailsRepository;

@Repository
@Transactional
public class UserInfoService {

	@Autowired
	private UserDetailsRepository userDatailsRepository;

	public UserInfo getUserInfoByUserName(String userName) {
		short enabled = 1;
		return userDatailsRepository.findByUserNameAndEnabled(userName, enabled);
	}

	public ResponseEntity getUserIdByUserName(String userName) {
	    try {
            short enabled = 1;
            UserInfo user = userDatailsRepository.findByUserNameAndEnabled(userName, enabled);
            JSONObject userToSave = new JSONObject();
            userToSave.put("user_id", user.getId());

            return ResponseEntity.status(HttpStatus.SC_OK).body(userToSave.toString());
        } catch (JSONException e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error parsing json");
        }
    }

	public List<UserInfo> getAllActiveUserInfo() {
		return userDatailsRepository.findAllByEnabled((short) 1);
	}

	public UserInfo getUserInfoById(Integer id) {
		return userDatailsRepository.findById(id);
	}

	public UserInfo addUser(UserInfo userInfo) {
		userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
		return userDatailsRepository.save(userInfo);
	}

	public UserInfo updateUser(UserInfo userInfo) {
		return userDatailsRepository.save(userInfo);
	}

	public void deleteUser(Integer id) {
		userDatailsRepository.deleteById(id);
	}
}