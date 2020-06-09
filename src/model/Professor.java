package model;

public class Professor extends Funcionario {

	private String materia;

	public Professor() {

	}

	public Professor(String materia) {
		super();
		this.materia = materia;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}
	
	
	
}
