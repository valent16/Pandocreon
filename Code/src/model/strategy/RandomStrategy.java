package model.strategy;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.ActionCard;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.player.Bot;
import model.player.Player;

/**Une strategie random avec un switch case*/
public class RandomStrategy implements Strategy{

	/**garde le bot qui joue en memoire pour recuperer ses donnees (cartes, score etc..)*/
	private Bot bot;

	@Override
	public void setBot(Bot bot) {
		this.bot = bot;
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
				this.jouerCarteRandom();
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

		case 4: 
			//defausser une ou plusieurs carte
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
	
	/**Methode qui permet de jouer une carte Random utiliser pour la strategie Random et Easy*/
	private void jouerCarteRandom(){
		int indexCard = (int) (Math.random() * bot.getNbCartes())+1;
		ActionCard card = bot.getHand().get(indexCard);
		System.out.println("Le bot "+ bot.getNom()+" active le pouvoir de la carte "+ bot.getHand().get(indexCard));
		System.out.println("Il faut appeller la bonne methode LA CARTE A ACTIVER LE POUVOIR "+ bot.getHand().get(indexCard));
		System.out.println(card.getPouvoirs());
	}
	
	@Override
	public void depotCroyant() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void convertirCroyants() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lancerApocalypse() {
		// TODO Auto-generated method stub
		
	}


	@Override
	//permet d'economiser ses points dans notre cas il pioche
	public void economy(){
		bot.piocher();	
	}

	@Override
	public Player pickTarget() {
		// TODO Auto-generated method stub
		//prendre un joueur de maniere random
		return null;
	}

	@Override
	public EnumCosmogonie pickOrigine(ActionCardWithOrigin carte) {
		// TODO si on a le choix entre neant et jour ou nuit prendre 1pt neant sinon prendre 2pt JOUR sinon prendre 2pt NUIT sinon rien
		return null;
	}

	@Override
	public List<Believer> pickCroyant(SpiritGuide carte) {
		// TODO recup les croyants sur la table prnedre un random regarder ses dogmes si il sont compabtles recuepr le croyant et 
		return null;
	}
}
