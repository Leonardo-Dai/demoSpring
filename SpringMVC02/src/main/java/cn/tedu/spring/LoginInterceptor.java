package cn.tedu.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器,需要继承HandlerInterceptor接口
 * 拦截器必须在spring.xml加配置
 * @author nbtarena
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LoginInterceptor.preHandle()");
		
		//判断用户是否已经登录,如果没有登录,则阻止运行
		//重定向到登陆页
		if(request.getSession().getAttribute("username")==null){
			//重定向到登陆页
			response.sendRedirect(request.getContextPath()+"/user/login.do");
//			response.sendRedirect("login.do");//两个都可以
			//入伙没登录,则阻止运行
			return false;
		}
		//放行
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
