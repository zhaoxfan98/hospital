package cn.buu.edu.service;

import java.util.List;
import java.util.Map;

import cn.buu.edu.bean.User;

public interface UserService {
	public List<User> getUserList();
	public User getUserInfo(String username);
	public User getUserInfoByEmail(String email);
	public boolean regist(User user);
	public User login(User user);
}
