package br.com.caelum.jdbc.modelo;

public class Produto {

		private int id;
		private String nome;
		private String descricao;
		
		
		public Produto(int id, String nome, String descricao) {
			super();
			this.id = id;
			this.nome = nome;
			this.descricao = descricao;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
		@Override
		public String toString() {
			//return "Produto: Id " + getId() + " " + getNome() + " - " + getDescricao();
			// ou dessa forma 
			return String.format("Produto: Id %d %s - %s", id,nome,descricao);
		}
		
}
