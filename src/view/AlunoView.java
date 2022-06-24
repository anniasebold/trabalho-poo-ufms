package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.AlunoListagem;
import model.Aluno;

public class AlunoView extends JPanel {

	private JLabel titulo;
	private JLabel nome;
	private JLabel idade;
	private JLabel objetivo;
	private JTextField inputNome;
	private JTextField inputIdade;
	private JTextField inputObjetivo;
	private JButton salvar;
	private JButton editar;
	private JButton excluir;
	private JButton selecionarLinha;

	private JTable tabelaAlunos = new JTable();
	AlunoListagem listaAlunos = new AlunoListagem();

	String colunas[] = { "Nome", "Idade", "Objetivo" };
	DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

	public AlunoView() {
		prepararJanela();
		organizarComponentes();
		organizarEventos();
//		finalizar();
	}

	public void prepararJanela() {
		setLayout(new GridBagLayout());
	}

	private void organizarComponentes() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		titulo = new JLabel("Menu de Alunos");
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(titulo, gbc);

		nome = new JLabel("Nome ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(nome, gbc);

		inputNome = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(inputNome, gbc);

		idade = new JLabel("Idade ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(idade, gbc);

		inputIdade = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(inputIdade, gbc);

		objetivo = new JLabel("Objetivo ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(objetivo, gbc);

		inputObjetivo = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(inputObjetivo, gbc);

		salvar = new JButton("Salvar");
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(salvar, gbc);

		selecionarLinha = new JButton("Selecionar Registro");
		gbc.gridx = 1;
		gbc.gridy = 5;
		add(selecionarLinha, gbc);

		editar = new JButton("Editar");
		gbc.gridx = 1;
		gbc.gridy = 6;
		add(editar, gbc);
		editar.setVisible(false);

		excluir = new JButton("Excluir");
		gbc.gridx = 1;
		gbc.gridy = 7;
		add(excluir, gbc);

		tabelaAlunos.setModel(modelo);
		tabelaAlunos.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 9;
		add(new JScrollPane(tabelaAlunos), gbc);

	}
	
	private void organizarEventos() {
		salvar.addActionListener((event) -> {
			String nome = inputNome.getText();
			int idade = Integer.parseInt(inputIdade.getText());
			String objetivo = inputObjetivo.getText();

//			Aluno aluno = new Aluno(nome, idade, objetivo);
//			listaAlunos.adicionar(aluno);

			inputNome.setText("");
			inputIdade.setText("");
			inputObjetivo.setText("");
			modelo.setRowCount(0);

			for (int i = 0; i < listaAlunos.getSize(); i++) {
				Object[] lista = { 
						listaAlunos.getOne(i).getNome(), 
						listaAlunos.getOne(i).getIdade(), 
						listaAlunos.getOne(i).getObjetivo(), 
				};

				modelo.addRow(lista);
			}
		});

		selecionarLinha.addActionListener((event) -> {
			int linhaSelecionada = tabelaAlunos.getSelectedRow();
			if (linhaSelecionada != -1) {
				inputNome.setText(tabelaAlunos.getValueAt(linhaSelecionada, 0).toString());
				inputIdade.setText(tabelaAlunos.getValueAt(linhaSelecionada, 1).toString());
				inputObjetivo.setText(tabelaAlunos.getValueAt(linhaSelecionada, 2).toString());
				editar.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um aluno.");
			}
		});

		editar.addActionListener((event) -> {
			int linhaSelecionada = tabelaAlunos.getSelectedRow();

			String nome = inputNome.getText();
			int idade = Integer.parseInt(inputIdade.getText());
			String objetivo = inputObjetivo.getText();
//			Aluno alunoEditado = new Aluno(nome, idade, objetivo);

			inputNome.setText("");
			inputIdade.setText("");
			inputObjetivo.setText("");

//			listaAlunos.editRegister(linhaSelecionada, alunoEditado);

			tabelaAlunos.setValueAt(nome, linhaSelecionada, 0);
			tabelaAlunos.setValueAt(idade, linhaSelecionada, 1);
			tabelaAlunos.setValueAt(objetivo, linhaSelecionada, 2);

		});

		excluir.addActionListener((event) -> {
			int linhaSelecionada = tabelaAlunos.getSelectedRow();
			if (linhaSelecionada != -1) {
				listaAlunos.removeRegister(linhaSelecionada);
				modelo.removeRow(linhaSelecionada);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um aluno para ser excluído.");
			}
		});
		
	}

	private void finalizar() {
		setVisible(true);
	}
}
