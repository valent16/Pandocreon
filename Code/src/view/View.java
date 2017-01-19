package view;

import controller.GameController;
import model.game.Game;
/**Class abstraite qui gere les differnetes vue*/
public abstract class View {
	
	/**Attribut qui est le controller de la partie*/
	GameController controllerJeu;
	
	/**Attribut qui represente la partie avec toutes ses donnes*/
	Game game;
	
	/**Constructeur
	 * @param gameController le controller de partie
	 * @param jeu le jeu
	 */
	public View(GameController gameController, Game jeu){
		this.controllerJeu = gameController;
		this.game = jeu;
	}
	
	/**Methode permettant de revenir au menu principal*/
	public abstract void menuPrincipal();
	
	/**Methode permettant de lancer une nouvelle partie*/
	public abstract void jouerNouvellePartie();
	
	
	
}
