package controller;

import java.util.ArrayList;

import model.Equipamento;

public class EquipamentoListagem {
	private ArrayList<Equipamento> listaEquipamentos;

	public EquipamentoListagem() {
		listaEquipamentos = new ArrayList<Equipamento>();
	}

	public void adicionar(Equipamento instrutor) {
		listaEquipamentos.add(instrutor);
	}

	public Equipamento getOne(int posicao) {
		return listaEquipamentos.get(posicao);
	}

	public int getSize() {
		return listaEquipamentos.size();
	}

	public void removeRegister(int posicao) {
		listaEquipamentos.remove(posicao);
	}

	public void editRegister(int posicao, Equipamento e) {
		listaEquipamentos.set(posicao, e);
	}

}
