package view.console;

import model.player.Player;

public class VueGameManager {
	
	public void afficherVictoireJoueur(Player joueur){
		System.out.println("\n\n");
		System.out.println("Bravo le joueur "+joueur.getNom()+" a gagne la partie avec un score de " + joueur.getScore());
	}
	
	public void afficherElimintation(Player joueur){
		System.out.println("\n\n");
		System.out.println("le joueur "+joueur.getNom()+" a ete elimine de la partie avec un score de " + joueur.getScore());
	}
}
