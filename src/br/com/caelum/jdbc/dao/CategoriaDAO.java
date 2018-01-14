package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class CategoriaDAO {

	private final Connection con;

	public CategoriaDAO(Connection connection) {
		this.con = connection;
	}

	public List<Categoria> listaTodos() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		String sql = "select * from Categoria";
		try(PreparedStatement stm = con.prepareStatement(sql)){
			stm.execute();
			try(ResultSet rs = stm.getResultSet()){
				while(rs.next()) {
					int idc = rs.getInt("id");
					String nomec = rs.getString("nome");
					Categoria categoria = new Categoria(idc,nomec);
					categorias.add(categoria);
				}
			}
		}
		return categorias;
	}

	public List<Categoria> listaMaisProdutos() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Categoria ultima = null;
		
		String sql = "select c.id as idc,c.nome as nomec,"
				+ "p.id as idp, p.nome as nomep, p.descricao as descricaop"
				+ " from Categoria as c join Produto as p on p.categoria_id = c.id";
		try(PreparedStatement stm = con.prepareStatement(sql)){
			stm.execute();
			try(ResultSet rs = stm.getResultSet()){
				while(rs.next()) {
					int idc = rs.getInt("idc");
					String nomec = rs.getString("nomec");
					if(ultima == null || !ultima.getNome().equals(nomec)) {
						Categoria categoria = new Categoria(idc,nomec);
						categorias.add(categoria);
						ultima = categoria;
					}
					int idp = rs.getInt("idp");
					String nomep = rs.getString("nomep");
					String descricaop = rs.getString("descricaop");
					Produto produto = new Produto(idp,nomep,descricaop);
					ultima.addProduto(produto);
				}
			}
		}
		return categorias;
	}

}
