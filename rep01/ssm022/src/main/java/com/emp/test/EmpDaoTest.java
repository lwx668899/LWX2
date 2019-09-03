package com.emp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.EmpDao;
import com.emp.dao.EmpLazyDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})  
public class EmpDaoTest {
	@Resource
   private EmpDao empDao;
	@Test
	public void testQueryAll() {
	       List<Emp> emps = empDao.queryAll();
	       for(Emp e:emps){
	    	    System.out.println(e.getEname()+","+e.getDept().getDname()
	    	    		+","+e.getMgr().getEname());
	       }
	}
	@Test
	public void testQueryById(){
	     Emp emps = empDao.queryById("e001");
	     System.out.println();

	}
	@Test
	public void testQueryLike(){
	     List<Emp> es = empDao.queryLikeName("��");
	     System.out.println(es);

	}
	@Test
	public void testAddEmp(){
	     Emp e=new Emp();
	     e.setEmpno("e668");
	     e.setEname("eric");
	     e.setEsex("��");
	     e.setEage(60);
	     e.setEsalary(33333F);
	     //�������Ŷ���
	     Dept dept=new Dept();
	     dept.setDeptno("d001");
	     e.setDept(dept);
	     //�����������
	     Emp mgr=new Emp();
	     mgr.setEmpno("e001");
	     e.setMgr(mgr);
	     //e���󱣴浽���ݿ���
	     empDao.addEmp(e);
	     System.out.println("�Ѳ���");

	}
	@Test//ɾ��
	public void testDelete(){
	     empDao.deleteEmp("e668");
	     System.out.println("��ɾ��!");

	}
	@Test//�޸�
	public void testUpdate(){
		Emp es=empDao.queryById("da282a08-6163-4d2a-a3de-4639baf572c4");
        System.out.println(es);
		es.setEsalary(600000000F);
		es.getDept().setDeptno("d002");
		es.getMgr().setEmpno("e004");
		empDao.updateEmp(es);
		System.out.println(es);
	}
	private void setEmpno(String string) {
		// TODO Auto-generated method stub
		
	}
	@Test//���ݲ��ű�Ų�ѯ�����µ����е�Ա��
	public void testByDeptno(){
		List<Emp> emp=empDao.queryByDeptno("d001");
		System.out.println(emp);
		 for(Emp e:emp){
	    	    System.out.println(e.getEname()+","+e.getDept().getDname()
	    	    		+","+e.getDept().getDeptno());
	       }
	}
	
	@Test//�龭��
	public void testQueryMgr(){
		List<Emp> mgrs=empDao.queryMgrs();
		for(Emp e:mgrs){
    	    System.out.println(e);
       }
	}
	//�����ز�ѯ
	@Resource
	private EmpLazyDao empLazyDao;
	@Test//���������ݱ�Ų�ѯ
	public void testLazy(){
		Emp e=empLazyDao.queryById("e002");
		System.out.println(e.getEname());
		System.out.println("~~~~~~~~~~~~~");
		System.out.println(e.getDept().getDeptno());
       }
	@Test//�����ز�ȫ��
	public void testLazyQueryAll(){
		List<Emp> list=empLazyDao.queryAll();
		for(Emp e:list){
			if(e.getDept()!=null)
			System.out.println(e.getEname()+","+e.getDept().getDname());
		}
       }
	@Test//������������ѯ
	public void testLazyLike(){
		List<Emp> list=empLazyDao.queryLikeName("��");
		for(Emp e:list){
			if(e.getDept()!=null)
			System.out.println(e.getEname()+","+e.getDept().getDname());
		}
       }
	@Test//�����ز�ѯ����������Ա��
	public void testLazyDept(){
		List<Emp> list=empLazyDao.queryByDeptno("d001");
		for(Emp e:list){
			System.out.println(e.getEname());
		}
       }
	@Test//�����ز�ѯ����
	public void testLazyMgrs(){
		
       }
	
	
	}

