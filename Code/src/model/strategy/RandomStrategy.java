package model.strategy;

import java.util.HashMap;

import model.EnumType.EnumCosmogonie;
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

		//depart
		int action = (int) (Math.random() * 5) + 1;
		System.out.println("LACTION "+action);
		switch (action){
		case 1: //jouer une carte random et active son pouvoir
			if(bot.getHand().size() != 0){	//si le bot possede des cartes
				bot.jouerCarteRandom();
				System.out.println("action2");
			}else{//sinon on rappelle la methode pour jouer
				jouer(bot); 
			}
			break;

		case 2://poser un croyant
			if(bot.hasBelievers()){
				System.out.println("on a des croyants");
				bot.afficherHand();
				//appeler une methode strategy.poserCroyant()
			}else{
				System.out.println("on a pas des croyants");
			}
			break;

		case 3: //recuperer des croyants avec le guide spirituel

			System.out.println("recuperer des croyants avec le guide spirituel");
			break;

		case 4: //defausser une ou plusieurs carte
			//TODO faire un random entre 1 et 2 si c'est 1 c'est un random d'un carte random qu'on defausse si c'est 2 c'est un random d'un nombre de carte random qui permet de faire une boucle pour defauusser pluisuer cartes
			//si sa main est vide on rappelle la methode jouer
			//this.defausserCartes(partie.getCartesDefaussees());
			System.out.println("defausser une ou plusieurs carte de maniere Random");
			break;

		case 5: //completer sa main jusqu'a 7
			if(bot.getHand().size() < 7){
				//this.completerMain(partie.getPioche());
				//this.jouer(partie);
				System.out.println("completer sa main jusqu'a 7");
				System.out.println("nombre de carte maximum atteint");
			}else{
				jouer(bot);
			}
			break;
		case 6: //Sacrifier une carte
			break;
		case 7://jouer une apocalypse
			break;
		default:
			break;
		}
	}

	@Override
	//permet d'economiser ses points dnas notre cas il passe son tour
	public void economy(){
		//bot.passerTour(); // TODO passerTour	
	}
}
