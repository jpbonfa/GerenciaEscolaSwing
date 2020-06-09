package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Aluno;
import model.Data;
import model.Turma;
import util.Mensagem;
import util.Util;

public class TurmaController {

	public String nomeArquivo = "turma txt";

	public void gravarTurma(Turma turma) {// inicio do metodo
		try {// inicio do try
				// gera um objeto do tipo File(arquivo fisico), onde sera
				// informado
				// nome fisico do arquivo.
			File arquivo = new File(nomeArquivo);

			// gera um objeto do tipo FileOutputStream(arquivo logico
			// temporario)
			// utilizado para gerar o arquivo fisico
			FileOutputStream arquivoOutput = new FileOutputStream(arquivo, true);

			// gera um objeto auxiliar do tipo PrintStream para gravação do
			// dados do arquivo
			PrintStream gravador = new PrintStream(arquivoOutput);

			// processo de gravação dos dados
			gravador.print(turma.getTurma());
			gravador.print(";");
			gravador.print(turma.getProfessor());
			gravador.print(";");
			gravador.print(turma.getMateria());
			for (Aluno aluno : turma.getAlunos()) {
				gravador.print(";");
				gravador.print(aluno.getCodigo());
				gravador.print(";");
				gravador.print(aluno.getNome());
				gravador.print(";");
				gravador.print(aluno.getDataNascimento());
			}

			gravador.println("");

			// procedimentos finais de gravação do arquivo
			gravador.close();
			arquivoOutput.close();
			JOptionPane.showMessageDialog(null, Mensagem.Turma + Mensagem.gravado,
					Mensagem.sucesso, 2);

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, Mensagem.erroArquivo,
					Mensagem.erro, 0);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, Mensagem.erroFechaArquivo,
					Mensagem.erro, 0);
			e.printStackTrace();
		}// fim do try

	}// fim do metodo

	public ArrayList<Turma> buscarTodos() {// inicio do metodo

		// Lista de objetos do tipo turma retornado na leitura do
		// arquivio
		ArrayList<Turma> listaTurma = new ArrayList<Turma>();

		try {// inico do try
				// Gera um objeto InputStream para leitura do arquivo fisico
			InputStream is = new FileInputStream(nomeArquivo);

			// gera um objeto InputStreamReader para armazenar os bytes do
			// arquivo fisico
			InputStreamReader isr = new InputStreamReader(is);

			// gera um objeto auxiliar BufferedReader para ler os dados do
			// arquivo
			BufferedReader leitor = new BufferedReader(isr);
			String texto = leitor.readLine();

			while (texto != null) {// inicio do while
				
				ArrayList<Aluno> alunos = new ArrayList<Aluno>();

				Turma turma = new Turma();
				
				String dados[] = texto.split(";");
				turma.setTurma(dados[0]);
				turma.setProfessor(dados[1]);
				turma.setMateria(dados[2]);
				
				for (int i = 3; i < 99;) {
					try {
						if (dados[i] != null) {
							Aluno aluno = new Aluno();
							aluno.setCodigo(Integer.parseInt(dados[i]));
							aluno.setNome(dados[i + 1]);
							int aux[] = Util.quebraData(dados[i + 2]);
							Data data = new Data();
							data.setDia(aux[0]);
							data.setMes(aux[1]);
							data.setAno(aux[2]);
							aluno.setDataNascimento(data);
							i = i + 3;
							alunos.add(aluno);
						}
						
					} catch (Exception e) {
						break;
					}
				}
				turma.setAlunos(alunos);

				listaTurma.add(turma);

				texto = leitor.readLine();
			}// fim do while

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, Mensagem.erroEncontrarArquivo,
					Mensagem.erro, 0);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, Mensagem.erroLerArquivo,
					Mensagem.erro, 0);
			e.printStackTrace();
		}// fim do try

		return listaTurma;
	}// fim do metodo

}
