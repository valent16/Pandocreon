package model.strategy;

import java.util.ArrayList;

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

	/**Attribut correspondant au bot qui joue*/
	private Bot bot;

	/**
	 * Methode pour recuperer le bot qui joue
	 * @param bot le bot qui joue
	 */
	public void setBot(Bot bot) {
		this.bot = bot;
	}

	/**Methode permettant au bot de jouer sa strategie Medium
	 * @param b correspond au bot qui joue
	 */
	public void jouer(Bot b){
		this.setBot(b); //Passage des données du bot
		System.out.println("Origine de la Divinite de "+ bot.getNom() +" "+ bot.getDivinity().getOrigine());////////////////////////////////////////////////////////
		System.out.println("les points de "+ bot.getNom() +" "+ bot.getDicoPA());////////////////////////////////////////////////////////
		System.out.println(bot.getNom() +" a comme cartes :");//////////////////////////////////////////////////////////////////////////////////////////////
		bot.afficherHand();///////////////////////////////////////////////////////////////////:///////////////////////////////////////
		
		if(bot.hasApocalypse()){//si le bot a une apocalypse
			this.lancerApocalypse();
		}else{//si pas d'apocalypse on eesaye de convertir un croyant
			this.convertirCroyants();
		}
	}

	@Override
	/*Methode permettant au bot de convertir des croyants sinon il essaye de les deposer*/
	public void convertirCroyants(){
		if(bot.hasSpiritGuide()){	//si il a des guide spirituels on recupere nos croyants
			LinkedList<Believer> possibleBelievers = GameManager.getInstanceUniqueManager().getCroyants();
			if(possibleBelievers.size() != 0){//si des croyants sont potentiellement convertibles
				SpiritGuide guide = bot.getSpiritGuides().get(0); //recupere un guide
				if(bot.pointsOrigineSuffisants(guide)){
					try{
						guide.utiliserPouvoir("convertir Croyant", bot);
						System.out.println(bot.getNom() +" a converti avec son guide des croyants ");////////////////////////////////////////////////////////
						System.out.println("le guide qui a converti " + guide);////////////////////////////////////////////////////////////////////////////
						System.out.println("les croyants convertis " +guide.getCroyantsConvertis());/////////////////////////////////////////////////////////
					}catch (Exception e) {
						this.depotCroyant();
					}
				}else{
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
	/*Methode permettant au bot de deposer des croyants sinon on lance la phase pour tester le nombre de carte et agir en consequence*/
	public void depotCroyant(){
		if(bot.hasBelievers()){
			LinkedList<Believer> liste = bot.getBelievers();
			Iterator<Believer> it = liste.iterator();
			while(it.hasNext()){ //recupere le premier croyant posable
				Believer believer = it.next();
				if(bot.pointsOrigineSuffisants(believer)){	//test si le bot a suffisamment de point
					try{
						believer.utiliserPouvoir("deposer Croyant", bot);
						System.out.println(bot.getNom() +" a deposer"+ believer);////////////////////////////////////////////////////////
						break;
					} catch (Exception e) {
						this.economy();
						e.printStackTrace();
					}
				}else{
					System.out.println("pas assez de points pour ce croyant");
				}
			}
		}else{
			System.out.println("autres");
			this.economy();
		}
	}

	@Override
	/**Methode permettant au bot de lancer une apocalypse*/
	public void lancerApocalypse(){
		if(GameManager.getInstanceUniqueManager().getNumeroTour() > 5){//Si on est dans les 5 premiers tour de jeu on ne lance pas d'apocalypse
			ArrayList<Player> players = GameManager.getInstanceUniqueManager().getPlayers();/////////////////////////////////:::::::::::::::::::::::::::::::::::::::::::::::
			System.out.println(players.size());////////////////////////////////////////////////////////////////////////////////////////////////////////
			ActionCard apocalypse = bot.getApocalypse(); //on recupere une apocalypse de maniere random
			System.out.println("\n\nAPPELLE DE LA METHODE isLAST");//////////////////////////////////////////////////////////
			bot.isLast();////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			System.out.println("\n\n////////////////////////////////////");///////////////////////////////////////////////////////////////////////////////////////
			System.out.println("score de "+ bot.getNom() + " " +bot.getScore());/////////////////////////////////////////////////////////////////////////////////
			if(!bot.isLast()){//on test s'il est dernier
				if(bot.pointsOrigineSuffisants((ActionCardWithOrigin) apocalypse) || apocalypse instanceof Apocalypse){
					try {					
						Iterator<Player> it = players.iterator();////////////////////////////////////////////////////:
						while(it.hasNext()){///////////////////////////////////////////////////////////////////////////
							System.out.println("score du "+ it.next().getNom() +" = "+ it.next().getScore());/////////////////////////////////////////////////////////////////
						}
						apocalypse.utiliserPouvoir("declencher apocalypse", bot);
						System.out.println(bot.getNom() +" A LANCER UNE APOCALYSPE "+ apocalypse);////////////////////////////////////////////////////////
						System.out.println("joueurs Restant "+GameManager.getInstanceUniqueManager().getPlayers());//////////////////////////////////////////////////////////////////////////
						//TODO voir si on doit arreter la partie de nous meme ou si dans les methodes et classe apocalypse on l'arrete automatiquement
					} catch (PAInsuffisantException e) {
						this.convertirCroyants();
					} catch (Exception e) {}
				}else{
					this.convertirCroyants();
				}
			}else{
				this.convertirCroyants();	
			}
		}else{
			this.convertirCroyants();
		}
	}
	
	/*Methode permettant au bot d'economiser ses points et donc de piocher ou de se defauuser dependant du nombre de cartes qu'il possede*/
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
	public Player pickTarget() {
		Iterator<Player> it = GameManager.getInstanceUniqueManager().getPlayers().iterator();
		if(it.hasNext()) 
			return it.next(); //retourne le test
		else 
			return null;
	}

	@Override
	public EnumCosmogonie pickOrigine(ActionCardWithOrigin carte) {
		// Remplacer les test de bot points origines suffisant si on a les points jours ou neant on prend cela sinon on utilise les neants
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
		if(believers.size() !=0)
			return believers;
		else
			bot.piocher();
		return null;
	}
}
