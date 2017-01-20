package model.game;

import java.util.ArrayList;

import model.player.Player;
/**Classe gerant les tours de jeu*/
public class Tour {
	
	/**Attribut representant le joueur qui joue pendant le tour*/
	private static Player joueurActif;

	/**Attribut representant le nombre de tour*/
	private static int nombreTour = 1;
	
	/**Methode permettant d'effectuer les tours de jeu*/
	public static void deroulementTourJeu(){
		int start = 1;
		ArrayList<Player> players = GameManager.getInstanceUniqueManager().getPlayers();
		while(players.size()!=0){
			players.get(start%players.size()).lancerDe();
			System.out.println("\nTour Numero "+ nombreTour + " le de est sur la face "+ De.getInstanceDe().getFace());

			int nbJoueur = GameManager.getInstanceUniqueManager().getNbJoueur();
			for (int i = start; i< start+nbJoueur; i++){
				if (players.size() != 0){
					joueurActif = players.get(i%players.size());
					GameManager.getInstanceUniqueManager().notifyJoueurActif();
					players.get(i%players.size()).jouerTour();
				}
			}

			start = start+1;
			if (players.size() != 0){
				start = start%players.size();
			}
			nombreTour++;
			System.out.println();
			GameManager.getInstanceUniqueManager().afficherCroyants();//on affiche les croyants qui sont communs
			System.out.println();
		}
	}
	
	/**Getter un joueur
	 * @return le joueur actif durant le tour de jeu
	 */
	public static Player getJoueurActif() {
		return joueurActif;
	}
	/**Getter numero tour
	 * @return le numero du tour actuel
	 */
	public int getNumeroTour() {
		return nombreTour;
	}
}
