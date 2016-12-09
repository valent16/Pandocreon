package controller;
import model.game.Game;
import view.console.VueGame;

public class GameController {
	VueGame vueJeu;
	Game game;
	
	public GameController(){
		game = new Game();
		vueJeu = new VueGame(this, game);
	}
	
	public void startGame(){
		
		game.initGame();
		vueJeu.MenuPrincipal();
	}
	
	public boolean checkNbJoueur(int nb){
		if (nb >=2 && nb <= game.getNbJoueurMax()){
			return true;
		}
		return false;
	}
	
	
	
}
