package cn.tedu.spring;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//有了这个注解,可以通过modelMap封装session 的数据,括号里的表示哪些数据是session数据
//@SessionAttributes("username")
@RequestMapping("user")
public class UserController {
	
	@RequestMapping("reg.do")
	public String showReg(){
		System.out.println("UserController.showReg()");
		// /templates/reg.html
		return "reg";//返回值可以理解为即将要创建的html文件的名称(返回值就是视图名!!)
		
	}
	
	@RequestMapping("login.do")
	public String showLogin(){
		System.out.println("UserController.showLogin()");
		// /templates/reg.html
		return "login";//返回值可以理解为即将要创建的html文件的名称(返回值就是视图名!!)
		
	}
	
	@RequestMapping("index.do")
	public String showIndex(){
		System.out.println("UserController.showIndex()");
		// /templates/reg.html
		return "index";//返回值可以理解为即将要创建的html文件的名称(返回值就是视图名!!)
	}
	
	@RequestMapping("error.do")
	public String showError(){
		System.out.println("UserController.showError()");
		// /templates/reg.html
		return "error";//返回值可以理解为即将要创建的html文件的名称(返回值就是视图名!!)
	}
	
	@RequestMapping("user.do")
	public String showUser(HttpSession session){
		System.out.println("UserController.showUser()");
		/*//功能放到拦截器LoginInterceptor中了
		//判断用户是否已经登录,如果未登录,转到登录页面
		if(session.getAttribute("username")==null){
			//没有登录信息
			System.out.println("\t没有登录信息");
			return "redirect:login.do";
		}
		*/
		// /templates/reg.html
		return "user";//返回值可以理解为即将要创建的html文件的名称(返回值就是视图名!!)
	}
	
	
	@RequestMapping(path="handle_reg.do")
	public String handleReg(User user,ModelMap model){
		System.out.println("UserController.handleReg()");
		System.out.println(user);
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getAge());
		System.out.println(user.getPhone());
		System.out.println(user.getEmail());
		// 假设root用户名已经被注册，不允许重复注册，其它用户名均视为注册成功，注册成功后，显示登录页面
		if("root".equals(user.getUsername())){
			//用户尝试注册的用户名是root,则错误
			//return "redirect:error.do";//err  使用这个的话不能传数据给浏览器
			model.addAttribute("msg", "尝试注册的用户名已经存在!");
			return "error";//ok
		}else{
			//用户尝试i注册的用户名不是root,则成功
			return "redirect:login.do";//ok
			//return "login";//err  如果使用转发,则浏览器地址上的后缀还是handle_reg.do没变,导致刷新的时候还是会再提交之前注册的信息
		}
	}
	//method=RequestMethod.POST用来限制请求方式,如果请求方式不匹配,出现405,属性值是数组
	//@RequestParam(name="username")代表这个值是客户端提交的名为username的参数绑定到username变量,可以用于解决客户端提交的请求参数
	//与控制器中处理请求的参数名不一致的问题
	@RequestMapping(path="handle_login.do",method=RequestMethod.POST)
	public String handleLogin(ModelMap modelmap,@RequestParam(name="username") String username,String password,HttpSession session){
		System.out.println("UserController.handleLogin()");
		System.out.println(username);
		System.out.println(password);
		
		
		//假设root/1234是正确的用户名和密码
		//先判断用户名是否正确
		if("root".equals(username)){
			//用户名正确,判断密码
			if("1234".equals(password)){
				//密码也正确,则登录成功
				//redirect:  如果需要重定向,则使用这个前缀,后面写地址
//				modelmap.addAttribute("username", username);
				//如果要使重定向(第二次连接)也能读取到服务器传过来的数据,就是用session,因为重定向的页面是二次请求,第一次请求带来的数据获取不到
				session.setAttribute("username", username);
				return "redirect:user.do";
			}else{//密码错误
				String errorMessage="登录失败,密码错误";
				modelmap.addAttribute("msg", errorMessage);
				return "error";
			}
		}else{
			//用户名错误
			String errorMessage="登录失败,用户名错误";
			modelmap.addAttribute("msg", errorMessage);
			return "error";
		}
		
		
	}

	
}
