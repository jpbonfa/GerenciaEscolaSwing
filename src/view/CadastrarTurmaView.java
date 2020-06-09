package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Aluno;
import model.Professor;
import model.Turma;
import util.Mensagem;
import controller.AlunoController;
import controller.ProfessorController;
import controller.TurmaController;

/**
 * Classe responsavel por armzenar os componentes de tela do cadatro de turmas
 * 
 * @author JPJBonfa
 * @since 02/06/2020
 */

public class CadastrarTurmaView {

	private JFrame janela;
	private JPanel painelDaJanela;
	private JLabel lbCadastro;
	private JLabel lbProfessor;
	private JLabel lbMateria;
	private JLabel lbAluno;
	private JButton btIncluir;
	private JButton btExcluir;
	private JButton btSalvar;
	private JButton btCancelar;
	private JTable tbTabela;
	private JTextField tfMateria;
	private JScrollPane scroll;
	private JComboBox cbProfessor;
	private JComboBox cbAluno;
	private String professor[] = {};
	private String aluno[] = {};
	private String colunas[] = { "Codigo", "Nome", "Data Nascimento" };
	private String dados[][] = { {} };

	private ArrayList<Professor> listaProfessorCombo = new ArrayList<Professor>();
	private ArrayList<Aluno> listaAlunoCombo = new ArrayList<Aluno>();
	private ArrayList<Aluno> listaAlunoTabela = new ArrayList<Aluno>();
	private Professor professorTurma = new Professor();

	public void iniciaGui() {
		// criação de instancias
		janela = new JFrame();
		painelDaJanela = (JPanel) janela.getContentPane();
		lbCadastro = new JLabel();
		lbProfessor = new JLabel();
		lbMateria = new JLabel();
		lbAluno = new JLabel();
		btIncluir = new JButton();
		btExcluir = new JButton();
		btSalvar = new JButton();
		btCancelar = new JButton();
		tfMateria = new JTextField();
		tbTabela = new JTable();
		cbProfessor = new JComboBox(professor);
		cbAluno = new JComboBox(aluno);

		// Configurações dos textos das Labels
		lbCadastro.setText("Cadastrar turmas:");
		lbProfessor.setText("Professor:");
		lbMateria.setText("Materia:");
		lbAluno.setText("Aluno:");

		// Configurações das fontes das labels
		lbCadastro.setFont(new Font("Times new Roman", Font.BOLD, 20));
		lbCadastro.setFont(new Font("SansSerif", Font.BOLD, 20));

		// configuração das cordenadaas das Labels
		lbCadastro.setBounds(210, 10, 200, 100);
		lbProfessor.setBounds(30, 100, 100, 20);
		lbMateria.setBounds(30, 130, 100, 20);
		lbAluno.setBounds(30, 160, 100, 20);

		// configuraçoes da combo de professor
		cbProfessor.setSelectedIndex(-1);
		cbProfessor.setBounds(100, 100, 450, 20);

		// Configurando a ação da comboBox
		cbProfessor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				carregarTextFieldMateria();

			}
		});

		// Configurações da TextField
		tfMateria.setBounds(100, 130, 450, 25);

		// Configurações da comboBox do aluno
		cbAluno.setSelectedIndex(-1);
		cbAluno.setBounds(100, 160, 450, 20);

		// Configurações dos textos dos botões
		btIncluir.setText("Incluir aluno");
		btExcluir.setText("Excluir aluno");
		btSalvar.setText("Salvar");
		btCancelar.setText("Cancelar");

		// Configurações das coordenadas dos botões
		btIncluir.setBounds(170, 210, 110, 20);
		btExcluir.setBounds(290, 210, 110, 20);
		btSalvar.setBounds(180, 490, 100, 20);
		btCancelar.setBounds(290, 490, 100, 20);

		// configurando as ações dos botões
		btIncluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				adicionaLinha();

			}
		});

		btExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				excluiLinha();

			}
		});

		btSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				salvar();

			}
		});

		btCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				janela.setVisible(false);
			}
		});

		// configurações da tabela
		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
		modelo.removeRow(0);
		tbTabela = new JTable(modelo);
		tbTabela.setEnabled(true);
		tbTabela.setBounds(80, 280, 400, 200);

		// configurações do scroll
		scroll = new JScrollPane(tbTabela);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(30, 260, 520, 200);

		carregarComboProfessor();
		carregarComboAluno();

		// configurçoes do painel da janela
		painelDaJanela.setLayout(null);
		painelDaJanela.add(lbCadastro);
		painelDaJanela.add(lbProfessor);
		painelDaJanela.add(cbProfessor);
		painelDaJanela.add(lbMateria);
		painelDaJanela.add(tfMateria);
		painelDaJanela.add(lbAluno);
		painelDaJanela.add(cbAluno);
		painelDaJanela.add(btIncluir);
		painelDaJanela.add(btExcluir);
		painelDaJanela.add(btSalvar);
		painelDaJanela.add(btCancelar);
		painelDaJanela.add(scroll);

		// Configurações da janela
		janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		janela.setTitle("Cadastro de turma");
		janela.setSize(600, 580);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);

	}

	public void carregarComboProfessor() {
		cbProfessor.addItem(" - Selecione um professor - ");
		// carregamento dos dados na comboBox de professor lendo arquivo txt
		for (Professor professor : new ProfessorController().buscarTodos()) {
			cbProfessor.addItem(professor.getNome());
			listaProfessorCombo.add(professor);

		}

	}

	public void carregarComboAluno() {

		cbAluno.addItem(" - Selecione um aluno - ");
		// Carregamento dos dados na combo box do aluno

		for (Aluno aluno : new AlunoController().buscarTodos()) {
			cbAluno.addItem(aluno.getNome());
			listaAlunoCombo.add(aluno);
		}
	}

	public void carregarTextFieldMateria() {

		// Carregamento dos dados na TextField da materia do professor
		// seliconado na comboBox
		if (cbProfessor.getSelectedIndex() != 0) {
			professorTurma = listaProfessorCombo.get(cbProfessor
					.getSelectedIndex() - 1);
			tfMateria.setText(professorTurma.getMateria());
		}

	}

	public void adicionaLinha() {

		if (cbAluno.getSelectedIndex() != 0) {
			// Obtendo o modelo da tabela ja criada
			DefaultTableModel modelo = (DefaultTableModel) tbTabela.getModel();
			// Adicioando a linha com os dados
			Aluno aluno = listaAlunoCombo.get(cbAluno.getSelectedIndex() - 1);
			modelo.addRow(new String[] { aluno.getCodigo() + "",
					aluno.getNome(), aluno.getDataNascimento() + "" });
			listaAlunoTabela.add(aluno);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.selecioneAluno,
					Mensagem.erro, 0);
		}
	}

	public void excluiLinha() {
		if (tbTabela.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, Mensagem.selecioneLinha);
		} else if (tbTabela.getSelectedRowCount() > 1) {
			JOptionPane.showMessageDialog(null,  Mensagem.selecioneUmaLinha);

		} else {
			// Obtendo o modelo da tabela ja criada
			DefaultTableModel modelo = (DefaultTableModel) tbTabela.getModel();
			Aluno aluno = listaAlunoCombo.get(tbTabela.getSelectedRow());

			// excluindo a linha selecionada
			modelo.removeRow(tbTabela.getSelectedRow());
			listaAlunoTabela.remove(aluno);

			JOptionPane.showMessageDialog(null, Mensagem.aluno + Mensagem.excluido);

		}

	}

	public void salvar() {
		if (validarDados()) {
			// Processamento para salvar a turma
			Turma turma = new Turma();
			turma.setTurma(tfMateria.getText() + " - "
					+ professorTurma.getNome());
			turma.setProfessor(professorTurma.getNome());
			turma.setMateria(tfMateria.getText());
			turma.setAlunos(listaAlunoTabela);
			new TurmaController().gravarTurma(turma);
			limparDados();

		}

	}

	public boolean validarDados() {

		if (cbProfessor.getSelectedIndex() == 0) {

			JOptionPane.showMessageDialog(null, Mensagem.selecioneProfessor
					+ Mensagem.campoObrigatorio, Mensagem.erro, 0);
			return false;
		}

		if (listaAlunoTabela.size() == 0) {
			JOptionPane.showMessageDialog(null, Mensagem.selecioneAluno,
					Mensagem.erro, 0);
			return false;
		}

		return true;
	}
	public void limparDados(){
		cbProfessor.setSelectedIndex(0);
		tfMateria.setText(null);
		cbAluno.setSelectedIndex(0);
		DefaultTableModel modelo = (DefaultTableModel) tbTabela.getModel();
		modelo.setNumRows(0);
		listaAlunoTabela.clear();
		
	}

}
