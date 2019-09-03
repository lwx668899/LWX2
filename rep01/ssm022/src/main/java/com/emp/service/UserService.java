package com.emp.service;

import java.util.Set;

import com.emp.entity.User;

public interface UserService {
  //�û�����ѯ�û�
  User queryUser(String username);
  //����û�(ע��ʹ��)
  void addUser(User user);
  //�û������Ҹ��û����еĽ�ɫ
  Set<String> queryRoles(String username);
  //�û������Ҹ��û����е�Ȩ��
  Set<String> queryPermissions(String username);
}
