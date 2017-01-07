package model.game;


import java.awt.EventQueue;

import controller.GameController;
import controller.JoueurController;
import model.EnumType.EnumCosmogonie;
import model.player.Bot;
import model.player.Human;
import model.player.Player;
import model.strategy.MediumStrategy;
import view.ihm.Client;
import view.ihm.TableJeu;

/**Classe de lancement du programme*/
public class Main{

	/**Methode pour lancer l'application*/
	public static void main(String[] args) {	

		int valeur = 7;

		switch(valeur){
		case 1: //permet de tester une partie de 2 humains
			testCreationPartie();
			break;

		case 2: //permet de voir si les points sont bien atttribues
			testJoueur();
			break;

		case 3: //pour tester 2 bots jouer une partie
			testBot();
			break;

		case 4: // Pour demarrer une partie en mode console
			GameController gc = new GameController();
			gc.startGame();
			break;

		case 5: //pour lancer la fenetre de jeu
			TableJeu table = new TableJeu();
			break;

		case 7: //Pour lancer le client (Menu du jeu)
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Client window = new Client();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		default:
		}
	}

	/**Methode permettant de tester les bots dans une partie avec la strategie medium*/
	private static void testBot() {
		Game game = new Game();
		game.initGame();

		Bot b1 = new Bot("bot1", new MediumStrategy());
		Bot b2 = new Bot("bot2", new MediumStrategy());
		Bot b3 = new Bot("bot3", new MediumStrategy());
		Bot b4 = new Bot("bot4", new MediumStrategy());
		Bot b5 = new Bot("bot5", new MediumStrategy());

		game.ajouterBot(b1);
		game.ajouterBot(b2);
		game.ajouterBot(b3);
		game.ajouterBot(b4);
		game.ajouterBot(b5);

		game.nouvellePartie();	
		GameManager.getInstanceUniqueManager().startGame();
	}

	/**Methode permettant de tester le fonctionnement d'une partie*/
	public static void testCreationPartie(){
		Game game = new Game();
		game.initGame();

		Human joueur1 = new Human("valentin", 18);
		joueur1.attacher(new JoueurController(joueur1));
		Human joueur2 = new Human("David", 20);
		joueur2.attacher(new JoueurController(joueur2));
		game.ajouterJoueurReel(joueur1);
		game.ajouterJoueurReel(joueur2);

		game.nouvellePartie();
		GameManager.getInstanceUniqueManager().startGame();
	}

	/**Methode permettant de tester les humains*/
	public static void testJoueur(){
		Player p1 = new Human("jean Yves", 12);
		p1.incrementerPointAction(EnumCosmogonie.JOUR, 2);
		System.out.println(p1.toString());
		System.out.println(p1.toString());
	}
}

