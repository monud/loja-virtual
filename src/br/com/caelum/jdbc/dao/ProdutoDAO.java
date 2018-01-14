package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class ProdutoDAO {

	private final Connection con;

	public ProdutoDAO(Connection con) {
		this.con = con;
	}

	public void salva(Produto produto) throws SQLException {
		String sql = "insert into produto(nome,descricao) values (?,?)";
		try(PreparedStatement stm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDescricao());
			stm.execute();
			try(ResultSet rs = stm.getGeneratedKeys()){
				rs.next();
				int id = rs.getInt("id");
				produto.setId(id);
			}
		}
		System.out.println("O produto foi inserido com sucesso " + produto);
	}

	public List<Produto> listaTodos() throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from produto";
		try(PreparedStatement stm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			stm.execute();
			resultadoEmProduto(produtos, stm);
		}
		return produtos;
	}

	private void resultadoEmProduto(List<Produto> produtos, PreparedStatement stm) throws SQLException {
		try(ResultSet rs = stm.getResultSet()){
			while(rs.next()){
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Produto produto = new Produto(id,nome,descricao);
				produtos.add(produto);
			}
		}
	}

	public List<Produto> busca(Categoria categoria) throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from produto where categoria_id=?";
		try(PreparedStatement stm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, categoria.getId());
			stm.execute();
			resultadoEmProduto(produtos, stm);
		}
		return produtos;
	}
}
