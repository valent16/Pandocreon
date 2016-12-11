package model.strategy;

import model.cards.OriginCards.Believer;
import model.game.GameManager;
import model.player.Bot;
import model.pouvoir.pouvoirCarte.DepotCroyant;

/**Stratégie de jeu facile pour les bots
 * Les choix se font au hasard*/
public class EasyStrategy implements Strategy {

	/**garde le bot qui joue en memoir pour recuperer ses sdonnees (cartes, score etc..)*/
	private Bot bot;

	/**
	 * Methode pour recuperer le bot qui joue
	 * @param bot le bot qui joue
	 */
	public void setBot(Bot bot) {
		this.bot = bot;
	}

	//methode de jeu
	public void jouer(Bot b){
		this.setBot(b); //Passage des données du bot
		System.out.println(bot.getNom());
		bot.afficherHand();
		System.out.println(bot.getDicoPA());
		System.out.println("Le nombre de croyants est de " +GameManager.getInstanceUniqueManager().getCroyants().size());

		if(GameManager.getInstanceUniqueManager().getCroyants().size() != 0){//si il y a deja des croyants sur la table
			//si il a des guide spirit on recupere nos croyants
			if(bot.hasSpiritGuide()){	
				System.out.println("le bot a recuperer avec son guide spirit un"); 
				//bot.getSpiritGuide(). //TODO appeler le pouvoir recuperer des croyants pour un guide spirituel

			}else{
				//si on a un croyant on le poser sur la table
				if(bot.hasBelievers()){ 
					//bot.DepotCroyant();

					//bot.getBeliever(). //TODO appeller le pouvoir pour poser la carte sur la table


					//sinon si on a une apocalypse on la lance
				}else if(bot.hasApocalypse()){ 
					//bot.getApocalypse(). //TODO appeller le pouvoir pour poser la carte sur la table
					System.out.println("a lancer une apocalypse");

					//si le bot n'a plus de cartes il pioche
				}else if(bot.getNbCartes() == 0){
					System.out.println("a lancer une apocalypse");
					System.out.println("pioche");
					bot.piocher();

				}else if(bot.getNbCartes() > 0){
					int choix = (int) (Math.random() * 2)+1;
					System.out.println(choix);
					switch(choix){
					case 1: //soit jouer une carte de maniere random en activant sa capacite
						bot.jouerCarteRandom();
						break;
					case 2: // soit de se defausser d'une carte de maniere random
						bot.defausserRandom();
						break;
					}
				}
			}

		}else{//si le bot n'a pas posé de croyant
			if(bot.hasBelievers()){//s'il il a des croyants il essaye d'en poser
				bot.DepotCroyant();

			}else{//sinon
				if(bot.hasApocalypse()){ //sinon si on a une apocalypse on la lance
					//bot.getApocalypse(). //TODO appeller le pouvoir pour poser la carte sur la table
					System.out.println("a lancer une apocalypse");

				}else if(bot.getNbCartes() == 0){//si le bot n'a plus de cartes il pioche
					System.out.println("pioche");
					bot.piocher();

				}else if(bot.getNbCartes() > 0){
					int choix = (int) (Math.random() * 2)+1;
					System.out.println(choix);
					switch(choix){
					case 1: //soit jouer une carte de maniere random en activant sa capacite
						bot.jouerCarteRandom();
						break;
					case 2: // soit de se defausser d'une carte de maniere random
						bot.defausserRandom();
						break;
					}
				}
			}
		}
	}

	@Override
	//permet d'economiser ses points dnas notre cas il passe son tour
	public void economy() {
		//bot.passerTour(); // TODO passerTour	
	}
}




