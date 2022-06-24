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

import DAO.InstrutorDAO;
import model.Instrutor;

public class InstrutorView extends JPanel {
	private JLabel titulo;
	private JLabel nome;
	private JLabel valor;
	private JLabel id;
	private JTextField inputId;
	private JTextField inputNome;
	private JTextField inputFormacao;
	private JButton salvar;
	private JButton editar;
	private JButton excluir;

	private JTable tabelaInstrutores = new JTable();

	ArrayList<Instrutor> listaInstrutores = new ArrayList<>();
	Instrutor instrutor = new Instrutor();
	InstrutorDAO instrutorDAO = new InstrutorDAO();

	private boolean cadastro = true;

	String colunas[] = { "ID", "Nome", "Formação" };
	DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

	public InstrutorView() {
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

		titulo = new JLabel("Menu de Instrutores");
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(titulo, gbc);

		id = new JLabel("ID ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(id, gbc);

		inputId = new JTextField(30);
		inputId.setEditable(false);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(inputId, gbc);

		nome = new JLabel("Nome ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(nome, gbc);

		inputNome = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(inputNome, gbc);

		valor = new JLabel("Formação ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(valor, gbc);

		inputFormacao = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(inputFormacao, gbc);

		salvar = new JButton("Salvar");
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(salvar, gbc);

		tabelaInstrutores.setModel(modelo);
		tabelaInstrutores.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 5;
		add(new JScrollPane(tabelaInstrutores), gbc);

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
		carregarListaInstrutores();

		salvar.addActionListener((event) -> {
			if (inputNome.getText().isEmpty() || inputFormacao.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String nome = inputNome.getText();
			String formacao = inputFormacao.getText();

			Instrutor instrutor = new Instrutor();
			instrutor.setNome(nome);
			instrutor.setFormacao(formacao);

			if (cadastro) {
				if (instrutorDAO.salvarInstrutor(instrutor)) {
					JOptionPane.showMessageDialog(this, "Instrutor cadastrado com sucesso.");
				} else {
					JOptionPane.showMessageDialog(this, "Erro ao cadastrar Instrutor.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

				carregarListaInstrutores();
				inputNome.setText("");
				inputFormacao.setText("");
			} else {

				int idInstrutor = Integer.parseInt(inputId.getText());
				instrutor.setId(idInstrutor);

				if (instrutorDAO.editarInstrutor(instrutor)) {
					JOptionPane.showMessageDialog(this, "Instrutor editado com sucesso.");
				} else {
					JOptionPane.showMessageDialog(this, "Erro ao editar Instrutor.", "Erro", JOptionPane.ERROR_MESSAGE);
				}

				carregarListaInstrutores();

				inputId.setText("");
				inputNome.setText("");
				inputFormacao.setText("");

				cadastro = true;
			}
		});

		editar.addActionListener((event) -> {
			cadastro = false;
			int linhaSelecionada = tabelaInstrutores.getSelectedRow();
			Instrutor instrutorCarregado = new Instrutor();

			if (linhaSelecionada != -1) {
				int idInstrutor = (int) tabelaInstrutores.getValueAt(linhaSelecionada, 0);

				instrutorCarregado = instrutorDAO.getInstrutor(idInstrutor);

				inputId.setText(String.valueOf(instrutorCarregado.getId()));
				inputNome.setText(instrutorCarregado.getNome());
				inputFormacao.setText(instrutorCarregado.getFormacao());

			} else {
				JOptionPane.showMessageDialog(this, "Selecione um instrutor para ser editado.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}

		});

		excluir.addActionListener((event) -> {
			int linhaSelecionada = tabelaInstrutores.getSelectedRow();

			if (linhaSelecionada != -1) {
				int idInstrutor = (int) tabelaInstrutores.getValueAt(linhaSelecionada, 0);

				if (instrutorDAO.removerInstrutor(idInstrutor)) {
					carregarListaInstrutores();

					JOptionPane.showMessageDialog(this, "Instrutor excluído com sucesso.");
				} else {
					JOptionPane.showMessageDialog(this, "Erro ao excluir instrutor.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um instrutor para ser excluído.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		});

	}

	private void carregarListaInstrutores() {
		listaInstrutores = instrutorDAO.listarInstrutores();

		// Limpa a tabela antes de carregar os usuários
		modelo.setRowCount(0);

		for (int i = 0; i < listaInstrutores.size(); i++) {
			Object[] lista = { listaInstrutores.get(i).getId(), listaInstrutores.get(i).getNome(),
					listaInstrutores.get(i).getFormacao() };
			modelo.addRow(lista);
		}

	}
}
