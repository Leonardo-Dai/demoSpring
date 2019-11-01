package cn.tedu.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ������,��Ҫ�̳�HandlerInterceptor�ӿ�
 * ������������spring.xml������
 * @author nbtarena
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LoginInterceptor.preHandle()");
		
		//�ж��û��Ƿ��Ѿ���¼,���û�е�¼,����ֹ����
		//�ض��򵽵�½ҳ
		if(request.getSession().getAttribute("username")==null){
			//�ض��򵽵�½ҳ
			response.sendRedirect(request.getContextPath()+"/user/login.do");
//			response.sendRedirect("login.do");//����������
			//���û��¼,����ֹ����
			return false;
		}
		//����
		return true;
	}

	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("LoginInterceptor.postHandle()");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("LoginInterceptor.afterCompletion()");
	}


}
