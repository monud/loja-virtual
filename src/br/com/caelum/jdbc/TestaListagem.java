package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		/* Foi alterado o modo de conexão, adotando-se agora o pool de conexão
		 * Connection connection = Conexao.getConnection()*/
		Connection connection = new Conexao().getConnection();
		Statement statement = connection.createStatement();
		boolean resultado = statement.execute("select * from Produto");
		System.out.println(resultado);
		ResultSet resultSet = statement.getResultSet();
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			String descricao = resultSet.getString("descricao");
			System.out.println(id);
			System.out.println(nome);
			System.out.println(descricao);
		}
		resultSet.close();
		statement.close();
		connection.close();
	}
}
