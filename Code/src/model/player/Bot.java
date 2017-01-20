package model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.cards.ActionCard;
import model.cards.Card;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.ApocalypseWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.cards.withoutOriginCards.Apocalypse;
import model.enumType.EnumCosmogonie;
import model.exception.PAInsuffisantException;
import model.exception.TargetSelectionException;
import model.game.GameManager;
import model.strategy.Strategy;


/**Un joueur qui represente un ordinateur avec une strategie de jeu*/
public class Bot extends Player{
	public final static int AGE_BOT = 200;

	/**Attribut representant la strategie choisi au depart pour tous les bots*/
	private static Strategy strategy;

	/**Constructeur de joueur qui est appele pour creer un ordinateur
	 * @param pseudo le nom du bot
	 * @param strat la strategie du bot
	 */
	public Bot(String pseudo, Strategy strat) {
		super(pseudo, AGE_BOT);
		setStrategy(strat);
		strategy.setBot(this); //donne les parametres du bot a la stratégie
	}

	@Override
	public void jouerTour(){	
		incrementerPointActionWithDe();
		strategy.jouer(this);
	}

	/**Methode qui teste si le bot possede des croyants dans sa main
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

	/**Methode qui test si le bot possede des guides dans sa main
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

	/**Methode permettant de recuperer une carte croyant dans la main du bot de maniere Random
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

	/**Methode permettant de recuperer un guide dans la main du bot de maniere Random
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

	/**Methode permettant de recuperer une carte apocalypse dans la main du bot de maniere Random
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

	/**Methode permettant de recuperer toutes les cartes croyants dans la main du bot
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

	/**Methode permettant de recuperer toutes les cartes guides dans la main du bot
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

	/**Methode permettant de recuperer toutes les cartes apocalypses dans la main du bot
	 * @return liste des apocalypses
	 */	
	public LinkedList<Apocalypse> getApocalypses(){
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

	/**Methode permettant de deposer un croyant de sa main sur la table en fonction de sa strategie*/
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

	/**Methode qui test si le nombre de points est suffisant pour lancer une carte
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

	/**
	 * Methode permettant de determiner si le joueur est le dernier au classement
	 * @return boolean vrai ou faux
	 */
	public boolean isLast(){
		Player p;
		ArrayList<Player> players = GameManager.getInstanceUniqueManager().getPlayers();
		Iterator<Player> itPlayer = players.iterator();
		while(itPlayer.hasNext()){
			p = itPlayer.next();
			if(p.getScore() < this.getScore())//si un joueur a un score strictement inferieur au this alors il n'est pas dernier
				return false;
		}
		return true;
	}

	@Override
	public void piocher(){
		hand.push(GameManager.getInstanceUniqueManager().piocherCarte());
	}

	@Override
	public void rattacherGuide(Card carte){
		if(carte instanceof SpiritGuide){
			this.guidesRattaches.add((SpiritGuide) carte);
		}
	}	 

	@Override
	public void defausserCarte(ActionCard carte){
		hand.remove(carte);
		GameManager.getInstanceUniqueManager().defausserCarte(carte);
	}

	@Override
	public void defausserCartes(LinkedList<ActionCard> cartes){
		hand.removeAll(cartes);
		GameManager.getInstanceUniqueManager().defausserCarte(cartes);
	}

	@Override
	public void defausserGuideRattache(SpiritGuide guide){
		guidesRattaches.remove(guide);
		GameManager.getInstanceUniqueManager().defausserCarte(guide);
	}

	@Override
	public void incrementerPointAction(EnumCosmogonie typePA, int nbPA){
		dicoPA.put(typePA, dicoPA.get(typePA) + nbPA);		
	}

	@Override
	public void decrementerPointAction(EnumCosmogonie typePA, int nbPA) throws PAInsuffisantException{
		if ((dicoPA.get(typePA) - nbPA) < 0){
			throw new PAInsuffisantException("Pas assez de point d'action "+dicoPA.get(typePA));
		}
		dicoPA.replace(typePA, dicoPA.get(typePA), dicoPA.get(typePA) - nbPA);
	}


	@Override
	public void ajouterMain(ActionCard card){
		this.hand.push(card);
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
}
