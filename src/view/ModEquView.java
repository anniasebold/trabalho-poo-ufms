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
import DAO.ModEquDAO;
import DAO.ModalidadeDAO;
import model.ModEqu;

public class ModEquView extends JPanel {

	private JLabel titulo;
	private JLabel id;
	private JLabel idModalidade;
	private JLabel idEquipamento;
	private JTextField inputID;
	private JTextField inputIdModalidade;
	private JTextField inputIdEquipamento;
	private JButton salvar;
	private JButton editar;
	private JButton excluir;

	private JTable tabelaModEqu = new JTable();
	
	ArrayList<ModEqu> listaModEqu = new ArrayList<>();
	EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
	ModalidadeDAO modalidadeDAO = new ModalidadeDAO();
	ModEquDAO modEquDAO = new ModEquDAO();
	private boolean cadastro = true;
	
	String colunas[] = { "ID", "ID Mod.", "ID Equ.", "Nome Modali.", "Nome Equip." };
	DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

	public ModEquView() {
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

		titulo = new JLabel("Equipamento por Modalidade");
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

		idModalidade = new JLabel("ID Modalidade ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(idModalidade, gbc);

		inputIdModalidade = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(inputIdModalidade, gbc);

		idEquipamento = new JLabel("ID Equipamento ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(idEquipamento, gbc);
		
		inputIdEquipamento = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(inputIdEquipamento, gbc);

		salvar = new JButton("Salvar");
		gbc.gridx = 1;
		gbc.gridy = 5;
		add(salvar, gbc);

		tabelaModEqu.setModel(modelo);
		tabelaModEqu.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 6;
		add(new JScrollPane(tabelaModEqu), gbc);
		
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
		carregarListaModEqu();
		salvar.addActionListener((event) -> {
			if (inputIdModalidade.getText().isEmpty() || inputIdEquipamento.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			ModEqu modEqu = new ModEqu();
			
			int idModalidade = Integer.parseInt(inputIdModalidade.getText());
			int idEquipamento = Integer.parseInt(inputIdEquipamento.getText());
			
			if(modalidadeDAO.getModalidade(idModalidade) == null) {
				JOptionPane.showMessageDialog(this, "Verifique o ID da modalidade e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			} else if(equipamentoDAO.getEquipamento(idEquipamento) == null) {
				JOptionPane.showMessageDialog(this, "Verifique o ID do equipamento e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				modEqu.setIdModalidade(idModalidade);
				modEqu.setIdEquipamento(idEquipamento);
				
				if(cadastro) {
					if(modEquDAO.salvarModEqu(modEqu)) {
						inputIdModalidade.setText("");
						inputIdEquipamento.setText("");
						JOptionPane.showMessageDialog(this, "Vínculo de Equipamento com Modalidade realizado com sucesso.");
						
						carregarListaModEqu();
					} else {
						JOptionPane.showMessageDialog(this, "Erro ao cadastrar vínculo de equipamento com modalidade.",
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
					
				} else {
					int id = Integer.parseInt(inputID.getText());
					modEqu.setId(id);
					
					if(modEquDAO.editarModEqu(modEqu)) {
						JOptionPane.showMessageDialog(this, "Registro editado com sucesso.");
						
						inputID.setText("");
						inputIdModalidade.setText("");
						inputIdEquipamento.setText("");
						
						carregarListaModEqu();
						cadastro = true;
					} else {
						JOptionPane.showMessageDialog(this, "Erro ao editar registro.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});

		editar.addActionListener((event) -> {
			cadastro = false;
			int linhaSelecionada = tabelaModEqu.getSelectedRow();
			ModEqu modEqu = new ModEqu();
			
			if (linhaSelecionada != -1) {
				int id = (int) tabelaModEqu.getValueAt(linhaSelecionada, 0);
				
				modEqu = modEquDAO.getModEqu(id);
				
				inputID.setText(String.valueOf(modEqu.getId()));
				inputIdModalidade.setText(String.valueOf(modEqu.getIdModalidade()));
				inputIdEquipamento.setText(String.valueOf(modEqu.getIdEquipamento()));
				
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um registro para ser editado.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		});

		excluir.addActionListener((event) -> {
			int linhaSelecionada = tabelaModEqu.getSelectedRow();
			
			if (linhaSelecionada != -1) {
				int id = (int) tabelaModEqu.getValueAt(linhaSelecionada, 0);
				
				if(modEquDAO.removerModEqu(id)) {
					JOptionPane.showMessageDialog(this, "Registro excluído com sucesso.");
					carregarListaModEqu();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um registro para ser excluído", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
		
	}
	
	public void carregarListaModEqu() {
		listaModEqu = modEquDAO.listarModalidadeEquipamento();
		
		modelo.setRowCount(0);
		
		for(int i = 0; i < listaModEqu.size(); i++) {
			Object[] lista = {
					listaModEqu.get(i).getId(),
					listaModEqu.get(i).getIdModalidade(),
					listaModEqu.get(i).getIdEquipamento(),
					listaModEqu.get(i).getNomeModalidade(),
					listaModEqu.get(i).getNomeEquipamento()
			};
			
			modelo.addRow(lista);
		}
	}
	
}
