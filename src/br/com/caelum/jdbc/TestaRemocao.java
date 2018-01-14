package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		/* Foi alterado o modo de conexão, adotando-se agora o pool de conexão
		 * Connection connection = Conexao.getConnection()*/
		Connection connection = new Conexao().getConnection();
		Statement statement = connection.createStatement();
		statement.execute("delete from produto where id>3");
		int count = statement.getUpdateCount();
		System.out.println(count + " linhas atualizadas");
		
		statement.close();
		connection.close();
	}

}
