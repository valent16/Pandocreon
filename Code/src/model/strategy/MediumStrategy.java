package model.strategy;

import model.player.Bot;

/**Stratégie de jeu moyenne pour les bots*/
public class MediumStrategy implements Strategy {

	//- tant qu’il peut jouer, il joue l’ordre des cartes qu’il a 
	//- pour la mediumStrat privilégier la pose des croyants, et le récupération avec des guides
	//- regarder le modele strategy
	//- test des exceptions
	
	/**garde le bot qui joue en memoire pour recuperer ses donnees (cartes, score etc..)*/
	private Bot bot;
	
	/**
	 * Methode pour recuperer le bot qui joue
	 * @param bot le bot qui joue
	 */
	public void setBot(Bot bot) {
		this.bot = bot;
	}

	@Override
	public void jouerCarte() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void poserCroyant() {
		// TODO Auto-generated method stub
		
	}
}
