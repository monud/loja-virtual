package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.dao.CategoriaDAO;
import br.com.caelum.jdbc.dao.ProdutoDAO;
import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaCategorias {

	/*Metodo antigo
	 * public static void main(String[] args) throws SQLException {
		try(Connection connection = new Conexao().getConnection()){
			System.out.println("Categorias:");
			* Aqui foi alterado para que ao se trazer com o select as categorias viesse junto com os produtos, 
			* vide codigo abaixo
			List<Categoria> categorias = new CategoriaDAO(connection).listaTodos();
			for (Categoria categoria : categorias) {
				System.out.println(categoria);
				for (Produto produto : new ProdutoDAO(connection).busca(categoria)) {
					System.out.println(categoria + " - " + produto);
				}
			}
		}
	}
	* A busca é feita primeiro pelas categorias , depois buscam-se os produtos que tem aquela categoria
	public static void main(String[] args) throws SQLException {
		try(Connection connection = new Conexao().getConnection()){
			System.out.println("Categorias:");
			List<Categoria> categorias = new CategoriaDAO(connection).listaMaisProdutos();
			for (Categoria categoria : categorias) {
				System.out.println(categoria);
				for (Produto produto : new ProdutoDAO(connection).busca(categoria)) {
					System.out.println(categoria + " - " + produto);
				}
			}
		}
	}
	
	* Alteração agora buscando a categoria em conjunto com o produto , ou seja, vc faz um select  que traz as 
	* categorias e os produtos, esse código é o que o código novo faz logo abaixo
	* */
	public static void main(String[] args) throws SQLException {
		try(Connection connection = new Conexao().getConnection()){
			System.out.println("Categorias:");
			List<Categoria> categorias = new CategoriaDAO(connection).listaMaisProdutos();
			for (Categoria categoria : categorias) {
				System.out.println(categoria);
				for (Produto produto : categoria.getProdutos()) {
					System.out.println(categoria + " - " + produto);
				}
			}
		}
	}
}
