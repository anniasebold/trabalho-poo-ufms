package model;

abstract public class Entidade {
	private int id;
	private String nome;
	
	public Entidade() {
		
	}

	public Entidade(int id, String nome) {
		super();
		setId(id);
		setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
