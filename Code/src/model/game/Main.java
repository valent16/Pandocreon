package model.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import controller.GameController;
import controller.JoueurController;
import model.EnumType.EnumCosmogonie;
import model.EnumType.EnumDogme;
import model.EnumType.EnumOrigineDivinite;
import model.cards.Divinity;
import model.player.Bot;
import model.player.Human;
import model.player.Player;
import model.strategy.MediumStrategy;
import view.ihm.Client;
import view.ihm.PanelCarte;
import view.ihm.TableJeu;

/**Classe de lancement du programme*/
public class Main{

	/**Methode pour lancer l'application*/
	public static void main(String[] args) {	

		int valeur = 8;
		
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

		case 5: //test pour le lancement d'une partie
			Game game = new Game();
			game.initGame();

			Human joueur1 = new Human("valentin", 18);
			joueur1.attacher(new JoueurController(joueur1));
			
			Human joueur2 = new Human("David", 20);
			joueur2.attacher(new JoueurController(joueur2));
			game.ajouterJoueurReel(joueur1);
			game.ajouterJoueurReel(joueur2);

			game.nouvellePartie();
			GameManager.getInstanceUniqueManager().startGameConsole();
			
			break;
		
		case 6:
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
			break;
			
		case 8:
			GameController gameController = new GameController();
			gameController.getGame().initGame();

			Bot b1 = new Bot("bot1", new MediumStrategy());
			Bot b2 = new Bot("bot2", new MediumStrategy());
			Bot b3 = new Bot("bot3", new MediumStrategy());

			Human j1 = new Human("valentin", 18);
			j1.attacher(new JoueurController(j1));
			
			Human j2 = new Human("David", 20);
			j2.attacher(new JoueurController(j2));
			gameController.getGame().ajouterJoueurReel(j1);
			gameController.getGame().ajouterJoueurReel(j2);
			
			gameController.lancerPartie("IHM");//choisi de lancer en mode graphique
			
			
			
			TableJeu tb = new TableJeu();
//			testCarte();
		default:
		}
	}
	
	
	public static void testCarte(){
		ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();
		dogmes.add(EnumDogme.CHAOS);
		dogmes.add(EnumDogme.NATURE);
		String description = "description de la divinite";
		EnumOrigineDivinite origine = EnumOrigineDivinite.JOUR;
		Divinity carte = new Divinity("nom divinite", dogmes, description, origine);
		
		
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(1100,1000));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setVisible(true);
		
		PanelCarte panel = new PanelCarte(carte);
		
		frame.getContentPane().add(panel, BorderLayout.WEST);
		
		frame.pack();
	}

	/**Methode permettant de tester les bots dans une partie avec la strategie medium*/
	private static void testBot() {
		GameController gc = new GameController();
		gc.getGame().initGame();

		Bot b1 = new Bot("bot1", new MediumStrategy());
		Bot b2 = new Bot("bot2", new MediumStrategy());
		Bot b3 = new Bot("bot3", new MediumStrategy());

		gc.getGame().ajouterBot(b1);
		gc.getGame().ajouterBot(b2);
		gc.getGame().ajouterBot(b3);

		gc.lancerPartie("console");
		//GameManager.getInstanceUniqueManager().startGameConsole();
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
		GameManager.getInstanceUniqueManager().startGameConsole();
	}

	/**Methode permettant de tester les humains*/
	public static void testJoueur(){
		Player p1 = new Human("jean Yves", 12);
		p1.incrementerPointAction(EnumCosmogonie.JOUR, 2);
		System.out.println(p1.toString());
		System.out.println(p1.toString());
	}
}

