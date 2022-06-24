package controller;

import java.util.ArrayList;

import model.Aluno;

public class AlunoListagem {
	private ArrayList<Aluno> listaAlunos;

	public AlunoListagem() {
		listaAlunos = new ArrayList<Aluno>();
	}

	public void adicionar(Aluno aluno) {
		listaAlunos.add(aluno);
	}

	public Aluno getOne(int posicao) {
		return listaAlunos.get(posicao);
	}

	public int getSize() {
		return listaAlunos.size();
	}

	public void removeRegister(int posicao) {
		listaAlunos.remove(posicao);
	}

	public void editRegister(int posicao, Aluno a) {
		listaAlunos.set(posicao, a);
	}
}
