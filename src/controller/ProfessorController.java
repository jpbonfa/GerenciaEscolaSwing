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

import model.Professor;
import util.Mensagem;

public class ProfessorController {// inicio da
																// classe
	private String nomeArquivo = "professor.txt";
	
	public void gravarProfessor(Professor professor) {// inicio do metodo
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
			gravador.print(professor.getCodigo());
			gravador.print(";");
			gravador.print(professor.getNome());
			gravador.print(";");
			gravador.print(professor.getCpf());
			gravador.print(";");
			gravador.print(professor.getRg());
			gravador.print(";");
			gravador.print(professor.getSalario());
			gravador.print(";");
			gravador.print(professor.getMateria());
			gravador.println("");

			// procedimentos finais de gravação do arquivo
			gravador.close();
			arquivoOutput.close();
			JOptionPane.showMessageDialog(null, Mensagem.professor
					+ Mensagem.gravado, Mensagem.sucesso, 2);

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

	public ArrayList<Professor> buscarTodos() {// inicio do metodo

		// Lista de objetos do tipo professor retornado na leitura do
		// arquivio
		ArrayList<Professor> listaProfessor = new ArrayList<Professor>();

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
				Professor professor = new Professor();
				String dados[] = texto.split(";");
				professor.setCodigo(Integer.parseInt(dados[0]));
				professor.setNome(dados[1]);
				professor.setCpf(dados[2]);
				professor.setRg(dados[3]);
				professor.setSalario(Double.parseDouble(dados[4]));
				professor.setMateria(dados[5]);
				listaProfessor.add(professor);

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

		return listaProfessor;
	}// fim do metodo


}// fim da classe
