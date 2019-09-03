package com.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.emp.entity.Dept;
import com.emp.entity.Emp;
import com.emp.service.DeptService;
import com.emp.service.EmpService;
import com.emp.utils.PageBean;

@Controller
public class EmpController {
    //ע��ҵ������
	@Resource
	private EmpService empService;
	@Resource
	private DeptService deptService;
	
	public void loadData(HttpSession session){
		//�Ա�
		Map<String,String> map=new HashMap<String,String>();
		map.put("��","��");
		map.put("Ů","Ů");
		session.setAttribute("map",map);
		//���в�������
		List<Dept>depts=deptService.queryAllDepts();
		session.setAttribute("depts", depts);
		//���о���
		List<Emp> mgrs=empService.queryMgrs();
		session.setAttribute("mgrs", mgrs);
	}
	//��ת��ɾ��
	@RequestMapping(value="/emp/{empno}",method=RequestMethod.DELETE)
	public String deleteEmp(@PathVariable("empno")String empno){
		empService.deleteEmp(empno);
		return "redirect:/emp/conditionList";
	}
	//��ת���޸�ҳ��
	@RequestMapping("/emp/toUpdate")
	public String toUpdate(@ModelAttribute("emp")Emp emp,
			@RequestParam("empno")String empno,Model model,
			HttpSession session){
		loadData(session);//��������
		
		emp=empService.queryEmpById(empno);
		System.out.println("111111111111"+emp);
		model.addAttribute("emp",emp);
		
		return "UpdateEmp";
	}
	//��ת�����ҳ��
	@RequestMapping("/emp/toAdd")
	public String toAddEmp(@ModelAttribute("emp")Emp emp,
			HttpSession session){
		loadData(session);
		return "AddEmp";
	}
	

	
	//�޸�Ա��
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
    public String updateEmp(@ModelAttribute("emp")Emp emp,HttpSession session){
		System.out.println("���뵽updateEmp!!");
		//�����޸���Ϣ�����ݿ���
		empService.updateEmp(emp);
		System.out.println("����ɹ�!");
		System.out.println("sesssssssssssssss"+session);
		PageBean<Emp> pageBean
		=(PageBean<Emp>)session.getAttribute("pageBean");
		String cd=(String)session.getAttribute("cd");
		//����list
		System.out.println("cccccccccccccccddddddddddd"+cd);
		System.out.println("pageBean"+pageBean);
		pageBean=empService.queryByCondition(pageBean.getPageNo(),pageBean.getPageSize(),cd);
		session.setAttribute("pageBean",pageBean);
		
		return "ListEmp";
	}
	/*@RequestMapping("/emp/reList")
	public String toEmpList(){
		return "ListEmp";
	}*/
	//��ҳ��ѯ
	@RequestMapping("/emp/list")
	  public String queryByPage(
	  @RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
	  @RequestParam(value="pageSize",required=false,defaultValue="3") Integer pageSize,
	  Model model){
		      PageBean<Emp> pageBean = empService.queryByPage(pageNo, pageSize);
	          //��pageBean���������������
		      model.addAttribute("pageBean", pageBean);
		      return "ListEmp";
	  }
	
	//���Ա��
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String addEmp(Emp emp){
		//��id��������
		emp.setEmpno(UUID.randomUUID().toString());
		empService.addEmp(emp);
		return "redirect:/emp/conditionList";
	}
	
	//������ҳ��ѯ
	@RequestMapping("/emp/conditionList")
	@RequiresPermissions(value={"emp:query","emp:del"},logical=Logical.AND)
	//@RequiresRoles(value={"��ͨ�û�","����Ա"},logical=Logical.AND)
	public String queryCondition(
		@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
	    @RequestParam(value="pageSize",required=false,defaultValue="3")Integer pageSize,
		@RequestParam(value="cd",required=false,defaultValue="")String cd,
		//Model model,
		HttpSession session
			){
		System.out.println("333333333333");
		   PageBean<Emp> pageBean = empService.queryByCondition(pageNo, pageSize, cd);
		   //��pageBean��cd ������������
		   session.setAttribute("pageBean", pageBean);
		   session.setAttribute("cd", cd);
		return "ListEmp";
	}
}
