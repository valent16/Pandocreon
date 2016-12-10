package model.strategy;

import model.player.Bot;

/**Stratégie de jeu facile pour les bots
 * Les choix se font au hasard*/
public class EasyStrategy implements Strategy {

	//POUR LA EASY STRATEGIE FAIRE UN CHOIX RANDOM parmi les choix suivants:
	//Suivre le code sur le site




	/**garde le bot qui joue en memoir pour recuperer ses sdonnees (cartes, score etc..)*/
	private Bot bot;
	
	/**
	 * Methode pour recuperer le bot qui joue
	 * @param bot le bot qui joue
	 */
	public void setBot(Bot bot) {
		this.bot = bot;
	}

	@Override
	public void jouerCarte(){
		int indexCarte = (int) (Math.random() * bot.getHand().size() +1);
		System.out.println("Le bot "+ bot.getNom()+" active le pouvoir de la carte "+ bot.getHand().get(indexCarte));
		System.out.println("LA CARTE A ACTIVER LE POUVOIR"+ bot.getHand().get(indexCarte));
		// bot.getHand().get(indexCarte).utiliserPouvoir("nom de la commande", bot);
		//////////APPELER LE POUVOIR DE LA CARTE/////////////////////////////////////////////////////////////////////////////////////////
	}

	//pose un de ses croyants de maniere random
	public void poserCroyant() {
		//LinkedList<Believer> bel = bot.getBelievers();
		//int random = (int) (Math.random() * bel.size()) + 1;
		//System.out.println("le random " +random);
	}
}




