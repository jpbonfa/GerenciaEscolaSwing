package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ProfessorController;

import model.Professor;

public class ConsultarProfessorView {
	// Declara�ao de atributos
	private JFrame janela;
	private JPanel painelDaJanela;
	private JLabel lbConsulta;
	private JTable tbTabela;
	private JScrollPane scroll;
	private JButton btCancelar;
	private String colunas[] = { "Codigo", "Nome", "Cpf", "Rg", "Salario",
			"Materia" };
	private String dados[][] = { { "" } };

	public void iniciaGui() {
		// Cria��o das instancias
		janela = new JFrame();
		painelDaJanela = (JPanel) janela.getContentPane();
		lbConsulta = new JLabel();
		btCancelar = new JButton();
		tbTabela = new JTable();

		// Configura��es de textos da label
		lbConsulta.setText("Consulta de Professores");
		lbConsulta.setFont(new Font("Times new Roman", Font.BOLD, 18));

		// configura��es de coordenadas das labels
		lbConsulta.setBounds(200, 20, 200, 20);

		// configura��es da tabela
		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
		tbTabela = new JTable(modelo);
		tbTabela.setEnabled(true);
		tbTabela.setBounds(80, 280, 400, 200);

		// configura��es do scroll
		scroll = new JScrollPane(tbTabela);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(30, 50, 520, 200);

		// configura��es dos bot�es
		btCancelar.setText("Cancelar");
		btCancelar.setBounds(235, 280, 100, 20);
		btCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				janela.setVisible(false);
			}
		});
		
		carregarTabela();

		// Configura��es do painel da janela
		painelDaJanela.setLayout(null);
		painelDaJanela.add(lbConsulta);
		painelDaJanela.add(scroll);
		painelDaJanela.add(btCancelar);

		// Configura��es da janela
		janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		janela.setTitle("Consulta de professor");
		janela.setSize(600, 370);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);

	}

	public void carregarTabela() {
		// Obtendo o modelo da tabela ja criada
		DefaultTableModel modelo = (DefaultTableModel) tbTabela.getModel();
		modelo.removeRow(0);
		
		for (Professor professor : new ProfessorController().buscarTodos()) {
			// Adicioando a linha com os dados
			modelo.addRow(new String[] { professor.getCodigo() + "",
					professor.getNome(), professor.getCpf(), professor.getRg(),
					professor.getSalario() + "", professor.getMateria() });
		}

	}

}
