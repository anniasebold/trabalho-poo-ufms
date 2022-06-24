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

import model.Aluno;

public class AlunoView extends JPanel {

	private JLabel titulo;
	private JLabel nome;
	private JLabel idade;
	private JLabel ID;
	private JTextField inputNome;
	private JTextField inputIdade;
	private JTextField inputID;
	private JButton salvar;
	private JButton editar;
	private JButton excluir;

	private JTable tabelaAlunos = new JTable();

	String colunas[] = { "ID", "Nome", "Idade", "Modalidade", "Instrutor"};
	DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

	public AlunoView() {
		prepararJanela();
		organizarComponentes();
		organizarEventos();
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
		
		ID = new JLabel("ID ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(ID, gbc);

		inputID = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(inputID, gbc);
		inputID.setEditable(false);

		nome = new JLabel("Nome ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(nome, gbc);

		inputNome = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(inputNome, gbc);

		idade = new JLabel("Idade ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(idade, gbc);

		inputIdade = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(inputIdade, gbc);

		salvar = new JButton("Salvar");
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(salvar, gbc);

		tabelaAlunos.setModel(modelo);
		tabelaAlunos.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 5;
		add(new JScrollPane(tabelaAlunos), gbc);
		
		editar = new JButton("Editar");
		gbc.gridx = 1;
		gbc.gridy = 6;
		add(editar, gbc);
		
		excluir = new JButton("Excluir");
		gbc.gridx = 1;
		gbc.gridy = 7;
		add(excluir, gbc);

	}
	
	private void organizarEventos() {
		salvar.addActionListener((event) -> {
			String nome = inputNome.getText();
			int idade = Integer.parseInt(inputIdade.getText());

			inputNome.setText("");
			inputIdade.setText("");
			modelo.setRowCount(0);

		});

		editar.addActionListener((event) -> {

		});

		excluir.addActionListener((event) -> {
			
		});
		
	}
}
