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
	/*Antiga conex�o , foi mudado para um pool de conex�es para que v�rios usu�rios possam utilizar a mesma conex�o
	 * e para que isso n�o pese o banco de dados entre aberturas e fechamentos de sess�es no banco de dados e consultas
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
