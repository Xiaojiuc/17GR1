package com.qhit.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qhit.user.bean.User;
import com.qhit.user.service.UserService;
import com.qhit.user.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {
	private UserService userService = new UserServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");	//操作标识
		if(action.equals("login")){	//登录
			login(request,response);
		}else if(action.equals("register")){//注册
			register(request,response);
		}else if(action.equals("exit")){//退出
			exit(request,response);
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	/**
	 * 登录操作
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");//获取到登陆表单提交的值
		User user = userService.login(username, password);//判断是否可以 从数据库查询到用户
		if(user==null){//如果查询不到则账号||密码错误
			request.setAttribute("message", "用户名或密码错误！");//自定义提示信息，储存到request中
			request.getRequestDispatcher("login.jsp").forward(request, response);//转发至login.jsp
		}else{
			request.getSession().setAttribute("user", user);//将user对象放入session
			response.sendRedirect("messageServlet?action=list");//重定向到消息列表页面
		}
	}
	/**
	 * 注册操作
	 * @param request
	 * @param response
 	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void register(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");//获取到注册表单提交的值
		User user = new User(username, password, email);//创建用户对象储存要添加的用户信息
		int result = userService.register(user);//执行添加方法，内置判断，如果存在相同的用户名则会返回0；如果添加成功，返回添加条数
		if(result == 0){//当已存在用户名时
			request.setAttribute("message", "用户名已注册！");//自定义提示信息，储存到request中
			request.getRequestDispatcher("register.jsp").forward(request, response);//转发至login.jsp
		}else{//当注册成功时
			request.setAttribute("message", "注册成功！");//自定义提示信息，储存到request中
			request.getRequestDispatcher("login.jsp").forward(request, response);//转发至login.jsp
		}
	}
	/**
	 * 退出操作
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void exit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().invalidate(); //销毁当前session
		response.sendRedirect("login.jsp"); //登录页面
	}

}
