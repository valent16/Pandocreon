package model.strategy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.cards.ActionCard;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.cards.withoutOriginCards.Apocalypse;
import model.enumType.EnumCosmogonie;
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
		if(bot.hasApocalypse())
			this.lancerApocalypse();//si le bot a une apocalypse
		else
			this.convertirCroyants();//si pas d'apocalypse on eesaye de convertir un croyant
	}

	@Override
	public void convertirCroyants(){
		if(bot.hasSpiritGuide()){	//si il a des guide spirituels on recupere nos croyants
			LinkedList<Believer> possibleBelievers = GameManager.getInstanceUniqueManager().getCroyants();
			if(possibleBelievers.size() != 0){//si des croyants sont potentiellement convertibles
				SpiritGuide guide = bot.getSpiritGuides().get(0); //recupere un guide
				if(bot.pointsOrigineSuffisants(guide)){
					try{
						guide.utiliserPouvoir("convertir Croyant", bot);
					}catch (Exception e) {
						this.depotCroyant();
					}
				}else
					this.depotCroyant();
			}else
				this.depotCroyant();
		}else 
			this.depotCroyant();
	}

	@Override
	public void depotCroyant(){
		if(bot.hasBelievers()){
			LinkedList<Believer> liste = bot.getBelievers();
			Iterator<Believer> it = liste.iterator();
			while(it.hasNext()){ //recupere le premier croyant posable
				Believer believer = it.next();
				if(bot.pointsOrigineSuffisants(believer)){	//test si le bot a suffisamment de point
					try{
						believer.utiliserPouvoir("deposer Croyant", bot);
						break;
					} catch (Exception e) {
						this.defausser();
						e.printStackTrace();
					}
				}else
					this.defausser();
			}
		}else
			this.defausser();
	}

	@Override
	public void lancerApocalypse(){
		ActionCard apocalypse = bot.getApocalypse(); //on recupere une apocalypse de maniere random
		if(!bot.isLast()){//on test s'il est dernier
			if(bot.pointsOrigineSuffisants((ActionCardWithOrigin) apocalypse) || apocalypse instanceof Apocalypse){
				try {	
					apocalypse.utiliserPouvoir("declencher apocalypse", bot);
				} catch (PAInsuffisantException e) {
					this.convertirCroyants();
				} catch (Exception e) {}
			}else
				this.convertirCroyants();
		}else
			this.convertirCroyants();	
	}

	@Override
	public void defausser(){
		if(bot.getNbCartes() > 3){//si le bot a au moins 4 cartes il se defausse
			int indexCard = (int) ((Math.random() * bot.getNbCartes())-1); //on choisit de maniere random
			ActionCard card = bot.getHand().get(indexCard+1);
			bot.defausserCarte(card);//on enleve la carte de la main du joueur

		}else{//sinon il pioche
			bot.piocher();
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
