package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import DAO.InstrutorDAO;
import DAO.ModalidadeDAO;
import controller.InstrutorListagem;
import controller.ModalidadeListagem;
import model.Instrutor;
import model.Modalidade;

public class ModalidadeView extends JPanel {

	private JLabel titulo;
	private JLabel nome;
	private JLabel valor;
	private JLabel IdInstrutor;
	private JLabel ID;
	private JTextField inputNome;
	private JTextField inputValor;
	private JTextField inputIdInstrutor;
	private JTextField inputID;
	private JButton salvar;
	private JButton editar;
	private JButton excluir;
	private JButton selecionarLinha;

	private JTable tabelaModalidades = new JTable();
	
	String colunas[] = { "ID", "Nome", "Valor", "Nome Instrutor" };
	DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
	
	ArrayList<Modalidade> listaModalidades = new ArrayList<>();
	private InstrutorDAO instrutorDAO = new InstrutorDAO();
	private ModalidadeDAO modalidadeDAO = new ModalidadeDAO();

	public ModalidadeView() {
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

		titulo = new JLabel("Menu de Modalidades");
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

		valor = new JLabel("Valor ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(valor, gbc);

		inputValor = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(inputValor, gbc);

		IdInstrutor = new JLabel("ID Instrutor ");
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(IdInstrutor, gbc);

		inputIdInstrutor = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(inputIdInstrutor, gbc);

		salvar = new JButton("Salvar");
		gbc.gridx = 1;
		gbc.gridy = 5;
		add(salvar, gbc);

		tabelaModalidades.setModel(modelo);
		tabelaModalidades.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 6;
		add(new JScrollPane(tabelaModalidades), gbc);
		
		editar = new JButton("Editar");
		gbc.gridx = 1;
		gbc.gridy = 7;
		add(editar, gbc);
		
		excluir = new JButton("Excluir");
		gbc.gridx = 1;
		gbc.gridy = 8;
		add(excluir, gbc);

	}

	private void organizarEventos() {
		carregarListaModalidades();
		
		salvar.addActionListener((event) -> {
			if (inputNome.getText().isEmpty() || inputValor.getText().isEmpty() || inputIdInstrutor.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Modalidade modalidade = new Modalidade();
			
			String nome = inputNome.getText();
			double valor = Double.parseDouble(inputValor.getText());
			int idInstrutor = Integer.parseInt(inputIdInstrutor.getText());
			
			Instrutor instrutorCarregado = new Instrutor();
			
			instrutorCarregado = instrutorDAO.getInstrutor(idInstrutor);
			
			if(instrutorDAO.getInstrutor(idInstrutor) != null) {
				modalidade.setIdinstrutor(idInstrutor);
				modalidade.setNome(nome);
				modalidade.setValor(valor);
				
				if(modalidadeDAO.salvarModalidade(modalidade, idInstrutor)) {
					inputNome.setText("");
					inputValor.setText("");
					inputIdInstrutor.setText("");
					
					carregarListaModalidades();
					
					JOptionPane.showMessageDialog(this, "Modalidade cadastrada com sucesso.");
				} else {
					JOptionPane.showMessageDialog(this, "Erro ao cadastrar modalidade.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(this, "Verifique o ID de instrutor e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		editar.addActionListener((event) -> {
			
		});

		excluir.addActionListener((event) -> {	
			int linhaSelecionada = tabelaModalidades.getSelectedRow();
			
			if (linhaSelecionada != -1) {
				int idModalidade = (int) tabelaModalidades.getValueAt(linhaSelecionada, 0);
				
				if(modalidadeDAO.removerModalidade(idModalidade)) {
					carregarListaModalidades();
					JOptionPane.showMessageDialog(this, "Modalidade excluída com sucesso.");
				} else {
					JOptionPane.showMessageDialog(this, "Erro ao excluir modalidade.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma modalidade para ser excluída.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	private void carregarListaModalidades() {
		listaModalidades = modalidadeDAO.listarModalidades();
		
		modelo.setRowCount(0);
		
		for(int i = 0; i < listaModalidades.size(); i++) {
			Object[] lista = {
					listaModalidades.get(i).getId(),
					listaModalidades.get(i).getNome(),
					listaModalidades.get(i).getValor(),
					listaModalidades.get(i).getNomeInstrutor()
			};
			
			modelo.addRow(lista);
		}
	}
}
