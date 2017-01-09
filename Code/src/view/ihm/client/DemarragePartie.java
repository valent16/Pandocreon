package view.ihm.client;

import javax.swing.JPanel;

import controller.GameController;

/**Classe qui lance la partie de maniere graphique*/
public class DemarragePartie extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**Lance la partie*/
	public DemarragePartie(){
		GameController gameController1 = new GameController();
		gameController1.startGame();

		gameController1.CreationJoueur("valentin", 18);
		gameController1.CreationJoueur("David", 20);

		gameController1.lancerPartie();
		
	}

	/**Getter
	 * @return le panel MenuPrincipale
	 */
	public JPanel getPanel(){
		return this;
	}
}
