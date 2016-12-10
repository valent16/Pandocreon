package model.strategy;

import model.player.Bot;

/**Stratégie de jeu moyenne pour les bots*/
public class MediumStrategy implements Strategy {

	/**garde le bot qui joue en memoir pour recuperer ses sdonnees (cartes, score etc..)*/
	private Bot bot;
	
	/**
	 * Methode pour recuperer le bot qui joue
	 * @param bot le bot qui joue
	 */
	private void setBot(Bot bot) {
		this.bot = bot;
	}

	@Override
	/**
	 * Methode de jeu moyenne
	 * @param bot recupere le bot pour avoir ses parametress
	 */
	public void jouer(Bot bot) {
		this.setBot(bot);
	}

	@Override
	public void choisirCarte() {
		// TODO Auto-generated method stub
		
	}
}
