package cn.yyx.ServletTest.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MyDataSourceUtils {

	private static Context context;
	private static Context envCtx;
	private static DataSource dataSource;

	static {
		try {
			context = new InitialContext();
			envCtx = (Context) context.lookup("java:comp/env");  // 固定路径
			dataSource = (DataSource) envCtx.lookup("jdbc/EmployeeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//创建ThreadLocal
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	//开启事务
	public static void startTransaction() throws SQLException{
		Connection conn = getCurrentConnection();
		conn.setAutoCommit(false);
	}
	
	//获得当前线程上绑定的conn
	public static Connection getCurrentConnection() throws SQLException{
		//从ThreadLocal寻找 当前线程是否有对应Connection
		Connection conn = tl.get();
		if(conn==null){
			//获得新的connection
			conn = getConnection();
			//将conn资源绑定到ThreadLocal（map）上
			tl.set(conn);
		}
		return conn;
	}
	
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}

	//回滚事务
	public static void rollback() throws SQLException {
		getCurrentConnection().rollback();
	}

	//提交事务
	public static void commit() throws SQLException {
		Connection conn = getCurrentConnection();
		conn.commit();
		//将Connection从ThreadLocal中移除
		tl.remove();
		conn.close();
		
	}

}
