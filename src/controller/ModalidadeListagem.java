package controller;

import java.util.ArrayList;

import model.Modalidade;

public class ModalidadeListagem {
	private ArrayList<Modalidade> listaModalidades;

	public ModalidadeListagem() {
		listaModalidades = new ArrayList<Modalidade>();
	}

	public void adicionar(Modalidade modalidade) {
		listaModalidades.add(modalidade);
	}

	public Modalidade getOne(int posicao) {
		return listaModalidades.get(posicao);
	}

	public int getSize() {
		return listaModalidades.size();
	}

	public void removeRegister(int posicao) {
		listaModalidades.remove(posicao);
	}

	public void editRegister(int posicao, Modalidade m) {
		listaModalidades.set(posicao, m);
	}

}
