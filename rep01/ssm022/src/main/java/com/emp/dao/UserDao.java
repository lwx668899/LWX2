package com.emp.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.emp.entity.User;

public interface UserDao {
  /*�û�����ѯ�û�*/
	@Select("select id,username,password"
			+ " from sh_user"
			+ " where username=#{username}")
  public User getByUserame(@Param("username")String username);
	
	//����û�
	@Insert("insert into sh_user(username,password)values(#{username},#{password})")
	void save(User user);
  /*�û������Ҹ��û����еĽ�ɫ*/
	@Select("select r.rolename"
			+ " from sh_user u inner join sh_user_role ur"
			+ " on ur.user_id=u.id"
			+ " inner join sh_role r"
			+ " on ur.role_id=r.id"
			+ " where u.username=#{username}")
   Set<String> queryRoles(@Param("username")String username);
  /*�û������Ҹ��û����е�Ȩ��*/
	@Select("select distinct p.permission_name"
			+ " from sh_user u inner join sh_user_role ur "
			+ " on ur.user_id=u.id "
			+ " inner join sh_role r "
			+ " on ur.role_id=r.id "
			+ " inner join sh_role_permission rp "
			+ " on rp.role_id=r.id "
			+ " inner join sh_permission p "
			+ " on rp.permission_id=p.id "
			+ " where u.username=#{username}")
  public Set<String> getPermissions(@Param("username")String username);
}
