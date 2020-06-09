package model;

public class Secretaria extends Funcionario {

	private String ramal;
	
	public Secretaria() {
	
	}

	public Secretaria(String ramal) {
		super();
		this.ramal = ramal;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

}
