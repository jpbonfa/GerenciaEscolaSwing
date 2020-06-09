package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Classe responsavel por carmazenar os componentes de menu
 * 
 * @author JPJBonfa
 * @since 02/06/2020
 */

public class MenuView {

	// Declaração de variaveis
	private JFrame janela;
	private JMenuBar barraMenu;
	private JMenu menuCadastro;
	private JMenu menuConsulta;
	private JMenu menuTurma;
	private JMenuItem itemMenuCadastroAluno;
	private JMenuItem itemMenuCadastroProfessor;
	private JMenuItem itemMenuCadastroSecretaria;
	private JMenuItem itemMenuCadastroFaxineira;
	private JMenuItem itemMenuConsultaAluno;
	private JMenuItem itemMenuConsultaProfessor;
	private JMenuItem itemMenuCadastraTurma;
	private JMenuItem itemMenuConsultaTurma;
	private JMenuItem itemMenuConsultaSecretaria;
	private JMenuItem itemMenuConsultaFaxineira;

	public void iniciaGui() {

		// Criação das instancias
		janela = new JFrame();
		barraMenu = new JMenuBar();
		menuCadastro = new JMenu();
		itemMenuCadastroAluno = new JMenuItem();
		itemMenuCadastroProfessor = new JMenuItem();
		itemMenuCadastroSecretaria = new JMenuItem();
		itemMenuCadastroFaxineira = new JMenuItem();
		menuConsulta = new JMenu();
		itemMenuConsultaAluno = new JMenuItem();
		itemMenuConsultaProfessor = new JMenuItem();
		itemMenuConsultaSecretaria = new JMenuItem();
		itemMenuConsultaFaxineira = new JMenuItem();
		menuTurma = new JMenu();
		itemMenuCadastraTurma = new JMenuItem();
		itemMenuConsultaTurma = new JMenuItem();

		// Adcionando os rotulos no menus
		menuCadastro.setText("Cadastro");
		menuConsulta.setText("Consulta");
		menuTurma.setText("Turma");
		

		// configurações dos textos dos botões
		itemMenuCadastroAluno.setText("Aluno");
		itemMenuCadastroProfessor.setText("Professor");
		itemMenuCadastroSecretaria.setText("Secretaria");
		itemMenuCadastroFaxineira.setText("Faxineira");
		itemMenuConsultaAluno.setText("Aluno");
		itemMenuConsultaProfessor.setText("Professor");
		itemMenuConsultaSecretaria.setText("Secretaria");
		itemMenuConsultaFaxineira.setText("Faxineira");
		itemMenuCadastraTurma.setText("Cadastrar turma");
		itemMenuConsultaTurma.setText("Consultar turma");

		// Adicionando os menus a barra de menu
		barraMenu.add(menuCadastro);
		barraMenu.add(menuConsulta);
		barraMenu.add(menuTurma);

		// Adicionando os itens de menu no menuCadastro
		menuCadastro.add(itemMenuCadastroAluno);
		menuCadastro.add(itemMenuCadastroProfessor);
		menuCadastro.add(itemMenuCadastroSecretaria);
		menuCadastro.add(itemMenuCadastroFaxineira);

		// Adicionando os itens de menu no menuConsulta
		menuConsulta.add(itemMenuConsultaAluno);
		menuConsulta.add(itemMenuConsultaProfessor);
		menuConsulta.add(itemMenuConsultaSecretaria);
		menuConsulta.add(itemMenuConsultaFaxineira);
		
		//adicionando os itens de menu no menu turma
		menuTurma.add(itemMenuCadastraTurma);
		menuTurma.add(itemMenuConsultaTurma);

		// Configurando as ações dos itens de menuConsulta
		itemMenuConsultaAluno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConsultarAlunoView().iniciaGui();

			}
		});
		itemMenuConsultaProfessor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConsultarProfessorView().iniciaGui();
			}
		});
		itemMenuConsultaSecretaria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConsultarSecretariaView().iniciaGui();
			}
		});
		itemMenuConsultaFaxineira.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConsultarFaxineiraView().iniciaGui();
			}
		});

		// Configurando as ações dos itens de menuCadastro
		itemMenuCadastroAluno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new CadastrarAlunoView().iniciaGui();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		itemMenuCadastroProfessor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new CadastrarProfessorView().iniciaGui();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		itemMenuCadastroSecretaria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new CadastrarSecretariaView().iniciaGui();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		itemMenuCadastroFaxineira.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new CadastrarFaxineiraView().iniciaGui();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		itemMenuCadastraTurma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CadastrarTurmaView().iniciaGui();
				
			}
		});
		itemMenuConsultaTurma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConsultarTurmaView().iniciaGui();
			}
		});

		// Configuraçoes da janela
		janela.setJMenuBar(barraMenu);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setTitle("Gerencia Escola");
		//janela.setSize(600, 600);
		janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//janela.setLocationRelativeTo(null);
		janela.setVisible(true);
	}

}
