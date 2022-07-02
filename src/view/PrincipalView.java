package view;

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
	private JMenuItem sairSistema;
	private JMenuItem opcoesModEqu;
	private JPanel telaSelecionada;
	
	ModalidadeView modalidadeView = new ModalidadeView();
	ModEquView modEquView = new ModEquView();
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
		setSize(700, 850);
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
		
		opcoesModEqu = new JMenuItem("Equipamento por Modalidade");
		
		sair = new JMenu("Sair");
		sairSistema = new JMenuItem("Sair do sistema");
		
		telaSelecionada = new JPanel();

		this.setJMenuBar(barraTopo);

		add(barraTopo, BorderLayout.PAGE_START);
		barraTopo.add(modalidade);
		modalidade.add(opcoesModalidade);
		modalidade.add(opcoesModEqu);
		
		barraTopo.add(aluno);
		aluno.add(opcoesAluno);
		
		barraTopo.add(equipamento);
		equipamento.add(opcoesEquipamento);
		
		barraTopo.add(instrutor);
		instrutor.add(opcoesInstrutor);
		
		barraTopo.add(sair);
		sair.add(sairSistema);

		add(telaSelecionada);
	}

	private void organizarEventos() {
		opcoesModalidade.addActionListener((event) -> {
			alunoView.setVisible(false);
			instrutorView.setVisible(false);
			equipamentoView.setVisible(false);
			modEquView.setVisible(false);
			telaSelecionada.add(modalidadeView);
			modalidadeView.setVisible(true);
		});
		
		opcoesModEqu.addActionListener((event) -> {
			alunoView.setVisible(false);
			instrutorView.setVisible(false);
			equipamentoView.setVisible(false);
			modalidadeView.setVisible(false);
			telaSelecionada.add(modEquView);
			modEquView.setVisible(true);
		});

		opcoesAluno.addActionListener((event) -> {
			modalidadeView.setVisible(false);
			instrutorView.setVisible(false);
			equipamentoView.setVisible(false);
			modEquView.setVisible(false);
			telaSelecionada.add(alunoView);
			alunoView.setVisible(true);
		});
		
		opcoesInstrutor.addActionListener((event) -> {
			modalidadeView.setVisible(false);
			alunoView.setVisible(false);
			equipamentoView.setVisible(false);
			modEquView.setVisible(false);
			telaSelecionada.add(instrutorView);
			instrutorView.setVisible(true);
		});
		
		opcoesEquipamento.addActionListener((event) -> {
			modalidadeView.setVisible(false);
			alunoView.setVisible(false);
			instrutorView.setVisible(false);
			modEquView.setVisible(false);
			telaSelecionada.add(equipamentoView);
			equipamentoView.setVisible(true);
		});

		sairSistema.addActionListener((event) -> {
			System.exit(0);
		});
	}

	private void finalizar() {
		setVisible(true);
	}

}
