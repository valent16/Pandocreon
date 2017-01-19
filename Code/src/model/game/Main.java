package model.game;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import controller.GameController;
import model.cards.Divinity;
import model.enumType.EnumCosmogonie;
import model.enumType.EnumDogme;
import model.enumType.EnumOrigineDivinite;
import model.player.Human;
import model.player.Player;
import view.ihm.PanelCarte;
import view.ihm.client.Client;

/**Classe de lancement du programme*/
public class Main{

	/**Methode pour lancer l'application
	 * @param args les arguments en ligne de commande
	 */
	public static void main(String[] args) {	
		int valeur = 1;
		switch(valeur){
		case 1: //Pour lancer le client (Menu du jeu)
			try {
				Client window = new Client();
				
				while(!window.getInitialize()){
					Thread.sleep(200);
				}
				
				GameController g = new GameController();
				g.startGame(window.getListeNomBot(), window.getStrategie(), window.getListeNomHumain(), window.getListeAgeHumain());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case 2: //permet de voir si les points sont bien atttribues
			testJoueur();
			break;

		case 3: //pour tester 2 bots jouer une partie
			testBot();
			break;

		case 4: // Pour demarrer une partie en mode console
			GameController gc = new GameController();
			gc.startGame(null, "lala", null, null);
			break;

		case 5: //test pour le lancement d'une partie
			GameController gameController = new GameController();
			Human joueur1 = new Human("valentin", 18);
			Human joueur2 = new Human("David", 20);
			gameController.getGame().ajouterJoueurReel(joueur1);
			gameController.getGame().ajouterJoueurReel(joueur2);

			gameController.lancerPartie(); //on lance en mode console
			break;
			
		case 6: //permet de tester une partie de 2 humains
			testCreationPartie();
			break;
			
		case 7:
			GameController gameController1 = new GameController();
			gameController1.CreationJoueur("valentin", 18);
			gameController1.CreationJoueur("David", 20);
			gameController1.lancerPartie();
			break;
		default:
		}
	}
	
	/**Methode permettant de tester les cartes du jeu*/
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
		gc.CreationBot("bot1", "medium");
		gc.CreationBot("bot2", "medium");
		gc.CreationBot("bot3", "medium");
		gc.lancerPartie();
	}

	/**Methode permettant de tester le fonctionnement d'une partie*/
	public static void testCreationPartie(){
		GameController gc = new GameController();
		gc.CreationJoueur("valentin", 18);
		gc.CreationJoueur("David", 20);
		gc.lancerPartie();
	}

	/**Methode permettant de tester les humains*/
	public static void testJoueur(){
		Player p1 = new Human("jean Yves", 12);
		p1.incrementerPointAction(EnumCosmogonie.JOUR, 2);
		System.out.println(p1.toString());
		System.out.println(p1.toString());
	}
}

