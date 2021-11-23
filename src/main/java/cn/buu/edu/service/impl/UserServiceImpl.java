package cn.buu.edu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.buu.edu.bean.User;
import cn.buu.edu.bean.UserExample;
import cn.buu.edu.bean.UserExample.Criteria;
import cn.buu.edu.dao.UserMapper;
import cn.buu.edu.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> getUserList(){
		UserExample example = new UserExample();
		
		Criteria criteria = example.createCriteria();
		
		criteria.andUidGreaterThan(3);
		
		return userMapper.selectByExample(example);
	}

	@Override
	public User getUserInfo(String username) {
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		
		createCriteria.andUsernameEqualTo(username);
		
		List<User> list = userMapper.selectByExample(userExample);
		
		if(list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public User getUserInfoByEmail(String email) {
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		
		createCriteria.andEmailEqualTo(email);
		
		List<User> list = userMapper.selectByExample(userExample);
		
		if(list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public boolean regist(User user) {
		return userMapper.insert(user) == 1;
	}

	@Override
	public User login(User user) {
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		
		createCriteria.andUsernameEqualTo(user.getUsername());
		createCriteria.andPasswordEqualTo(user.getPassword());
		
		List<User> list = userMapper.selectByExample(userExample);
		
		if(list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}

}
