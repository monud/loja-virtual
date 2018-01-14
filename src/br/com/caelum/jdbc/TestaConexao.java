package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		/* Foi alterado o modo de conexão, adotando-se agora o pool de conexão
		 * Connection connection = Conexao.getConnection()*/
		Connection connection = new Conexao().getConnection();
		/*Connection connection = DriverManager
				.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual","SA","");*/
		System.out.println("Abrindo conexão com o banco de dados");
		connection.close();
	}
}

