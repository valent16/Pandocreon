package game;

import Enum.EnumPointAction;
import player.Human;
import player.Player;

/**Classe de lancement du programme*/
public class Main{
	
	/**Methode pour lancer l'application*/
	public static void main(String[] args) {	
		int valeur =1;
		//1: lancement du jeu
		//2: test sur la classe joueur
		
		switch(valeur){
			case 1:
				Game game = new Game();  //Creation de la partie
				game.initGame();  //lancement de la partie
				
			case 2:
				testJoueur();
			default:
				//ne rien mettre
		}
	}
	
	public static void testJoueur(){
		Player p1 = new Human("jean Yves", 12);
		p1.incrementerPointAction(EnumPointAction.JOUR, 2);
		System.out.println(p1.toString());
		p1.decrementerPointAction(EnumPointAction.JOUR, 1);
		System.out.println(p1.toString());
	}
}