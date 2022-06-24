package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import connection.Conexao;

public class PrincipalView extends JFrame {

	private JMenuBar barraTopo;
	private JMenu modalidade;
	private JMenuItem opcoesModalidade;
	private JMenu aluno;
	private JMenuItem opcoesAluno;
	private JMenu equipamento;
	private JMenuItem opcoesEquipamento;
	private JMenu instrutor;
	private JMenuItem opcoesInstrutor;
	private JMenu sair;
	private JMenuItem exit;
	private JPanel telaSelecionada;
	private JMenuBar barraRodape;
	private JLabel status;
	
	ModalidadeView modalidadeView = new ModalidadeView();
	AlunoView alunoView = new AlunoView();
	InstrutorView instrutorView = new InstrutorView();
	EquipamentoView equipamentoView = new EquipamentoView();

	public PrincipalView() {
		super("Principal");
		prepararJanela();
		organizarComponentes();
		organizarEventos();
		finalizar();
	}

	private void prepararJanela() {
		setTitle("GymFit");
		setSize(750, 850);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(this);
	}

	private void organizarComponentes() {
		barraTopo = new JMenuBar();
		modalidade = new JMenu("Modalidade");
		opcoesModalidade = new JMenuItem("Menu");
		aluno = new JMenu("Aluno");
		opcoesAluno = new JMenuItem("Menu");
		equipamento = new JMenu("Equipamento");
		opcoesEquipamento = new JMenuItem("Menu");
		instrutor = new JMenu("Instrutor");
		opcoesInstrutor = new JMenuItem("Menu");
		sair = new JMenu("Sair");
		exit = new JMenuItem("Exit");
		telaSelecionada = new JPanel();
		barraRodape = new JMenuBar();
		status = new JLabel("Status: ");

		this.setJMenuBar(barraTopo);
		this.setJMenuBar(barraRodape);

		add(barraTopo, BorderLayout.PAGE_START);
		barraTopo.add(modalidade);
		modalidade.add(opcoesModalidade);
		barraTopo.add(aluno);
		aluno.add(opcoesAluno);
		barraTopo.add(equipamento);
		equipamento.add(opcoesEquipamento);
		barraTopo.add(instrutor);
		instrutor.add(opcoesInstrutor);
		barraTopo.add(sair);
		sair.add(exit);

		add(telaSelecionada);

		add(barraRodape, BorderLayout.PAGE_END);
		barraRodape.add(status);
	}

	private void organizarEventos() {
		opcoesModalidade.addActionListener((event) -> {
			alunoView.setVisible(false);
			instrutorView.setVisible(false);
			equipamentoView.setVisible(false);
			telaSelecionada.add(modalidadeView);
			modalidadeView.setVisible(true);
		});

		opcoesAluno.addActionListener((event) -> {
			modalidadeView.setVisible(false);
			instrutorView.setVisible(false);
			equipamentoView.setVisible(false);
			telaSelecionada.add(alunoView);
			alunoView.setVisible(true);
		});
		
		opcoesInstrutor.addActionListener((event) -> {
			modalidadeView.setVisible(false);
			alunoView.setVisible(false);
			equipamentoView.setVisible(false);
			telaSelecionada.add(instrutorView);
			instrutorView.setVisible(true);
		});
		
		opcoesEquipamento.addActionListener((event) -> {
			modalidadeView.setVisible(false);
			alunoView.setVisible(false);
			instrutorView.setVisible(false);
			telaSelecionada.add(equipamentoView);
			equipamentoView.setVisible(true);
		});

		exit.addActionListener((event) -> {
			System.exit(0);
		});
	}

	private void finalizar() {
		setVisible(true);
	}

}
