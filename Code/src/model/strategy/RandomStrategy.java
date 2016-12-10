package model.strategy;

import model.player.Bot;

//Une strategie random avec un switch case
public class RandomStrategy implements Strategy{

	
	
	/**garde le bot qui joue en memoire pour recuperer ses donnees (cartes, score etc..)*/
	private Bot bot;

	@Override
	public void setBot(Bot bot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jouer(Bot b){
		this.setBot(b); //Passage des données du bot
		System.out.println(bot.getNom());
		bot.afficherHand();
		System.out.print("ACTION DU BOT: "+ bot.getNom() + " ");
		
		//TODO Faire un switch case pour les actions suivante: 
		/*- poser des craoynats
		- recup des croyants
		- lancer un apocalupser
		- se defausser
		- piocher
		- lancer le pouvoir d'une carte random
		*/
	}
}
