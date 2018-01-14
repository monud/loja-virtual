package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		
		/*O bloco try automaticamente já fecha tudo que ele toca, então esse connection será fechado automaticamente
		o código abaixo demonstra isso!
		Connection connection = Conexao.getConnection();
		Por padrão o commit no banco é automático então para sair desse padrão e o usuário poder voltar atrás da descisão
		de inserir um dado e o outro não colocasse isso, e apartir dai deverá se colocar commit para efetuar as transações
		connection.setAutoCommit(false);

		 Substituido pelos parametros que serãoinseridos no statement
		 * String nome = "Notebook'";
		String descricao = "Notebook I5 ACER";
		
		
		Substituido pelo prepareStament para que ao inves de passar o produto que irá ser inserido no createstatement, o 
		 * prepare permite que se colocaque ' no nome e evita sqlInjection, assim o createStatement foi substituido pelo 
		 * prepare, além de simplificar e modificar o código
		 * Statement statement = connection.createStatement();
		boolean resultado = statement
				.execute("Insert into produto(nome,descricao) values ('Notebook','Notebook I5')",
						Statement.RETURN_GENERATED_KEYS);
		System.out.println(resultado);
		ResultSet resultSet = statement.getGeneratedKeys();
		
		
		try {
			String sql = "Insert into produto(nome,descricao) values (? , ? )";
			PreparedStatement statement = connection.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
			
			 ALT+ SHIFT + M -> para o metodo adiciona
			 * statement.setString(1, nome);
			statement.setString(2, descricao);
			boolean resultado = statement.execute();
			System.out.println(resultado);
			ResultSet resultSet = statement.getGeneratedKeys();
			
			while(resultSet.next()) {
				String id = resultSet.getString("id");
				System.out.println("Id: " + id + " gerado");
			}
			resultSet.close();
			
			adiciona("TV LCD","TV LCD de 15 polegadas",statement);
			adiciona("Garrafa térmica","Garrafa térmica de 1 litro", statement);
			
			statement.close();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			System.out.println("Rollback efetuado");
		}
		connection.close();*/
		
		/* Foi alterado o modo de conexão, adotando-se agora o pool de conexão
		 * try(Connection connection = Conexao.getConnection()) {*/
			
		try(Connection connection = new Conexao().getConnection()) {
			connection.setAutoCommit(false);		
			String sql = "Insert into produto(nome,descricao) values (? , ? )";
			try(PreparedStatement statement = connection.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS)) {
				adiciona("TV LCD","TV LCD de 15 polegadas",statement);
				adiciona("Garrafa térmica","Garrafa térmica de 1 litro", statement);
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
				connection.rollback();
				System.out.println("Rollback efetuado");
			}
		}
	}
	
	private static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
		statement.setString(1, nome);
		statement.setString(2, descricao);
		boolean resultado = statement.execute();
		System.out.println(resultado);
		ResultSet resultSet = statement.getGeneratedKeys();
		
		while(resultSet.next()) {
			String id = resultSet.getString("id");
			System.out.println("Id: " + id + " gerado");
		}
		resultSet.close();
	}
}
