package util;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

public class Util {

	public static String informeMascara(String mask, String msg, String title)
			throws ParseException {// inicio do metodo
		// variavel de retorno do metodo
		String retorno = null;
		// criar a mensagem a ser exibida
		JLabel label = new JLabel(msg);
		// cria a mascara utilizada no componente
		JFormattedTextField fmt = new JFormattedTextField(new MaskFormatter(
				mask));

		JOptionPane.showConfirmDialog(null, new Object[] { label, fmt }, title,
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

		retorno = new String(fmt.getText());

		return retorno;

	}// fim do metodo

	public static int[] quebraData(String data) {// inicio do metodo
		// variavel do retorno do metodo
		int retorno[] = new int[3];
		// procedimento para separar os elementos da data recebida
		String aux[] = data.split("/");
		// valorizando posição 0 - dia
		retorno[0] = Integer.parseInt(aux[0]);
		// valorizando posição 1 -mês
		retorno[1] = Integer.parseInt(aux[1]);
		// valorizando posição 2 -ano
		retorno[2] = Integer.parseInt(aux[2]);

		return retorno;

	}// fim do metodo

	public static void mudarAparencia() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}