package controller;

import model.game.Game;
import model.game.GameManager;
import model.player.Bot;
import model.player.Human;
import model.strategy.EasyStrategy;
import model.strategy.HardStrategy;
import model.strategy.MediumStrategy;
import model.strategy.Strategy;
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
	
	
	//Permet la cr�ation d'un joueur � partir de param�tres
	public void CreationJoueur(String nom, int age){
		Human joueur = new Human(nom, age);
		joueur.attacher(new JoueurController(joueur));
		game.ajouterJoueurReel(joueur);
	}
	
	//Permet la cr�ation d'un bot � partir de param�tres
	public void CreationBot(String nom, String nomStrat){
		Strategy strat;
		
		switch(nomStrat){
		case "facile":
			strat = new EasyStrategy();
			break;
		case "medium":
			strat = new MediumStrategy();
			break;
		case "difficile":
			strat = new HardStrategy();
		default:
			//Lancement Exception
			strat = new EasyStrategy();
		}
		
		Bot joueur = new Bot(nom, strat);
	}
	
	public void lancerPartie(){
//		game.initGame();
		game.nouvellePartie();
		GameManager.getInstanceUniqueManager().startGame();
//		GameManager.getInstanceUniqueManager().afficherJoueur();
	}
	
	
}
