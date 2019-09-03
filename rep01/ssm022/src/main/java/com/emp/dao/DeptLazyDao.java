package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.emp.entity.Dept;


public interface DeptLazyDao {
	//�����ز�ѯ
		@Select(" select deptno,dname,location "
				+ " from t_dept "
				+ " where deptno=#{deptno}")
		@Results(id="deptMapper",value={
				@Result(id=true,column="deptno",property="deptno"),
				@Result(column="dname",property="dname"),
				@Result(column="location",property="location"),
				@Result(column="deptno",property="emps",
				many=@Many(select="com.emp.dao.EmpDao.queryByDeptno",
		         fetchType=FetchType.LAZY))				
		})
		Dept queryLazyDeptno(@Param("deptno")String deptno);
		
		//��ѯ���в���
		@Select(" select deptno,dname,location "
				+ " from t_dept")
		@ResultMap("deptMapper")
		List<Dept> queryLazyDeptnoAll();
		//��
		@Insert(" insert into t_dept values(#{deptno},#{dname},#{location})")
		void addDept(Dept dept);
		//ɾ
		@Delete(" delete from t_dept where deptno=#{deptno}")
		void deleteDept(String deptno);
		//��
		@Update("update t_dept set deptno=#{deptno},dname=#{dname},location=#{location}"
				+ " where deptno=#{deptno}")
		void updateDept(Dept deptno);
}
