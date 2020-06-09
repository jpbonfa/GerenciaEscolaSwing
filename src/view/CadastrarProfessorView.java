package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ProfessorController;

import model.Professor;

import util.Mensagem;
import util.Valida;

/**
 * Classe responsavel por armazenar os componentes da tela de cadastro do
 * Professor
 * 
 * @author JPJBonfa
 * @since 30/05/2020
 */
public class CadastrarProfessorView {

	// DEclaração das variaveis
	private JFrame janela;
	private JPanel painelDaJanela;
	private JLabel lbCodigo;
	private JLabel lbNome;
	private JLabel lbCpf;
	private JLabel lbRg;
	private JLabel lbMateria;
	private JLabel lbSalario;
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JFormattedTextField tfCpf;
	private JFormattedTextField tfRg;
	private JTextField tfMateria;
	private JTextField tfSalario;
	private JButton btSalvar;
	private JButton btCancelar;

	// Declaração dos campos de formatação
	private MaskFormatter fmtCpf;
	private MaskFormatter fmtRg;

	public void iniciaGui() throws ParseException {

		// Criação das instancias de formato(DEVE SER A PRIMEIRA INSTANCIA)
		fmtCpf = new MaskFormatter("###.###.###-##");
		fmtRg = new MaskFormatter("##.###.###-#");

		// Criar Instancias
		janela = new JFrame();
		painelDaJanela = (JPanel) janela.getContentPane();
		lbCodigo = new JLabel();
		lbNome = new JLabel();
		lbCpf = new JLabel();
		lbRg = new JLabel();
		lbMateria = new JLabel();
		lbSalario = new JLabel();
		tfCodigo = new JTextField();
		tfNome = new JTextField();
		tfCpf = new JFormattedTextField(fmtCpf);
		tfRg = new JFormattedTextField(fmtRg);
		tfMateria = new JTextField();
		tfSalario = new JTextField();
		btSalvar = new JButton();
		btCancelar = new JButton();

		// Configurações dos textos das Labels
		lbCodigo.setText("Codigo:");
		lbNome.setText("Nome:");
		lbCpf.setText("Cpf:");
		lbRg.setText("Rg:");
		lbMateria.setText("Materia:");
		lbSalario.setText("Salario:");
		btSalvar.setText("Salvar");
		btCancelar.setText("Cancelar");

		// Configurações das cordenadas dos componentes
		lbCodigo.setBounds(20, 40, 50, 20);
		tfCodigo.setBounds(65, 40, 200, 30);
		lbNome.setBounds(20, 80, 50, 20);
		tfNome.setBounds(65, 80, 400, 30);
		lbCpf.setBounds(20, 120, 50, 20);
		tfCpf.setBounds(65, 120, 200, 30);
		lbRg.setBounds(300, 120, 50, 20);
		tfRg.setBounds(345, 120, 200, 30);
		lbMateria.setBounds(20, 160, 50, 20);
		tfMateria.setBounds(65, 160, 400, 30);
		lbSalario.setBounds(20, 200, 50, 20);
		tfSalario.setBounds(65, 200, 200, 30);
		btSalvar.setBounds(180, 270, 100, 20);
		btCancelar.setBounds(290, 270, 100, 20);

		// Configurações do painel da janela
		painelDaJanela.setLayout(null);
		painelDaJanela.add(lbCodigo);
		painelDaJanela.add(tfCodigo);
		painelDaJanela.add(lbNome);
		painelDaJanela.add(tfNome);
		painelDaJanela.add(lbCpf);
		painelDaJanela.add(tfCpf);
		painelDaJanela.add(lbRg);
		painelDaJanela.add(tfRg);
		painelDaJanela.add(lbMateria);
		painelDaJanela.add(tfMateria);
		painelDaJanela.add(lbSalario);
		painelDaJanela.add(tfSalario);
		painelDaJanela.add(btSalvar);
		painelDaJanela.add(btCancelar);

		// configurações das ações do botão
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

		// Configurações da janela
		janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		janela.setTitle("Cadastro de professor");
		janela.setSize(600, 370);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);

	}

	public void salvar() {
		if (validarDados()) {

			Professor professor = new Professor();
			professor.setCodigo(Integer.parseInt(tfCodigo.getText()));
			professor.setNome(tfNome.getText());
			professor.setCpf(tfCpf.getText());
			professor.setRg(tfRg.getText());
			professor.setSalario(Double.parseDouble(tfSalario.getText()));
			professor.setMateria(tfMateria.getText());

			new ProfessorController().gravarProfessor(professor);
			limparDados();
		}

	}

	public void limparDados() {
		tfCodigo.setText(null);
		tfNome.setText(null);
		tfCpf.setText(null);
		tfRg.setText(null);
		tfSalario.setText(null);
		tfMateria.setText(null);

	}

	public boolean validarDados() {
		if (tfCodigo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Mensagem.informe
					+ Mensagem.codigo + Mensagem.campoObrigatorio,
					Mensagem.erro, 0);
			return false;
		}
		if (tfNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Mensagem.informe
					+ Mensagem.nome + Mensagem.campoObrigatorio, Mensagem.erro,
					0);
			return false;
		}
		if (Valida.verificaCpfVazio(tfCpf.getText())) {
			JOptionPane.showMessageDialog(null, Mensagem.informe + Mensagem.cpf
					+ Mensagem.campoObrigatorio, Mensagem.erro, 0);
			return false;
		}
		if (Valida.verificaRgVazio(tfRg.getText())) {
			JOptionPane.showMessageDialog(null, Mensagem.informe + Mensagem.rg
					+ Mensagem.campoObrigatorio, Mensagem.erro, 0);
			return false;
		}
		if (tfMateria.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Mensagem.informe
					+ Mensagem.materia + Mensagem.campoObrigatorio,
					Mensagem.erro, 0);
			return false;
		}
		if (tfSalario.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Mensagem.informe
					+ Mensagem.salario + Mensagem.campoObrigatorio,
					Mensagem.erro, 0);
			return false;

		}

		return true;
	}

}
