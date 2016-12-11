package model.strategy;

import java.util.Iterator;

import java.util.LinkedList;
import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.cards.withoutOriginCards.Apocalypse;
import model.exception.PAInsuffisantException;
import model.game.GameManager;
import model.player.Bot;
import model.player.Player;

/**Stratégie de jeu facile pour les bots, les choix sont très simples*/
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
				bot.convertirCroyants();
				System.out.println("le bot a recuperer avec son guide spirit un croyant"); 

			}else{
				//si on a un croyant on le poser sur la table
				if(bot.hasBelievers()){ 
					bot.depotCroyant();

					//bot.getBeliever(). //TODO appeller le pouvoir pour poser la carte sur la table

					//sinon si on a une apocalypse on la lance
					//TODO: SI il est pas dernier on peut la lancer faire la methode lancer Apocalypse dans bot qui retourne un booleen
				}else if(bot.hasApocalypse()){ // si le bot a une apocalypse il la lance
					bot.lancerApocalypse();
					System.out.println("a lancer une apocalypse");
					
				}else if(bot.getNbCartes() == 0){//si le bot n'a plus de cartes il pioche
					bot.piocher();
					System.out.println("pioche");
				}
			}

		}else{//si le bot n'a pas posé de croyant

			if(bot.hasBelievers()){//s'il a des croyants il essaye d'en poser
				bot.depotCroyant();

			}else{//sinon
				if(bot.hasApocalypse()){ //sinon si on a une apocalypse on la lance
					//bot.getApocalypse(). //TODO appeller le pouvoir pour poser la carte sur la table
					System.out.println("a lancer une apocalypse");

				}else if(bot.getNbCartes() == 0){//si le bot n'a plus de cartes il pioche
					System.out.println("pioche");
					bot.piocher();

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
	public Player pickTarget() {
		Iterator<Player> it = GameManager.getInstanceUniqueManager().getPlayers().iterator();
		if(it.hasNext()) 
			return it.next(); //retourne le test
		else 
			return null;
	}
	
	@Override
	public EnumCosmogonie pickOrigine(ActionCardWithOrigin carte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Believer> pickCroyant(SpiritGuide guide) {
		List<Believer> believer = new LinkedList<Believer>();
		LinkedList<Believer> liste = GameManager.getInstanceUniqueManager().getCroyants();
		int limite = 0; //limite de carte croyant a convertire
		Iterator<Believer> it = liste.iterator();
		while(it.hasNext() && limite < guide.getNbMaxCroyant()){ //tant qu'il y a des croyants sur la table
			Believer b = it.next();
			if(guide.isCroyantCompatible(b)){
				believer.add(b);	
			}
			return believer;
		}
		System.out.println("Aucun croyant sur la table ne peuvent etre converti");
		return null;
	}

	@Override
	public void depotCroyant() {
		LinkedList<Believer> liste = getBelievers(); //recupere tous le croyants
		Iterator<Believer> it = liste.iterator();
		//recupere le premier croyant posable
		while(it.hasNext()){ 
			Believer believer = it.next();
			if(pointsOrigineSuffisants(believer)){	//test si le bot a suffisamment de point
				System.out.println("le bot a "+this.getDicoPA().get(believer.getOrigine())+" points "+ believer.getOrigine());
				try{
					//new DepotCroyant().onAction(believer, this);//Ca sa marche
					believer.utiliserPouvoir("deposer Croyant", this);
					System.out.println("croyant deposer");
					//believer.utiliserPouvoir("deposer Croyant", new DepotCroyant());
					/////////////////////////Exemple avec APOCALYPSE: new Apocalypse().utiliserPouvoir("declencher apocalypse", player);
				} catch (PAInsuffisantException e) {
					this.jouerTour();
					e.printStackTrace();
				} catch (Exception e) {}
				System.out.println("le bot "+ this.getNom() +" a posé le croyant "+ believer);
				break;
			}

			//si il a pas pu poser de croyants il economise ses points
			else{
				this.economy();
			}
		}
		
	}

	@Override
	public void convertirCroyants() {
		SpiritGuide guide = this.getGuides().get(0); //recupere un guide
		Believer believer = this.pickCroyant(guide).get(0); //recupere le croyant a convertir
		try {
			guide.convertirCroyant(believer);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println(this.getGuides().get(0).getCroyantsConvertis());	
		
	}

	@Override
	public void lancerApocalypse() {
		if(this.isLast())//on test si il est dernier 
			new Apocalypse().utiliserPouvoir("declencher apocalypse", player);b
		
	}
}