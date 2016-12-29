package model.strategy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.ActionCard;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.cards.withoutOriginCards.Apocalypse;
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
		bot.afficherHand();
		System.out.println("score de "+ bot.getNom() + " " +bot.getScore());
		System.out.println("voici les points du bot " + bot.getNom() + " " + bot.getDicoPA());///////////////////////////////

		if(bot.hasApocalypse()){
			System.out.println("il a une apocalypse");
			this.lancerApocalypse();
		}
		else{//si pas d'apocalypse on eesaye de convertir un croyant
			System.out.println("il essaye de convertir");
			this.convertirCroyants();
		}
	}

	@Override
	/*Le bot essaye de convertir des croyants sinon il essaye de les deposer*/
	public void convertirCroyants(){
		if(bot.hasSpiritGuide()){	//si il a des guide spirituels on recupere nos croyants
			LinkedList<Believer> possibleBelievers = GameManager.getInstanceUniqueManager().getCroyants();
			if(possibleBelievers.size() != 0){//si des croyants sont potentiellement convertibles
				SpiritGuide guide = bot.getSpiritGuides().get(0); //recupere un guide
				if(bot.pointsOrigineSuffisants(guide)){
					System.out.println("il a assez de points");
					try{
						guide.utiliserPouvoir("convertir Croyant", bot);
						System.out.println("le bot a recuperer avec "+ guide + " le croyant "+ guide.getCroyantsConvertis()); /////////////////////////:
					}catch (Exception e) {
						this.depotCroyant();
					}
				}else{
					System.out.println("pas assez de point donc on essaye de poser un croyant");//////////////////////////////////////////////////////:
					this.depotCroyant();
				}

			}else{//on essaye donc de deposer des croyants
				System.out.println("pas de croyant sur la table, il fait un depot");////////////////////////////////////////
				this.depotCroyant();
			}

		}else{//on essaye donc de deposer des croyants
			System.out.println("pas de guide, il fait un depot");/////////////////////////////////////////////////////////
			this.depotCroyant();
		}
	}

	@Override
	/*test dans l'ordre de deposer des croyants sinon on lance la phase pour tester le nombre de carte et agir en consequence*/
	public void depotCroyant(){
		if(bot.hasBelievers()){
			LinkedList<Believer> liste = bot.getBelievers();
			System.out.println("Coucou le depot");/////////////////////////////////////////////////////////////////////////////////////////////
			Iterator<Believer> it = liste.iterator();
			while(it.hasNext()){ //recupere le premier croyant posable
				Believer believer = it.next();
				if(bot.pointsOrigineSuffisants(believer)){	//test si le bot a suffisamment de point
					System.out.println("le point requis " + bot.getDicoPA().get(believer.getOrigine()));
					System.out.println("il a assez de point");
					try{
						believer.utiliserPouvoir("deposer Croyant", bot);
						break;
					} catch (Exception e) {
						System.out.println("pas de croyant dans sa main, il fait autre chose");////////////////////////////////////////
						this.economy();
						e.printStackTrace();
					}
				}else{
					System.out.println("pas assez de points pour ce croyant");
				}
			}
			System.out.println("voici les points du bot après le depot " + bot.getNom() + " " + bot.getDicoPA());///////////////////////////////
		}else{
			System.out.println("autres");
			this.economy();
		}
	}

	/*cette phase test le nombre de carte du bot et agit en fonction*/
	@Override
	public void economy(){
		if(bot.getNbCartes() < 7){//si le bot n'a plus de cartes il pioche
			System.out.println(bot.getNom() +" pioche");
			bot.piocher();

		}else{
			bot.defausserCarte(bot.getHand().get(0));//il se defausse de sa premiere carte
			System.out.println(bot.getNom() +" s'est defausser de 7 cartes");
		}
	}

	@Override
	//lancement d'une apocalypse
	public void lancerApocalypse(){
		//if(GameManager.getInstanceUniqueManager()MediumStrategy.) //TODO il ne doit pas la lancer des le premier tour//faire un attribu int tour et un getter pour recuperer le tour de la partie
		
		ActionCard apocalypse = bot.getApocalypse(); //on recupere une apocalypse de maniere random
		if(!bot.isLast()){//on test si il est dernier
			if(bot.pointsOrigineSuffisants((ActionCardWithOrigin) apocalypse) || apocalypse instanceof Apocalypse){
				System.out.println("il a assez de point");////////////////////////////////////////////////////////////////////////////
				try {
					apocalypse.utiliserPouvoir("declencher apocalypse", bot);
					//TODO voir si on doit arreter la partie de nous meme
					System.out.println("apocalypse Lancer");/////////////////////////////////////////////////////////////////////////////
				} catch (PAInsuffisantException e) {
					this.convertirCroyants(); //on relance la phase de conversion de croyant
				} catch (Exception e) {}
			}else{
				System.out.println("il n'a pas les points requis");///////////////////////////////////////////////////////////////
				this.convertirCroyants();
			}
		}else{
			System.out.println("ce bot est dernier il n'a pas d'interet de la lancer");////////////////////////////////////////////////////////////////////////
			this.convertirCroyants();	
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
		int limiteCroyant = 0; //limite de carte croyant a convertir
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
