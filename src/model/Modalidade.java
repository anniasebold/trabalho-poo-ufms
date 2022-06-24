package model;

public class Modalidade extends Entidade {
	private double valor;
	private int Idinstrutor;
	private String nomeInstrutor;
	
	public Modalidade() {
		super();
	}
	
	public Modalidade(int id, String nome, double valor, int idInstrutor) {
		super(id, nome);
		setValor(valor);
		setIdinstrutor(idInstrutor);
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getIdinstrutor() {
		return Idinstrutor;
	}

	public void setIdinstrutor(int idInstrutor) {
		this.Idinstrutor = idInstrutor;
	}

	public String getNomeInstrutor() {
		return nomeInstrutor;
	}

	public void setNomeInstrutor(String nomeInstrutor) {
		this.nomeInstrutor = nomeInstrutor;
	}
	
	

}
