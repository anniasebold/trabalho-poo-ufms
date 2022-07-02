package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.AlunoDAO;
import DAO.InstrutorDAO;
import DAO.ModalidadeDAO;
import model.Aluno;
import model.Instrutor;
import model.Modalidade;

public class AlunoView extends JPanel {

	private JLabel titulo;
	private JLabel nome;
	private JLabel idade;
	private JLabel ID;
	private JLabel idInstrutor;
	private JLabel idModalidade;
	private JTextField inputNome;
	private JTextField inputIdade;
	private JTextField inputID;
	private JTextField inputIdInstrutor;
	private JTextField inputIdModalidade;
	private JButton salvar;
	private JButton editar;
	private JButton excluir;
	private JLabel status;
	private JLabel statusText;

	private JTable tabelaAlunos = new JTable();

	String colunas[] = { "ID", "Nome", "Idade", "Instrutor", "Modalidade"};
	DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
	
	ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
	private AlunoDAO alunoDAO = new AlunoDAO();
	private InstrutorDAO instrutorDAO = new InstrutorDAO();
	private ModalidadeDAO modalidadeDAO = new ModalidadeDAO();
	
	private boolean cadastro = true;

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
		
		idInstrutor = new JLabel("ID Instrutor");
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(idInstrutor, gbc);

		inputIdInstrutor = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(inputIdInstrutor, gbc);
		
		idModalidade = new JLabel("ID Modalidade");
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(idModalidade, gbc);

		inputIdModalidade = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 5;
		add(inputIdModalidade, gbc);


		salvar = new JButton("Salvar");
		gbc.gridx = 1;
		gbc.gridy = 6;
		add(salvar, gbc);

		tabelaAlunos.setModel(modelo);
		tabelaAlunos.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 7;
		add(new JScrollPane(tabelaAlunos), gbc);
		
		editar = new JButton("Editar");
		gbc.gridx = 1;
		gbc.gridy = 8;
		add(editar, gbc);
		
		excluir = new JButton("Excluir");
		gbc.gridx = 1;
		gbc.gridy = 9;
		add(excluir, gbc);
		
		status = new JLabel("Status: ");
		gbc.gridx = 0;
		gbc.gridy = 10;
		add(status, gbc);
		
		statusText = new JLabel("Listando Alunos");
		gbc.gridx = 1;
		gbc.gridy = 10;
		add(statusText, gbc);

	}
	
	private void organizarEventos() {
		carregarListaAlunos();
		
		salvar.addActionListener((event) -> {
			if (inputNome.getText().isEmpty() || inputIdade.getText().isEmpty() || inputIdInstrutor.getText().isEmpty()
					|| inputIdModalidade.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String nome = inputNome.getText();
			int idade = Integer.parseInt(inputIdade.getText());
			int idInstrutor = Integer.parseInt(inputIdInstrutor.getText());
			int idModalidade = Integer.parseInt(inputIdModalidade.getText());
			
			Instrutor instrutorCarregado = instrutorDAO.getInstrutor(idInstrutor);
			Modalidade modalidadeCarregada = modalidadeDAO.getModalidade(idModalidade);
			
			Aluno aluno = new Aluno();
			
			if(instrutorCarregado == null) {
				JOptionPane.showMessageDialog(this, "Verifique o ID de instrutor e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			} else if(modalidadeCarregada == null) {
				JOptionPane.showMessageDialog(this, "Verifique o ID da modalidade e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				aluno.setNome(nome);
				aluno.setIdade(idade);
				aluno.setIdInstrutor(idInstrutor);
				aluno.setIdModalidade(idModalidade);
				
				if(cadastro) {
					
					statusText.setText("");
					statusText.setText("Cadastrando Aluno");
					
					if(alunoDAO.salvarAluno(aluno)) {
						inputNome.setText("");
						inputIdade.setText("");
						inputIdInstrutor.setText("");
						inputIdModalidade.setText("");
						
						JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso.");
						carregarListaAlunos();
					} else {
						JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno.", "Erro", JOptionPane.ERROR_MESSAGE);
					}	
				} else {
					int idAluno = Integer.parseInt(inputID.getText());
					aluno.setId(idAluno);
					
					if(alunoDAO.editarAluno(aluno)) {
						JOptionPane.showMessageDialog(this, "Aluno editado com sucesso.");
						carregarListaAlunos();
						
						inputID.setText("");
						inputNome.setText("");
						inputIdade.setText("");
						inputIdInstrutor.setText("");
						inputIdModalidade.setText("");
						cadastro = true;
					} else {
						JOptionPane.showMessageDialog(this, "Erro ao editar aluno.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});

		editar.addActionListener((event) -> {
			cadastro = false;
			int linhaSelecionada = tabelaAlunos.getSelectedRow();
			Aluno alunoCarregado = new Aluno();
			
			if (linhaSelecionada != -1) {
				
				statusText.setText("");
				statusText.setText("Editando Aluno");
				
				int idAluno = (int) tabelaAlunos.getValueAt(linhaSelecionada, 0);
				
				alunoCarregado = alunoDAO.getAluno(idAluno);
				
				inputID.setText(String.valueOf(alunoCarregado.getId()));
				inputNome.setText(alunoCarregado.getNome());
				inputIdade.setText(String.valueOf(alunoCarregado.getIdade()));
				inputIdInstrutor.setText(String.valueOf(alunoCarregado.getIdInstrutor()));
				inputIdModalidade.setText(String.valueOf(alunoCarregado.getIdModalidade()));
				
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um aluno para ser editado.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			
		});

		excluir.addActionListener((event) -> {
			int linhaSelecionada = tabelaAlunos.getSelectedRow();
			
			if (linhaSelecionada != -1) {
				
				statusText.setText("");
				statusText.setText("Excluindo Aluno");
				
				int idAluno = (int) tabelaAlunos.getValueAt(linhaSelecionada, 0);
				
				if(alunoDAO.removerAluno(idAluno)) {
					JOptionPane.showMessageDialog(this, "Aluno excluído com sucesso.");
					carregarListaAlunos();
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao excluir aluno.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um aluno para ser excluído.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
		
	}
	
	private void carregarListaAlunos() {
		listaAlunos = alunoDAO.listarAlunos();
		
		modelo.setRowCount(0);
		
		for(int i = 0; i < listaAlunos.size(); i++ ) {
			Object[] lista = {
					listaAlunos.get(i).getId(),
					listaAlunos.get(i).getNome(),
					listaAlunos.get(i).getIdade(),
					listaAlunos.get(i).getNomeInstrutor(),
					listaAlunos.get(i).getNomeModalidade()
			};
			
			modelo.addRow(lista);
		}
	}
}
