package util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Valida {// inicio da classe

	public static boolean verificaStringVazio(String args) {// inicio metodo
		if (args.trim().equals("")) {// inicio do if
			return true;
		} else {
			return false;
		}// fim do if

	}// fim do metodo

	public static boolean verificaIntZero(int args) {// inicio do metodo
		if (args == 0) {// if
			return true;
		} else {
			return false;

		}// fim do if
	}// inicio do metodo

	public static boolean verificaDoubleZero(double args) {// inicio metodo
		if (args == 0) {// inicio do if
			return true;
		} else {
			return false;
		}// fim do if
	}// fim metodo

	public static boolean verificaNumeros(String string) {
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(string);

		if (matcher.find()) {// inicio do if
			return true;
		} else {
			return false;
		}// fim do if

	}// fim do metodo

	public static boolean verificaCpfVazio(String cpf) {// inicio do metodo
		if (cpf.equals("   .   .   -  ")) {// inicio do if
			return true;
		} else {
			return false;
		}// fim do if
	}// fim do metodo

	public static boolean verificaRgVazio(String rg) {// inicio do metodo
		if (rg.equals("  .   .   - ")) {// inicio do if
			return true;
		} else {
			return false;
		}// fim do if

	}// fim do metodo

	public static boolean verificaDia(int args) {
		if (args > 0 || args <= 31) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean verificaMes(int args) {
		if (args > 0 && args <= 12) {
			return true;

		} else {
			return false;
		}
	}

	public static boolean verificaAno(int args) {

		Calendar cal = GregorianCalendar.getInstance();

		if (args > cal.get(Calendar.YEAR)) {
			return true;
		} else
			return false;
	}

	public static boolean verificaDataVazio(String data) {// inicio do metodo
		if (data.equals("  /  /    ")) {// inicio do if
			return true;
		} else {
			return false;
		}// fim do if

	}// fim do metodo]

	public static String validaData(int[] args) {
		String retorno = "";
		if (!verificaDia(args[0])) {
			retorno += Mensagem.diaInvalido;
		}
		if (!verificaMes(args[1])) {
			retorno += Mensagem.mesInvalido;
		}
		if (verificaAno(args[2])) {
			retorno += Mensagem.anoInvalido;
		}
		return retorno;
	}

}// fim da classe
