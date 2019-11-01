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
//�������ע��,����ͨ��modelMap��װsession ������,������ı�ʾ��Щ������session����
//@SessionAttributes("username")
@RequestMapping("user")
public class UserController {
	
	@RequestMapping("reg.do")
	public String showReg(){
		System.out.println("UserController.showReg()");
		// /templates/reg.html
		return "reg";//����ֵ�������Ϊ����Ҫ������html�ļ�������(����ֵ������ͼ��!!)
		
	}
	
	@RequestMapping("login.do")
	public String showLogin(){
		System.out.println("UserController.showLogin()");
		// /templates/reg.html
		return "login";//����ֵ�������Ϊ����Ҫ������html�ļ�������(����ֵ������ͼ��!!)
		
	}
	
	@RequestMapping("index.do")
	public String showIndex(){
		System.out.println("UserController.showIndex()");
		// /templates/reg.html
		return "index";//����ֵ�������Ϊ����Ҫ������html�ļ�������(����ֵ������ͼ��!!)
	}
	
	@RequestMapping("error.do")
	public String showError(){
		System.out.println("UserController.showError()");
		// /templates/reg.html
		return "error";//����ֵ�������Ϊ����Ҫ������html�ļ�������(����ֵ������ͼ��!!)
	}
	
	@RequestMapping("user.do")
	public String showUser(HttpSession session){
		System.out.println("UserController.showUser()");
		/*//���ܷŵ�������LoginInterceptor����
		//�ж��û��Ƿ��Ѿ���¼,���δ��¼,ת����¼ҳ��
		if(session.getAttribute("username")==null){
			//û�е�¼��Ϣ
			System.out.println("\tû�е�¼��Ϣ");
			return "redirect:login.do";
		}
		*/
		// /templates/reg.html
		return "user";//����ֵ�������Ϊ����Ҫ������html�ļ�������(����ֵ������ͼ��!!)
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
		// ����root�û����Ѿ���ע�ᣬ�������ظ�ע�ᣬ�����û�������Ϊע��ɹ���ע��ɹ�����ʾ��¼ҳ��
		if("root".equals(user.getUsername())){
			//�û�����ע����û�����root,�����
			//return "redirect:error.do";//err  ʹ������Ļ����ܴ����ݸ������
			model.addAttribute("msg", "����ע����û����Ѿ�����!");
			return "error";//ok
		}else{
			//�û�����iע����û�������root,��ɹ�
			return "redirect:login.do";//ok
			//return "login";//err  ���ʹ��ת��,���������ַ�ϵĺ�׺����handle_reg.doû��,����ˢ�µ�ʱ���ǻ����ύ֮ǰע�����Ϣ
		}
	}
	//method=RequestMethod.POST������������ʽ,�������ʽ��ƥ��,����405,����ֵ������
	//@RequestParam(name="username")�������ֵ�ǿͻ����ύ����Ϊusername�Ĳ����󶨵�username����,�������ڽ���ͻ����ύ���������
	//��������д�������Ĳ�������һ�µ�����
	@RequestMapping(path="handle_login.do",method=RequestMethod.POST)
	public String handleLogin(ModelMap modelmap,@RequestParam(name="username") String username,String password,HttpSession session){
		System.out.println("UserController.handleLogin()");
		System.out.println(username);
		System.out.println(password);
		
		
		//����root/1234����ȷ���û���������
		//���ж��û����Ƿ���ȷ
		if("root".equals(username)){
			//�û�����ȷ,�ж�����
			if("1234".equals(password)){
				//����Ҳ��ȷ,���¼�ɹ�
				//redirect:  �����Ҫ�ض���,��ʹ�����ǰ׺,����д��ַ
//				modelmap.addAttribute("username", username);
				//���Ҫʹ�ض���(�ڶ�������)Ҳ�ܶ�ȡ��������������������,������session,��Ϊ�ض����ҳ���Ƕ�������,��һ��������������ݻ�ȡ����
				session.setAttribute("username", username);
				return "redirect:user.do";
			}else{//�������
				String errorMessage="��¼ʧ��,�������";
				modelmap.addAttribute("msg", errorMessage);
				return "error";
			}
		}else{
			//�û�������
			String errorMessage="��¼ʧ��,�û�������";
			modelmap.addAttribute("msg", errorMessage);
			return "error";
		}
		
		
	}

	
}
