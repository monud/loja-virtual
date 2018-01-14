package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.caelum.jdbc.dao.ProdutoDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaInsercaodeProduto {

	public static void main(String[] args) throws SQLException {
		Produto produto = new Produto(100,"Cama","Cama King Size");
		
		try(Connection connection = new Conexao().getConnection()) {
			ProdutoDAO dao = new ProdutoDAO(connection);
			/* codigo refatorado para o metodo abaixo
			 * String sql = "insert into produto(nome,descricao) values (?,?)";
			try(PreparedStatement stm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
				stm.setString(1, produto.getNome());
				stm.setString(2, produto.getDescricao());
				stm.execute();
				try(ResultSet rs = stm.getGeneratedKeys()){
					rs.next();
					int id = rs.getInt("id");
					produto.setId(id);
				}
			}
			* logo após o código é mudado para inserção do DAO
			insert(produto, connection);*/
			dao.salva(produto);
			List<Produto> produtos = dao.listaTodos();
			System.out.println("Lista de Produtos:");
			for (Produto prod : produtos) {
				System.out.println(prod);
			}
		}
		// Foi retirado e colocado no ProdutoDAO 
		//System.out.println("O produto foi inserido com sucesso " + produto);
	}

	/* Método colocado na nova classe: ProdutoDAO
	 * private static void insert(Produto produto, Connection connection) throws SQLException {
		String sql = "insert into produto(nome,descricao) values (?,?)";
		try(PreparedStatement stm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDescricao());
			stm.execute();
			try(ResultSet rs = stm.getGeneratedKeys()){
				rs.next();
				int id = rs.getInt("id");
				produto.setId(id);
			}
		}
	}*/
}
