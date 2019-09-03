package com.emp.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emp.dao.UserDao;
import com.emp.entity.User;
import com.emp.service.UserService;
@Service
public class UserServiceImpl implements UserService{
    
    @Resource
    private UserDao userDao;
	@Override
	public User queryUser(String username) {
		User user=userDao.getByUserame(username);
		return user;
	}

	@Override
	public Set<String> queryRoles(String username) {
		Set<String> roles=userDao.queryRoles(username);
		return roles;
	}

	@Override
	public Set<String> queryPermissions(String username) {
		Set<String> permissions =userDao.getPermissions(username);
		return permissions;
	}

	@Override
	public void addUser(User user) {
		//����,����
		//MD5���ü����㷨
		//��������
		String password//�㷨 ��Ҫ���� ������  �� ���ܵĴ���
		= new SimpleHash("MD5",user.getPassword(),user.getUsername(),1024).toString();
		//password���Ǽ��ܺ������
		//�û����ܺ�������û�ԭ����ҳ�洫�� ������
		user.setPassword(password);
		//��user���浽���ݿ���
		userDao.save(user);
	}
  
}
