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

import model.Aluno;
import model.Data;
import model.Secretaria;
import controller.AlunoController;
import controller.SecretariaController;

import util.Mensagem;
import util.Util;
import util.Valida;

/**
 * Classe responsavel por armazenar os componentes da tela de cadastro da
 * secretaria
 * 
 * @author JPJBonfa
 * @since 30/05/2020
 */
public class CadastrarAlunoView {

	// Declaração das variaveis
	private JFrame janela;
	private JPanel painelDaJanela;
	private JLabel lbCodigo;
	private JLabel lbNome;
	private JLabel lbCpf;
	private JLabel lbRg;
	private JLabel lbData;
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JFormattedTextField tfData;
	private JButton btSalvar;
	private JButton btCancelar;
	private JFormattedTextField tfCpf;
	private JFormattedTextField tfRg;
	private MaskFormatter fmtCpf;
	private MaskFormatter fmtRg;
	private MaskFormatter fmtDataNascimento;

	public void iniciaGui() throws ParseException {

		// Criação das instancias de formato(DEVE SER A PRIMEIRA INSTANCIA)
		fmtCpf = new MaskFormatter("###.###.###-##");
		fmtRg = new MaskFormatter("##.###.###-#");
		fmtDataNascimento = new MaskFormatter("##/##/####");

		// Criar Instancias
		janela = new JFrame();
		painelDaJanela = (JPanel) janela.getContentPane();
		lbCodigo = new JLabel();
		lbNome = new JLabel();
		lbCpf = new JLabel();
		lbRg = new JLabel();
		lbData = new JLabel();
		tfCodigo = new JTextField();
		tfNome = new JTextField();
		tfCpf = new JFormattedTextField(fmtCpf);
		tfRg = new JFormattedTextField(fmtRg);
		tfData = new JFormattedTextField(fmtDataNascimento);
		btSalvar = new JButton();
		btCancelar = new JButton();

		// Configurações dos textos das Labels
		lbCodigo.setText("Codigo:");
		lbNome.setText("Nome:");
		lbCpf.setText("Cpf:");
		lbRg.setText("Rg:");
		lbData.setText("Data de nascimento:");
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
		lbData.setBounds(20, 160, 130, 20);
		tfData.setBounds(150, 160, 200, 30);
		btSalvar.setBounds(180, 270, 100, 20);
		btCancelar.setBounds(290, 270, 100, 20);

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
		painelDaJanela.add(lbData);
		painelDaJanela.add(tfData);
		painelDaJanela.add(btSalvar);
		painelDaJanela.add(btCancelar);

		// Configurações da janela
		janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		janela.setTitle("Cadastro de aluno");
		janela.setSize(600, 370);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);


	}

	public void salvar() {
		if (validarDados()) {
			// gravar a Aluno
			Aluno aluno = new Aluno();
			aluno.setCodigo(Integer.parseInt(tfCodigo.getText()));
			aluno.setNome(tfNome.getText());
			aluno.setCpf(tfCpf.getText());
			aluno.setRg(tfRg.getText());
			Data data = new Data();
			
			int aux[] = Util.quebraData(tfData.getText());
			data.setDia(aux[0]);
			data.setMes(aux[1]);
			data.setAno(aux[2]);
			aluno.setDataNascimento(data);

			new AlunoController().gravarAluno(aluno);
			limparDados();

		}
	}

	public void limparDados() {
		tfCodigo.setText(null);
		tfNome.setText(null);
		tfCpf.setText(null);
		tfRg.setText(null);
		tfData.setText(null);

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
		if (Valida.verificaDataVazio(tfData.getText())) {
			JOptionPane.showMessageDialog(null, Mensagem.informe
					+ Mensagem.data + Mensagem.campoObrigatorio, Mensagem.erro,
					0);
			return false;
		}

		return true;
	}

}
