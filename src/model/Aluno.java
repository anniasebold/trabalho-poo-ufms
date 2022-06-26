package model;

public class Aluno extends Entidade {
	private int idade;
	private int idModalidade;
	private int idInstrutor;
	private String nomeModalidade;
	private String nomeInstrutor;
	
	public Aluno() {
		super();
	}

	public Aluno(int id, String nome, int idade) {
		super(id, nome);
		setIdade(idade);
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getIdModalidade() {
		return idModalidade;
	}

	public void setIdModalidade(int idModalidade) {
		this.idModalidade = idModalidade;
	}

	public int getIdInstrutor() {
		return idInstrutor;
	}

	public void setIdInstrutor(int idInstrutor) {
		this.idInstrutor = idInstrutor;
	}

	public String getNomeModalidade() {
		return nomeModalidade;
	}

	public void setNomeModalidade(String nomeModalidade) {
		this.nomeModalidade = nomeModalidade;
	}

	public String getNomeInstrutor() {
		return nomeInstrutor;
	}

	public void setNomeInstrutor(String nomeInstrutor) {
		this.nomeInstrutor = nomeInstrutor;
	}
	
	
	
}
