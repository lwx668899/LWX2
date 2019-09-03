package com.emp.service;

import java.util.List;

import com.emp.entity.Dept;
import com.emp.entity.Emp;

public interface DeptService {
   //��ѯ���в���
	List<Dept> queryAllDepts();
	//��ѯ����Ա��
	List<Emp> queryAllEmps();
	//����Ա����Ų�ѯԱ��
	Emp queryEmpById(String empno);
	//���Ա��
	void addEmp(Emp emp);
	//�޸�Ա��
	void updateEmp(Emp emp);
	//ɾ��Ա��
	void deleteEmp(String empno);	
}
