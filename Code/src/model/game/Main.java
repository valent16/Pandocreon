package model.game;

import controller.GameController;
import controller.JoueurController;
import model.EnumType.EnumOrigineCA;
import model.player.Bot;
//import model.game.De.Face;
import model.player.Human;
import model.player.Player;
import model.strategy.EasyStrategy;
import model.strategy.MediumStrategy;

/**Classe de lancement du programme*/
public class Main{
	
	/**Methode pour lancer l'application*/
	public static void main(String[] args) {	
		int valeur =1;
		//1: lancement du jeu
		//2: test sur la classe joueur
		
		switch(valeur){
			case 1:
				//Game game = new Game();
				//game.initGame();
				
				//GameController gc = new GameController();
				//gc.startGame();
				testCreationPartie();
				break;
				
			case 2:
				testJoueur();
				break;
			default:
				//ne rien mettre
		}
	}
	
	
	public static void testCreationPartie(){
		Game game = new Game();
		game.initGame();
		
		Human joueur1 = new Human("valentin", 18);
		joueur1.attacher(new JoueurController(joueur1));
		Human joueur2 = new Human("David", 20);
		joueur2.attacher(new JoueurController(joueur2));
		Human joueur3 = new Human("Lucas", 20);
		joueur3.attacher(new JoueurController(joueur3));
		Bot IA1 = new Bot("henry", new EasyStrategy());
		Bot IA2 = new Bot("jean jacque", new MediumStrategy());
		
		game.ajouterBot(IA1);
		game.ajouterBot(IA2);

		game.ajouterJoueurReel(joueur1);
		game.ajouterJoueurReel(joueur2);
		game.ajouterJoueurReel(joueur3);
		
		game.nouvellePartie();
		GameManager.getInstanceUniqueManager().startGame();
		
		GameManager.getInstanceUniqueManager().afficherJoueur();

		
	}
	
	
	public static void testJoueur(){
		Player p1 = new Human("jean Yves", 12);
		p1.incrementerPointAction(EnumOrigineCA.JOUR, 2);
		System.out.println(p1.toString());
		p1.decrementerPointAction(EnumOrigineCA.JOUR, 1);
		System.out.println(p1.toString());
	}
}

