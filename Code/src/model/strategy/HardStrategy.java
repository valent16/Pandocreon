package model.strategy;

import model.player.Bot;

/**Stratégie de jeu difficile pour les bots*/
public class HardStrategy implements Strategy {

	/**garde le bot qui joue en memoir pour recuperer ses sdonnees (cartes, score etc..)*/
	private Bot bot;

	/**
	 * Methode pour recuperer le bot qui joue
	 * @param bot le bot qui joue
	 */
	@Override
	public void setBot(Bot bot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jouer(Bot b) {
		this.setBot(b); //Passage des données du bot
		System.out.println(bot.getNom());
		bot.afficherHand();
		System.out.print("ACTION DU BOT: "+ bot.getNom() + " ");	
	}

	@Override
	//permet d'economiser ses points dans notre cas il pioche si il a moins de 7 cartes sinon il passe son tour
	public void economy() {
		//if(getHand < 7)
			//piocher
		//else
			//bot.passerTour(); // TODO passerTour	
	}
}
