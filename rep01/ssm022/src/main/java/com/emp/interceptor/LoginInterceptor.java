package com.emp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//��¼������
/**
 * preHandle��ҵ��������������֮ǰ������;
postHandle��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ��;
afterCompletion��DispatcherServlet��ȫ����������󱻵���,
������������Դ�� ��afterCompletion()ִ����ɺ�ʼ��Ⱦҳ��
 * @author tom
 *preHandle-->/emp/conditonList-->postHandle-->����ListEmp.jsp(html)
 * -->afterCompletion --->��������ش�html
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * return true ������ ���� ����ҵ����� ����/emp/ConditionList
	 * return false ����,������ҵ�����
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object user=request.getSession().getAttribute("user");
		if(user!=null){
			return true;
		}
		//ת����Login.jsp
		//�󶨴�����Ϣ
		request.setAttribute("msg", "���ȵ�¼");
		String appName=request.getServletContext().getContextPath();
		request.getRequestDispatcher("/user/toLogin")
		.forward(request, response);
		return false;
	}

}
