package model.game;

import controller.GameController;
import model.EnumType.EnumOrigineCA;
//import model.game.De.Face;
import model.player.Human;
import model.player.Player;

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
				
				GameController gc = new GameController();
				gc.startGame();
				break;
				
			case 2:
				testJoueur();
				break;
			default:
				//ne rien mettre
		}
	}
	
	public static void testJoueur(){
		Player p1 = new Human("jean Yves", 12);
		p1.incrementerPointAction(EnumOrigineCA.JOUR, 2);
		System.out.println(p1.toString());
		p1.decrementerPointAction(EnumOrigineCA.JOUR, 1);
		System.out.println(p1.toString());
	}
}

