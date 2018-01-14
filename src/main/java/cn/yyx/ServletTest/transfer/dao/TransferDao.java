package cn.yyx.ServletTest.transfer.dao;

import java.sql.Connection;
import java.sql.SQLException;

import cn.yyx.ServletTest.utils.MyDataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

public class TransferDao {

	public void out(String out, double money) throws SQLException {
		QueryRunner runner = new QueryRunner();
		Connection conn = MyDataSourceUtils.getCurrentConnection();
		String sql = "update account set money=money-? where name=?";
		runner.update(conn, sql, money,out);
	}

	public void in(String in, double money) throws SQLException {
		QueryRunner runner = new QueryRunner();
		Connection conn = MyDataSourceUtils.getCurrentConnection();
		String sql = "update account set money=money+? where name=?";
		runner.update(conn, sql, money,in);
	}

}
