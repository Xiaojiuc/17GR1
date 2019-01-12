package com.qhit.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qhit.message.bean.Message;
import com.qhit.message.service.MessageService;
import com.qhit.message.service.impl.MessageServiceImpl;
import com.qhit.user.bean.User;

public class MessageServlet extends HttpServlet {
	private MessageService messageService = new MessageServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//做一个选择器
		if(action.equals("list")){ //首页
			list(request,response);
		}else if(action.equals("detail")){ //查看邮件
			detail(request,response);
		}else if(action.equals("del")){ //删除
			del(request,response);
		}else if(action.equals("preSend")){ //发送或回复
			preSend(request,response);
		}else if(action.equals("send")){ //发送信息
			send(request,response);
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	/**
	 * 显示列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");//从session中获取到登陆的user对象
		String sendto = user.getUsername();//获取的user对象的name属性
		List<Message> list = messageService.showIndex(sendto);//通过查询接收人为登陆用户的信息
		request.setAttribute("list", list);//将查到的结果储存到list中
		request.getRequestDispatcher("main.jsp").forward(request, response);//转发至main.jsp 
		
	}
	/**
	 * 查看邮件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int msgid = Integer.parseInt(request.getParameter("msgid"));//获取信息的ID
		Message message = messageService.showDetail(msgid);//通过ID查询需要读取的信息
		request.setAttribute("message", message);//将信息存入request
		request.getRequestDispatcher("readMsg.jsp").forward(request, response);//转发到readMsg.jsp页面
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int msgid = Integer.parseInt(request.getParameter("msgid"));//获取到信息的ID
		int count = messageService.delMessage(msgid);//通过Id删除掉信息
		if(count==1){//如果返回值为1则删除成功
			request.setAttribute("message", "删除成功！");//自定义提示信息
			request.getRequestDispatcher("messageServlet?action=list").forward(request, response);//转发至messageServlet页面
		}
	}
	/**
	 * 准备发送或者回复
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void preSend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sendto = request.getParameter("sendto");//获取到接收人的信息
		request.setAttribute("sendto", sendto);//将接受人存入
		User user = (User) request.getSession().getAttribute("user");//获取发送用户
		String username = user.getUsername();//获取到接收人的名字
		List<User> list = messageService.showSend(username);//通过用户获取到信息
		request.setAttribute("list", list);//将信息存入集合
		request.getRequestDispatcher("newMsg.jsp").forward(request, response);//转发值信息编辑页面
	}
	/**
	 * 发送
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void send(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sendto = request.getParameter("sendto");//获取接收人
		String title = request.getParameter("title");//获取信息标题
		String msgcontent = request.getParameter("msgcontent");//获取信息内容
		User user = (User) request.getSession().getAttribute("user");//获取接收人
		String username = user.getUsername();	//当前登录用户为发送人
		Message message = new Message();//创建一个信息对象
		message.setUsername(username);
		message.setTitle(title);
		message.setMsgcontent(msgcontent);
		message.setSendto(sendto);//为message设置值
		int count = messageService.sendMessage(message);//添加信息到数据库
		if(count==1){//添加成功返回1
			request.setAttribute("message", "发送成功！");//设置自定义提示
			list(request,response);  //调用列表显示方法
		}
		
	}

}
