import util.Util;
import view.MenuView;

/**
 * Classe responsavel por executar o programa
 * 
 * @author JPJBonfa
 * @since 30/05/2020
 */
public class Main {

	public static void main(String[] args) {
	
		Util.mudarAparencia();
		new MenuView().iniciaGui();
	}

}
