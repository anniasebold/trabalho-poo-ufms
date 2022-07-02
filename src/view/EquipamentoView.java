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

import DAO.EquipamentoDAO;
import model.Equipamento;

public class EquipamentoView extends JPanel {

	private JLabel titulo;
	private JLabel nome;
	private JLabel musculoTrab;
	private JLabel estado;
	private JLabel id;
	private JTextField inputNome;
	private JTextField inputMusculoTrab;
	private JTextField inputEstado;
	private JTextField inputID;
	private JButton salvar;
	private JButton editar;
	private JButton excluir;
	private JLabel status;
	private JLabel statusText;

	private JTable tabelaEquipamentos = new JTable();
	
	EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
	private ArrayList<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();
	private boolean cadastro = true;
	
	String colunas[] = { "ID", "Nome", "Músculo Trabalhado", "Estado" };
	DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

	public EquipamentoView() {
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

		titulo = new JLabel("Menu de Equipamentos");
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(titulo, gbc);
		id = new JLabel("ID");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(id, gbc);
		
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

		musculoTrab = new JLabel("Músculo Trabalhado ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(musculoTrab, gbc);
		
		inputMusculoTrab = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(inputMusculoTrab, gbc);

		estado = new JLabel("Estado ");
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(estado, gbc);

		inputEstado = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(inputEstado, gbc);

		salvar = new JButton("Salvar");
		gbc.gridx = 1;
		gbc.gridy = 5;
		add(salvar, gbc);

		tabelaEquipamentos.setModel(modelo);
		tabelaEquipamentos.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 6;
		add(new JScrollPane(tabelaEquipamentos), gbc);
		
		editar = new JButton("Editar");
		gbc.gridx = 1;
		gbc.gridy = 7;
		add(editar, gbc);
		
		excluir = new JButton("Excluir");
		gbc.gridx = 1;
		gbc.gridy = 8;
		add(excluir, gbc);
		
		status = new JLabel("Status: ");
		gbc.gridx = 0;
		gbc.gridy = 9;
		add(status, gbc);
		
		statusText = new JLabel("Listando Equipamentos");
		gbc.gridx = 1;
		gbc.gridy = 9;
		add(statusText, gbc);

	}
	
	private void organizarEventos() {
		carregarListaEquipamentos();
		salvar.addActionListener((event) -> {
			if (inputNome.getText().isEmpty() || inputMusculoTrab.getText().isEmpty() || inputEstado.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String nome = inputNome.getText();
			String musculoTrab = inputMusculoTrab.getText();
			String estado = inputEstado.getText();
			
			Equipamento equipamento = new Equipamento();
			equipamento.setNome(nome);
			equipamento.setMusculoTrab(musculoTrab);
			equipamento.setEstado(estado);
			
			if(cadastro) {
				statusText.setText("");
				statusText.setText("Cadastrando Equipamento");
				
				if(equipamentoDAO.salvarEquipamento(equipamento)) {
					JOptionPane.showMessageDialog(this, "Equipamento cadastrado com sucesso.");
				} else {
					JOptionPane.showMessageDialog(this, "Erro ao cadastrar Equipamento.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
				carregarListaEquipamentos();
				inputNome.setText("");
				inputMusculoTrab.setText("");
				inputEstado.setText("");
			} else {
				int idEquipamento = Integer.parseInt(inputID.getText());
				equipamento.setId(idEquipamento);
				
				if(equipamentoDAO.editarEquipamento(equipamento)) {
					JOptionPane.showMessageDialog(this, "Equipamento editado com sucesso.");
				} else {
					JOptionPane.showMessageDialog(this, "Erro ao editar Equipamento.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
				carregarListaEquipamentos();
				
				inputID.setText("");
				inputNome.setText("");
				inputMusculoTrab.setText("");
				inputEstado.setText("");
				
				cadastro = true;
			}
		});

		editar.addActionListener((event) -> {
			cadastro = false;
			int linhaSelecionada = tabelaEquipamentos.getSelectedRow();
			Equipamento equipamentoCarregado = new Equipamento();
			
			if(linhaSelecionada != -1) {
				int idEquipamento = (int) tabelaEquipamentos.getValueAt(linhaSelecionada, 0);
				
				equipamentoCarregado = equipamentoDAO.getEquipamento(idEquipamento);
				
				inputID.setText(String.valueOf(equipamentoCarregado.getId()));
				inputNome.setText(equipamentoCarregado.getNome());
				inputMusculoTrab.setText(equipamentoCarregado.getMusculoTrab());
				inputEstado.setText(equipamentoCarregado.getEstado());
				
				statusText.setText("");
				statusText.setText("Editando Equipamento");
				
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um equipamento para ser editado.", "Erro", JOptionPane.ERROR_MESSAGE);
			}

		});

		excluir.addActionListener((event) -> {
			int linhaSelecionada = tabelaEquipamentos.getSelectedRow();
			
			
			if (linhaSelecionada != -1) {
				int idEquipamento = (int) tabelaEquipamentos.getValueAt(linhaSelecionada, 0);
				
				statusText.setText("");
				statusText.setText("Excluindo Equipamento");
				
				if(equipamentoDAO.removerEquipamento(idEquipamento)) {
					
					carregarListaEquipamentos();
					JOptionPane.showMessageDialog(this, "Equipamento excluído com sucesso.");
				} else {
					JOptionPane.showMessageDialog(this, "Erro ao excluir instrutor.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um equipamento para ser excluído.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
		
	}
	
	public void carregarListaEquipamentos() {
		listaEquipamentos = equipamentoDAO.listarEquipamentos();
		
		modelo.setRowCount(0);
		
		for (int i = 0; i < listaEquipamentos.size(); i++) {
			Object[] lista = { 
					listaEquipamentos.get(i).getId(),
					listaEquipamentos.get(i).getNome(), 
					listaEquipamentos.get(i).getMusculoTrab(), 
					listaEquipamentos.get(i).getEstado(), 
			};
	
			modelo.addRow(lista);
		}
	}
	
}
