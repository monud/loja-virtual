package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCPool;

public class Conexao {
	
	private DataSource dataSource;
	
	public Conexao() {
		JDBCPool pool = new JDBCPool();
		pool.setUrl("jdbc:hsqldb:hsql://localhost/loja-virtual");
		pool.setUser("SA");
		pool.setPassword("");
		this.dataSource = pool;
	}
	/*Antiga conexão , foi mudado para um pool de conexões para que vários usuários possam utilizar a mesma conexão
	 * e para que isso não pese o banco de dados entre aberturas e fechamentos de sessões no banco de dados e consultas
	 * static Connection getConnection() throws SQLException {
		Connection connection = DriverManager
				.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual","SA","");
		return connection;
	}*/
	Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		return connection;
	}
}
