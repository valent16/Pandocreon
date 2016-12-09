package controller;
import model.game.Game;
import model.player.Bot;
import model.player.Human;
import model.player.Player;
import model.strategy.EasyStrategy;
import model.strategy.HardStrategy;
import model.strategy.MediumStrategy;
import model.strategy.Strategy;
import view.console.VueGame;

public class GameController {
<<<<<<< HEAD
	
=======
>>>>>>> 8452066bdd43a7c52b8504de641b891348049a9b
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
	
	//Permet la création d'un joueur à partir de paramètres
	public void CreationJoueur(String nom, int age){
		Human joueur = new Human(nom, age);
		game.ajouterJoueurReel(joueur);
	}
	
	//Permet la création d'un bot à partir de paramètres
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
	
	
}
