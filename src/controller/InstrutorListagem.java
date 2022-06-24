package controller;

import java.util.ArrayList;

import model.Instrutor;

public class InstrutorListagem {
	private ArrayList<Instrutor> listaInstrutores;

	public InstrutorListagem() {
		listaInstrutores = new ArrayList<Instrutor>();
	}

	public void adicionar(Instrutor instrutor) {
		listaInstrutores.add(instrutor);
	}

	public Instrutor getOne(int posicao) {
		return listaInstrutores.get(posicao);
	}

	public int getSize() {
		return listaInstrutores.size();
	}

	public void removeRegister(int posicao) {
		listaInstrutores.remove(posicao);
	}

	public void editRegister(int posicao, Instrutor i) {
		listaInstrutores.set(posicao, i);
	}

}
