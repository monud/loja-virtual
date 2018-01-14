package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		
		/*O bloco try automaticamente j� fecha tudo que ele toca, ent�o esse connection ser� fechado automaticamente
		o c�digo abaixo demonstra isso!
		Connection connection = Conexao.getConnection();
		Por padr�o o commit no banco � autom�tico ent�o para sair desse padr�o e o usu�rio poder voltar atr�s da descis�o
		de inserir um dado e o outro n�o colocasse isso, e apartir dai dever� se colocar commit para efetuar as transa��es
		connection.setAutoCommit(false);

		 Substituido pelos parametros que ser�oinseridos no statement
		 * String nome = "Notebook'";
		String descricao = "Notebook I5 ACER";
		
		
		Substituido pelo prepareStament para que ao inves de passar o produto que ir� ser inserido no createstatement, o 
		 * prepare permite que se colocaque ' no nome e evita sqlInjection, assim o createStatement foi substituido pelo 
		 * prepare, al�m de simplificar e modificar o c�digo
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
			adiciona("Garrafa t�rmica","Garrafa t�rmica de 1 litro", statement);
			
			statement.close();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			System.out.println("Rollback efetuado");
		}
		connection.close();*/
		
		/* Foi alterado o modo de conex�o, adotando-se agora o pool de conex�o
		 * try(Connection connection = Conexao.getConnection()) {*/
			
		try(Connection connection = new Conexao().getConnection()) {
			connection.setAutoCommit(false);		
			String sql = "Insert into produto(nome,descricao) values (? , ? )";
			try(PreparedStatement statement = connection.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS)) {
				adiciona("TV LCD","TV LCD de 15 polegadas",statement);
				adiciona("Garrafa t�rmica","Garrafa t�rmica de 1 litro", statement);
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
