package com.qhit.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 基础DAO
 * @author Administrator
 *
 */
public class BaseDao {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://127.0.0.1:3306/bbs_meg?useUnicode=true&characterEncoding=utf-8";
	private static String user = "root";
	private static String password = "root";
	private static Connection conn;	//JDBC方式
	protected Connection connection = null; //连接jdbc方式
	//静态块，类加载时会自动执行，加载驱动
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

    /**
     * 获取数据库连接对象。（JDBC）
     */
    public Connection getConnection() {
        // 获取连接并捕获异常
        try {
            if (conn == null || conn.isClosed()) //当链接为null或者链接被关闭，创建新链接
                conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;// 返回连接对象
    }
    /**
     * 连接池
     * @return
     */
	protected Connection getConnectionByDS(){
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Message");
			connection = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * 关闭资源
	 * @param con
	 * @param st
	 * @param rs
	 */
	protected void closeAll(Connection con,Statement st,ResultSet rs){
		try {
			if(rs!=null && !rs.isClosed()){
				rs.close();
			}
			if(st!=null && !st.isClosed()){
				st.close();
			}
			if(con!=null && !con.isClosed()){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 通用查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public ResultSet executeQuery(String sql,Object... params){
		ResultSet rs = null;
		conn = getConnection();	//创建连接
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);	//创建预编译陈述对象
			//给SQL传参
			for(int i=0;i<params.length;i++){//将params看为数组，其中第一个参数就是第一个元素
				ps.setObject(i+1, params[i]); //因为问号序号是从1开始，而数组是从0开始
			}
			rs = ps.executeQuery();	//执行SQL语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 通用增删改
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeUpdate(String sql,Object... params){
		int result = 0;
		conn = getConnection();	//创建连接
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);	//创建预编译陈述对象
			//给SQL传参
			for(int i=0;i<params.length;i++){//将params看为数组，其中第一个参数就是第一个元素
				ps.setObject(i+1, params[i]); //因为问号序号是从1开始，而数组是从0开始
			}
			result = ps.executeUpdate();	//执行SQL语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeAll(conn, ps, null);//关闭
		}
		return result;
	}
}
