package model.strategy;

import java.util.List;
import java.util.Set;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.game.GameManager;
import model.player.Bot;
import model.player.Bot.Return;
import model.pouvoir.pouvoirCarte.ConversionCroyant;
import model.player.Player;

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

	//Methode de jeu
	public void jouer(Bot b){
		this.setBot(b); //Passage des données du bot
		System.out.println(bot.getNom());
		bot.afficherHand();
		System.out.println(bot.getDicoPA());
		System.out.println("Le nombre de croyants est de " +GameManager.getInstanceUniqueManager().getCroyants().size());

		if(GameManager.getInstanceUniqueManager().getCroyants().size() != 0){//si il y a deja des croyants sur la table
			//si il a des guide spirit on recupere nos croyants
			if(bot.hasSpiritGuide()){	
				System.out.println("le bot a recuperer avec son guide spirit un croyant"); 
				bot.getGuides().get(0).ajouterPouvoir("convertir Croyant", new ConversionCroyant());  //TODO a verifier si ca marche
				System.out.println(bot.getGuides().get(0).getCroyantsConvertis());

			}else{
				//si on a un croyant on le poser sur la table
				if(bot.hasBelievers()){ 
					bot.DepotCroyant();  //TODO a verifier si ca marche

					//bot.getBeliever(). //TODO appeller le pouvoir pour poser la carte sur la table

					//sinon si on a une apocalypse on la lance
					//TODO: SI il est pas dernier on peut la lancer faire l amethode lancer Apocalypse dans bot qui retourne un booleen
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
	/**permet d'economiser ses points dans notre cas il pioche son tour*/
	public void economy() {
		bot.piocher();
	}


	@Override
	public ActionCard pickCard (LinkedList<ActionCard> cards) {
		if(cards.size() > 0){
			int nb = (int) (Math.random() * (cards.size()-1));
			return cards.get(nb);
		}else return null;
	}

	@Override
	public Player pickTarget() {
		Iterator<Players> it = GameManager.getInstanceUniqueManager().getPlayers().iterator();
		if(it.hasNext()) return it.next();
		else return null;
	}

	@Override
	public Player pickTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumCosmogonie pickOrigine(ActionCardWithOrigin carte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Believer> pickCroyant(SpiritGuide carte) {
		// TODO Auto-generated method stub
		return null;
	}
}