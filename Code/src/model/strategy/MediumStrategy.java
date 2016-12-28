package model.strategy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.ActionCard;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.exception.PAInsuffisantException;
import model.game.GameManager;
import model.player.Bot;
import model.player.Player;

/**Stratégie de jeu moyenne pour les bots*/
public class MediumStrategy implements Strategy {

	/**garde le bot qui joue en memoire pour recuperer ses donnees (cartes, score etc..)*/
	private Bot bot;

	/**
	 * Methode pour recuperer le bot qui joue
	 * @param bot le bot qui joue
	 */
	public void setBot(Bot bot) {
		this.bot = bot;
	}

	/**Methode de jeu*/
	public void jouer(Bot b){
		this.setBot(b); //Passage des données du bot
		//bot.afficherHand();
		System.out.println("score de "+ bot.getNom() + " " +bot.getScore());

		if(bot.hasApocalypse()){
			System.out.println("il a une apocalypse");
			this.lancerApocalypse();
		}
		else{//si pas d'apocalypse on eesaye de convertir un croyant
			this.convertirCroyants();
		}
	}

	@Override
	/*Le bot essaye de convertir des croyants sinon il essaye de les deposer*/
	public void convertirCroyants(){
		LinkedList<Believer> believers;
		if(bot.hasSpiritGuide()){	//si il a des guide spirituels on recupere nos croyants
			LinkedList<Believer> possibleBelievers = GameManager.getInstanceUniqueManager().getCroyants();
			if(possibleBelievers != null){
				SpiritGuide guide = bot.getSpiritGuides().get(0); //recupere un guide
				believers = (LinkedList<Believer>) bot.pickCroyant(guide); //recupere le plus de croyant possible
				if(believers != null){
					Iterator<Believer> it = believers.iterator();
					while(it.hasNext()){ //pour chaque croyant
						Believer believer = it.next();
						try {
							guide.utiliserPouvoir("convertir Croyant", bot);
							//guide.convertirCroyant(believer); //on les convertit au guide
							//bot.getGuideRattache().add(guide); //on rattache ce guide au joueur
							System.out.println("le bot a recuperer avec "+ guide + " le croyant "+ believer); /////////////////////////:
						} catch (Exception e) {} 
					}
				}else{//on essaye donc de deposer des croyants
					this.depotCroyant();
				}
			}else{//on essaye donc de deposer des croyants
				this.depotCroyant();
			}
			
		}else{//on essaye donc de deposer des croyants
			this.depotCroyant();
		}
	}

	@Override
	/*test dans l'ordre de deposer des croyants sinon on lance la phase pour tester le nombre de carte et agir en consequence*/
	public void depotCroyant(){
		if(bot.hasBelievers()){ //si on a un croyant on le poser sur la table
			LinkedList<Believer> liste = bot.getBelievers(); //recupere tous le croyants
			Iterator<Believer> it = liste.iterator();

			while(it.hasNext()){ //recupere le premier croyant posable
				Believer believer = it.next();
				if(bot.pointsOrigineSuffisants(believer)){	//test si le bot a suffisamment de point
					try{
						believer.utiliserPouvoir("deposer Croyant", bot);
					} catch (PAInsuffisantException e) {
						this.economy();
						e.printStackTrace();
					} catch (Exception e) {}
					System.out.println("le bot "+ bot.getNom() +" a posé le croyant "+ believer);
					break;
				}
			}
			System.out.println("voici les points du bot " + bot.getNom() + " " + bot.getDicoPA());///////////////////////////////
		}else{
			this.economy();
		}
	}

	/*cette phase test le nombre de carte du bot et agit en fonction*/
	@Override
	public void economy(){
		//TODO si son nombre de carte est a 0 on pioche 
		//TODO si il est a 7 on passe etc
		//bot.defausserRandom();
	}

	@Override
	//lancement d'une apocalypse
	public void lancerApocalypse(){
		//TODO il ne doit pas la lancer des le premier tour
		if(!bot.isLast()){
			try {
				ActionCard card = bot.getApocalypse(); //on recupere une apocalypse de maniere random
				card.utiliserPouvoir("declencher apocalypse", bot);
				System.out.println("apocalypse Lancer");/////////////////////////////////////////////////////////////////////////////
			} catch (PAInsuffisantException e) {
				//System.out.println("salut1");
				convertirCroyants(); //on relance la phase de conversion de croyant
			} catch (Exception e) {}
		}else{
			this.economy();
		}
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
		if(believers.size() !=0){
			return believers;
		}else{
			bot.piocher();
		}
		System.out.println("Aucun croyant sur la table ne peuvent etre converti");/////////////////////////////////////////////
		return null;
	}
}
