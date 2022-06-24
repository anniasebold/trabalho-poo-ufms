package model;

public class Instrutor extends Entidade {
	
	private String formacao;
	
	public Instrutor() {
		super();
	}

	public Instrutor(int id, String nome, String formacao) {
		super(id, nome);
		setFormacao(formacao);
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	
}
