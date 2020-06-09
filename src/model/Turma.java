package model;

import java.util.ArrayList;

public class Turma {

	private String turma;
	private String professor;
	private String materia;
	private ArrayList<Aluno> alunos;
	

	public Turma() {
	}

	public Turma(String turma, String professor, String materia,
			ArrayList<Aluno> alunos) {
		this.turma = turma;
		this.professor = professor;
		this.materia = materia;
		this.alunos = alunos;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}

}
