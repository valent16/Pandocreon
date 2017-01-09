package view;

import controller.GameController;
import model.game.Game;

public abstract class View {
	GameController controllerJeu;
	
	Game game;
	
	public View(GameController gameController, Game jeu){
		this.controllerJeu = gameController;
		this.game = jeu;
	}
	
	public abstract void menuPrincipal();
	
	public abstract void jouerNouvellePartie();
	
	
	
}