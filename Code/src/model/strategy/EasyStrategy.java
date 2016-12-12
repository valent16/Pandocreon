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
		System.out.println(bot.getNom() + " " +bot.getDivinity().getOrigine());//////////////////////////////////////////////////////////////////
		System.out.println("voici les points du bot " + bot.getNom() + " " + bot.getDicoPA());/////////////////////////
		System.out.println("Le nombre de croyants sur la table est de " +GameManager.getInstanceUniqueManager().getCroyants().size());///////////////////////
		bot.afficherHand();
		System.out.println("SCORE "+bot.getScore());

		if(GameManager.getInstanceUniqueManager().getCroyants().size() != 0){//si il y a deja des croyants sur la table
			if(bot.hasSpiritGuide()){	//si il a des guidez spirituels on recupere nos croyants
				this.convertirCroyants();
				System.out.println("le bot a recuperer avec son guide spirit un croyant"); /////////////////////////:
				System.out.println("SCORE "+bot.getScore());

			}else{
				//si on a un croyant on le poser sur la table
				if(bot.hasBelievers()){ 
					this.depotCroyant();
					System.out.println("voici les points du bot " + bot.getNom() + " " + bot.getDicoPA());////////////////////////////////


					//TODO: SI il est pas dernier on peut la lancer faire la methode lancer Apocalypse dans bot qui retourne un booleen
				}else if(bot.hasApocalypse()){ // si le bot a une apocalypse il la lance
					this.lancerApocalypse();
					System.out.println("a lancer une apocalypse");

				}else if(bot.getNbCartes() == 0){//si le bot n'a plus de cartes il pioche
					bot.piocher();
					System.out.println("1pioche");
				}
			}

		}else{//si le bot n'a pas posé de croyant

			if(bot.hasBelievers()){//s'il a des croyants il essaye d'en poser
				bot.depotCroyant();
				System.out.println("voici les points du bot " + bot.getNom() + " " + bot.getDicoPA());////////////////////////////////////////
			}else{//sinon
				if(bot.hasApocalypse()){ //sinon si on lance une apocalypse
					this.lancerApocalypse();
					System.out.println("a lancer une apocalypse");

				}else if(bot.getNbCartes() < 7){//si le bot n'a plus de cartes il pioche
					System.out.println(bot.getNom() +" pioche");
					bot.piocher();
				}else{
					bot.defausserCarte(bot.getHand().get(0));//il se defausse de sa premiere carte
					System.out.println(bot.getNom() +" s'est defausser de 7 cartes");
				}
			}
		}
		System.out.println();///////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	@Override
	//depot de croyant
	public void depotCroyant() {
		LinkedList<Believer> liste = bot.getBelievers(); //recupere tous le croyants
		Iterator<Believer> it = liste.iterator();

		while(it.hasNext()){ //recupere le premier croyant posable
			Believer believer = it.next();
			if(bot.pointsOrigineSuffisants(believer)){	//test si le bot a suffisamment de point
				System.out.println("les points suffisants resultat "+ bot.pointsOrigineSuffisants(believer));/////////////////////////////////////////////////
				System.out.println("le bot "+ bot.getNom()+ " a " + bot.getDicoPA().get(believer.getOrigine())+" points "+ believer.getOrigine()); ////////////////////:
				try{
					believer.utiliserPouvoir("deposer Croyant", bot);
				} catch (PAInsuffisantException e) {
					bot.jouerTour();
					e.printStackTrace();
				} catch (Exception e) {}
				System.out.println("le bot "+ bot.getNom() +" a posé le croyant "+ believer);
				break;
			}
		}
	}

	@Override
	//conversion de croyant
	public void convertirCroyants() {
		System.out.println("LES GUIDES du bot " + bot.getNom()+ " " + bot.getSpiritGuide());
		SpiritGuide guide = bot.getSpiritGuides().get(0); //recupere un guide
		System.out.println("guide recuperer est le guide "+ guide);////////////////////////////////////////////////////////////
		LinkedList<Believer> believers = (LinkedList<Believer>) bot.pickCroyant(guide); //recupere le plus de croyant possible
		Iterator<Believer> it = believers.iterator();
		while(it.hasNext()){ //pour chaque croyant
			Believer believer = it.next();
			try {
				guide.convertirCroyant(believer); //on les convertit au guide
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		System.out.println(guide.getCroyantsConvertis());
	}

	@Override
	//lancement d'une apocalypse
	public void lancerApocalypse() {
		if(!bot.isLast()){
			try {
				new Apocalypse().utiliserPouvoir("declencher apocalypse", bot);
				System.out.println("apocalypse Lancer");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.economy();
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
		List<Believer> believers = new LinkedList<Believer>(); //la liste a recuperer a la fin
		LinkedList<Believer> liste = GameManager.getInstanceUniqueManager().getCroyants();
		int limiteCroyant = 0; //limite de carte croyant a convertire
		Iterator<Believer> it = liste.iterator();
		while(it.hasNext() && limiteCroyant < guide.getNbMaxCroyant()){ //tant qu'il y a des croyants sur la table
			Believer b = it.next();
			if(guide.isCroyantCompatible(b)){
				believers.add(b);	
			}
		}
		System.out.println(believers.size());
		if(believers.size() !=0){
			return believers;
		}else{
			bot.piocher();
		}

		System.out.println("Aucun croyant sur la table ne peuvent etre converti");//////////////////////////////////////////////
		return null;
	}


}