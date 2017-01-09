package model.player;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import model.EnumType.EnumCosmogonie;
import model.cards.ActionCard;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.ApocalypseWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.cards.withoutOriginCards.Apocalypse;
import model.exception.ObservateurNotLinkedException;
import model.exception.TargetSelectionException;
import model.game.GameManager;
import model.strategy.*;
import view.ObservateurJoueurReel;

/**Un joueur qui représente un ordinateur avec une stratégie de jeu*/
public class Bot extends Player implements IObservableBot{
	public final static int AGE_BOT = 200;

	/**Attribut representant la stratégie choisi au départ pour tous les bots*/
	private static Strategy strategy;

	/**Constructeur de joueur qui est appelé pour créer un ordinateur*/
	public Bot(String pseudo, Strategy strat) {
		super(pseudo, AGE_BOT);
		setStrategy(strat);
		strategy.setBot(this); //donne les parametres du bot a la stratégie
	}

	/**Methode permettant au bot de jouer son tour en fonction de sa strategie*/
	public void jouerTour(){	
		incrementerPointActionWithDe();
		strategy.jouer(this);
	}

	/**Methode qui test si le bot possede des croyants dans sa main
	 * @return true s'il possede des croyants
	 */
	public boolean hasBelievers(){
		Iterator<ActionCard> it = getHand().iterator();
		while(it.hasNext()){
			ActionCard card = it.next();
			if (card instanceof Believer){
				return true;
			}
		}
		return false;
	}

	/**Methode qui test si le bot possede des guide dans sa main
	 * @return true s'il possede des guides
	 */
	public boolean hasSpiritGuide(){
		Iterator<ActionCard> it = getHand().iterator();
		while(it.hasNext()){
			ActionCard card = it.next();
			if (card instanceof SpiritGuide){
				return true;
			}
		}
		return false;
	}

	/**Methode qui test si le bot possede des apocalypse dans sa main
	 * @return true s'il possede des croyants
	 */
	public boolean hasApocalypse(){
		Iterator<ActionCard> it = getHand().iterator();
		while(it.hasNext()){
			ActionCard card = it.next();
			if (card instanceof Apocalypse || card instanceof ApocalypseWithOrigin){
				return true;
			}
		}
		return false;
	}

	/**Methode permettant de recuperer une carte croyant de la main du bot de maniere Random
	 * @return un croyant
	 */
	public Believer getBeliever(){
		LinkedList<ActionCard> liste = getHand();
		Collections.shuffle(liste); //pour obtenir le croyant de maniere random
		Iterator<ActionCard> it = liste.iterator();
		Believer believer = null;
		while(it.hasNext()){
			ActionCard card = it.next(); 
			if(card instanceof Believer){ //on retourne le premier croyant de la liste
				believer = (Believer) card;
			}
		}
		return believer;
	}

	/**Methode permettant de recuperer un guide de la main du bot de maniere Random
	 * @return un guide
	 */
	public SpiritGuide getSpiritGuide(){
		List<ActionCard> liste = getHand();
		Collections.shuffle(liste); //pour obtenir le guide spirit de maniere random
		Iterator<ActionCard> it = liste.iterator();
		SpiritGuide spiritGuide = null;
		while(it.hasNext()){
			ActionCard card = it.next(); 
			if(card instanceof SpiritGuide){ //on retourne le premier SpiritGuide de la liste
				spiritGuide = (SpiritGuide) card;
			}
		}
		return spiritGuide;
	}

	/**Methode permettant de recuperer une carte apocalypse de la main du bot de maniere Random
	 * @return une apocalypse
	 */
	public ActionCard getApocalypse(){
		List<ActionCard> liste = getHand();
		Collections.shuffle(liste); //pour obtenir l'apocalypse de maniere random
		Iterator<ActionCard> it = liste.iterator();
		ActionCard apocalypse = null;
		while(it.hasNext()){
			ActionCard card = it.next(); 
			if(card instanceof ApocalypseWithOrigin || card instanceof Apocalypse){ //on retourne la premiere apocalypse croyant de la liste
				apocalypse = (ActionCard) card;
			}
		}
		return apocalypse;
	}

	/**Methode permettant de recuperer toutes les cartes croyants de la main du bot
	 * @return liste des croyants
	 */
	public LinkedList<Believer> getBelievers(){
		LinkedList<ActionCard> liste = getHand();
		Iterator<ActionCard> it = liste.iterator();
		LinkedList<Believer> believers = new LinkedList<Believer>();
		while(it.hasNext()){
			ActionCard card = it.next(); 
			if(card instanceof Believer){ //on retourne le premier croyant de la liste
				believers.add((Believer) card);
			}
		}
		return believers;
	}

	/**Methode permettant de recuperer toutes les cartes guides de la main du bot
	 * @return liste des guides
	 */
	public LinkedList<SpiritGuide> getSpiritGuides(){
		List<ActionCard> liste = getHand();
		Iterator<ActionCard> it = liste.iterator();
		LinkedList<SpiritGuide> spiritGuides = new LinkedList<SpiritGuide>();
		while(it.hasNext()){
			ActionCard card = it.next(); 
			if(card instanceof SpiritGuide){ //on retourne le premier SpiritGuide de la liste
				spiritGuides.add((SpiritGuide) card);
			}
		}
		return spiritGuides;
	}

	/**Methode permettant de recuperer toutes les cartes apocalypse de la main du bot
	 * @return liste des apocalypse
	 */	public LinkedList<Apocalypse> getApocalypses(){
		 List<ActionCard> liste = getHand();
		 Collections.shuffle(liste);
		 Iterator<ActionCard> it = liste.iterator();
		 LinkedList<Apocalypse> apocalypses = new LinkedList<Apocalypse>();
		 while(it.hasNext()){
			 ActionCard card = it.next(); 
			 if(card instanceof Apocalypse){ //on retourne la premiere apocalypse croyant de la liste
				 apocalypses.add((Apocalypse) card);
			 }
		 }
		 return apocalypses;
	 }

	 /**Methode permettant de deposer un croyant de sa main a la table en fonction de sa strategie*/
	 public void depotCroyant(){
		 strategy.depotCroyant();
	 }

	 /**Methode permettant de convertir des croyants avec le guide en fonction de sa strategie*/
	 public void convertirCroyants(){
		 strategy.convertirCroyants();
	 }

	 /**Methode permettant de lancer une apocalypse en fonction de sa strategie*/
	 public void lancerApocalypse(){
		 strategy.lancerApocalypse();
	 }

	 /**Methode qui test si le nombre de point est suffisant pour lancer une carte
	  * @param card la carte a lancer
	  * @return true si les points sont suffisants
	  */
	 public boolean pointsOrigineSuffisants(ActionCardWithOrigin card){
		 EnumCosmogonie cosmogonie = card.getOrigine();
		 if(this.getDicoPA().get(cosmogonie) >= 1){
			 return true;
		 }
		 else if(cosmogonie == EnumCosmogonie.NEANT){//pour convertir les points NEANT en Cosmogonie
			 if(this.getDicoPA().get(EnumCosmogonie.JOUR) >= 2)//si on 2 points jour ca passe 
				 return true;
			 else if(this.getDicoPA().get(EnumCosmogonie.NUIT) >= 2)//si on a 2 point nuit ca passe aussi
				 return true;
			 else
				 return false;//sinon l'action ne peut etre faite	
		 }
		 return false;
	 }

	 public boolean isLast(){
		 //TODO a faire en sorte que cette methode marche faire une hasmap avec les bot et le score et utiliser une methode sort pour les tries
		 Player p;
		 
		 System.out.println("///////////////////");/////////////////////////////////////////////////////////////////////
		 System.out.println("Score des joueurs dans la game");/////////////////////////////////////////////////////////////////
		 ArrayList<Player> players1 = GameManager.getInstanceUniqueManager().getPlayers();//////////////////////////////////////////////
		 Iterator<Player> itPlayer1 = players1.iterator();////////////////////////////////////////////////////////////
		 while(itPlayer1.hasNext()){///////////////////////////////////////////////////////////////////////////
			 p = itPlayer1.next();//////////////////////////////////////////////////////////////////////////////////
			 System.out.println(p.getNom() + " a un score : "+ p.getScore());/////////////////////////////////////////////////:
		 }

		
		 ArrayList<Player> players = GameManager.getInstanceUniqueManager().getPlayers();
		 Iterator<Player> itPlayer = players.iterator();
		 while(itPlayer.hasNext()){
			 p = itPlayer.next();
			 if(p.getScore() < this.getScore())//si un joueur a un score strictement inferieur au this alors il n'est pas dernier
				 return false;
		 }
		 System.out.println(this.getNom() + " est dernier avec un score : "+this.getScore());//////////////////////////////////////////////
		 return true;
	 }

	 @Override
	 public Player pickTarget() throws TargetSelectionException {
		 return strategy.pickTarget();
	 }

	 @Override
	 public EnumCosmogonie pickOrigine(ActionCardWithOrigin carte) {
		 return strategy.pickOrigine(carte);
	 }

	 @Override
	 public List<Believer> pickCroyant(SpiritGuide carte) {
		 return strategy.pickCroyant(carte);
	 }

	 @Override
	 public String toString() {
		 return super.toString();
	 }

	 /**Getter de la strategie
	  * @return la strategie des bots
	  */
	 public static Strategy getStrategy() {
		 return strategy;
	 }

	 /**Setter de la strategie
	  * @param strategy la strategie choisi au tout debut de la partie
	  */
	 private void setStrategy(Strategy strategy) {
		 Bot.strategy = strategy;
	 }

	@Override
	public void attacher(ObservateurJoueurReel o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detacher(ObservateurJoueurReel o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player notifySelectPlayer() throws ObservateurNotLinkedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyStartTour() throws ObservateurNotLinkedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumCosmogonie notifySelectOriginePA(ActionCardWithOrigin carte) throws ObservateurNotLinkedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Believer> notifySelectCroyant(SpiritGuide guideSpirituel) throws ObservateurNotLinkedException {
		// TODO Auto-generated method stub
		return null;
	}
}