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
import model.player.Human;
import model.player.Player;
import view.ihm.Client;
import view.ihm.PanelCarte;
import view.ihm.TableJeu;

/**Classe de lancement du programme*/
public class Main{

	/**Methode pour lancer l'application*/
	public static void main(String[] args) {	

		int valeur = 4;
		
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
			gc.lancerPartie("console"); //on lance en mode console
			break;

		case 5: //test pour le lancement d'une partie
			GameController gameController = new GameController();
			//gameController.getGame().initGame();
			gameController.startGame();

			gameController.CreationJoueur("valentin", 18);
			gameController.CreationJoueur("David", 20);

			gameController.lancerPartie("console"); //on lance en mode console
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
			gameController = new GameController();
			gameController.startGame();
			
			gameController.CreationJoueur("Valentin", 18);
			gameController.CreationJoueur("David", 20);
			
			gameController.lancerPartie("IHM");//choisi de lancer en mode graphique
			break;
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
		gc.startGame();
		
		gc.CreationBot("bot1", "medium");
		gc.CreationBot("bot2", "medium");
		gc.CreationBot("bot3", "medium");

		gc.lancerPartie("console");
	}

	/**Methode permettant de tester le fonctionnement d'une partie*/
	public static void testCreationPartie(){
		GameController gc = new GameController();
		gc.startGame();

		gc.CreationJoueur("valentin", 18);
		gc.CreationJoueur("David", 20);
		
		gc.lancerPartie("console");
	}

	/**Methode permettant de tester les humains*/
	public static void testJoueur(){
		Player p1 = new Human("jean Yves", 12);
		p1.incrementerPointAction(EnumCosmogonie.JOUR, 2);
		System.out.println(p1.toString());
		System.out.println(p1.toString());
	}
}

