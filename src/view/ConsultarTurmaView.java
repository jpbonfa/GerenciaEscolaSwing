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

import util.Mensagem;

import model.Aluno;
import model.Turma;
import controller.TurmaController;

/**
 * Classe responsavel por carmazenar os componentes da tela de consulta da turma
 * 
 * @author JPJBonfa
 * @since 03/06/2020
 */
public class ConsultarTurmaView {

	// criação das variaveis
	private JFrame janela;
	private JPanel painelDaJanela;
	private JLabel lbTurmas;
	private JLabel lbProfessor;
	private JLabel lbMateria;
	private JLabel lbConsutaDeTurma;
	private JTable tbTabela;
	private JScrollPane scroll;
	private JButton btConsultar;
	private JButton btCancelar;
	private JComboBox cbTurma;
	private JTextField tfProfessor;
	private JTextField tfMateria;
	private String coluna[] = { "Codigo", "Nome", "Data de nascimento" };
	private String dados[][] = { { "" } };

	private ArrayList<Turma> listaTurmaCombo = new ArrayList<Turma>();

	public void iniciaGui() {

		// criação das instancias
		janela = new JFrame();
		painelDaJanela = (JPanel) janela.getContentPane();
		lbTurmas = new JLabel();
		lbProfessor = new JLabel();
		lbMateria = new JLabel();
		lbConsutaDeTurma = new JLabel();
		tbTabela = new JTable();
		btConsultar = new JButton();
		btCancelar = new JButton();
		cbTurma = new JComboBox();
		tfMateria = new JTextField();
		tfProfessor = new JTextField();

		// Configurações dos textos das labels
		lbTurmas.setText("Turmas:");
		lbProfessor.setText("Professor:");
		lbMateria.setText("Materia:");
		lbConsutaDeTurma.setText("Consulta de Turma");

		// Configurações das coordenadas das labels
		lbTurmas.setBounds(40, 70, 50, 20);
		lbConsutaDeTurma.setBounds(200, 25, 200, 20);
		lbConsutaDeTurma.setFont(new Font("Times new Roman", Font.BOLD, 18));
		lbProfessor.setBounds(37, 100, 65, 20);
		lbMateria.setBounds(40, 130, 60, 20);

		// Configurações da ComboBox
		cbTurma.setBounds(100, 70, 300, 30);

		// Configurações dos botões
		btConsultar.setText("Consultar");
		btCancelar.setText("Cancelar");
		btConsultar.setBounds(430, 70, 100, 20);
		btCancelar.setBounds(220, 280, 100, 20);

		// configurações das ações do botão
		btConsultar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				consultar();

			}
		});

		btCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				janela.setVisible(false);
			}
		});

		// Configurações das textfields
		tfProfessor.setBounds(100, 100, 430, 30);
		tfMateria.setBounds(100, 130, 430, 30);

		// configurações da tabela
		DefaultTableModel modelo = new DefaultTableModel(dados, coluna);
		tbTabela = new JTable(modelo);
		tbTabela.setEnabled(true);
		tbTabela.setBounds(80, 280, 400, 200);

		// Configurações do scroll
		scroll = new JScrollPane(tbTabela);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(40, 160, 492, 100);

		carregarComboTurmas();

		// Configurações do painel da janela
		painelDaJanela.setLayout(null);
		painelDaJanela.add(lbTurmas);
		painelDaJanela.add(lbConsutaDeTurma);
		painelDaJanela.add(cbTurma);
		painelDaJanela.add(btConsultar);
		painelDaJanela.add(lbProfessor);
		painelDaJanela.add(tfProfessor);
		painelDaJanela.add(lbMateria);
		painelDaJanela.add(tfMateria);
		painelDaJanela.add(scroll);
		painelDaJanela.add(btCancelar);

		// Configurações da janela
		janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		janela.setTitle("Consulta de turma");
		janela.setSize(600, 370);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);

	}

	public void carregarComboTurmas() {
		cbTurma.addItem(" - Selecione uma turma - ");
		for (Turma turma : new TurmaController().buscarTodos()) {
			cbTurma.addItem(turma.getTurma());
			listaTurmaCombo.add(turma);
		}
	}

	public void consultar() {
		if (cbTurma.getSelectedIndex() == 0) {

			JOptionPane.showMessageDialog(null, Mensagem.selecioneTurma,
					Mensagem.erro, 0);

		} else {
			Turma turma = listaTurmaCombo.get(cbTurma.getSelectedIndex() - 1);
			tfProfessor.setText(turma.getProfessor());
			tfMateria.setText(turma.getMateria());
			// Obtendo o modelo da tabela ja criada
			DefaultTableModel modelo = (DefaultTableModel) tbTabela.getModel();
			modelo.setRowCount(0);
			for (Aluno aluno : turma.getAlunos()) {
				modelo.addRow(new String[] { aluno.getCodigo() + "",
						aluno.getNome(), aluno.getDataNascimento() + "" });
			}

		}
	}

}
