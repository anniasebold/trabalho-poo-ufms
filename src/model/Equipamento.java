package model;

public class Equipamento extends Entidade {
	
	private String musculoTrab;
	private String estado;
	
	public Equipamento() {
		super();
	}
	
	public Equipamento(int id, String nome, String musculoTrab, String estado) {
		super(id, nome);
		setMusculoTrab(musculoTrab);
		setEstado(estado);
	}

	public String getMusculoTrab() {
		return musculoTrab;
	}

	public void setMusculoTrab(String musculoTrab) {
		this.musculoTrab = musculoTrab;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
