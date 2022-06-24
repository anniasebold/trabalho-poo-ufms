package model;

public class Aluno extends Entidade {
	private int idade;
	private String objetivo;

	public Aluno(int id, String nome, int idade, String objetivo) {
		super(id, nome);
		setIdade(idade);
		setObjetivo(objetivo);
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

}
