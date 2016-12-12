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
import model.exception.TargetSelectionException;
import model.game.GameManager;
import model.strategy.*;

/**Un joueur qui représente un ordinateur avec une stratégie de jeu*/
public class Bot extends Player{
	public final static int AGE_BOT = 200;

	/**recupere la stratégie choisi au départ pour tous les bots*/
	private static Strategy strategy;

	/**Constructeur de joueur qui est appelé pour créer un ordinateur*/
	public Bot(String pseudo, Strategy strat) {
		super(pseudo, AGE_BOT);
		setStrategy(strat);
		strategy.setBot(this); //donne les parametres du bot a la stratégie
	}

	//permet de setup le niveau des bots
	private void setStrategy(Strategy strategy) {
		Bot.strategy = strategy;
	}

	//recupere la strategie des Bots
	public static Strategy getStrategy() {
		return strategy;
	}

	/**Methode qui fait jouer les bots avec la difficulté choisie*/
	public void jouerTour(){	
		incrementerPointActionWithDe();
		strategy.jouer(this);
	}

	//Tests si le bot possede des croyants dans sa main
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

	//Test si le bot possede des GuidSpirit dans sa main
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

	//Test si le bot possede des Apocalypse dans sa main
	public boolean hasApocalypse(){
		Iterator<ActionCard> it = getHand().iterator();
		while(it.hasNext()){
			ActionCard card = it.next();
			if (card instanceof Apocalypse || card instanceof ApocalypseWithOrigin){
				System.out.println("YOU HAVE AN APOCALYPSE");
				return true;
			}
		}
		return false;
	}

	//recupere une carte croyant de la main du bot de maniere Random
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

	//recupere un guide spirit de la main du bot de maniere Random
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

	//recupere une apocalypse de la main du bot de maniere random
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

	//recupere tous les croyants de la main du bot
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

	//recupere tous les guide spirit de la main du bot
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

	//recupere toutes les apocalypses de la main du bot
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

	//Methode qui permet au bot de se defausser d'une carte de maniere random utiliser pour la strategie Random et Easy
	public void defausserRandom(){
		int indexCard = (int) (Math.random() * this.getNbCartes())+1;
		ActionCard card = this.getHand().get(indexCard);
		this.defausserCarte(card);
		System.out.println("se defausse de la carte "+ card);
	}

	/**permet au bot de deposer un croyant de sa main a la table
	retourne true si il peut poser un coryant sinon false*/
	public void depotCroyant(){
		strategy.depotCroyant();
	}
	
	//permet de convertir des croyants avec le guide donne en parametre
	public void convertirCroyants(){
		strategy.convertirCroyants();
	}
	
	//permet au bot de lancer une apocalypse
	public void lancerApocalypse(){
		strategy.lancerApocalypse();
	}

	//verifie si le nombre de point est suffisant pour effectuer l'action voulu sur la carte en parametre
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

	//Methode pour tester si le bot est dernier au score
	public boolean isLast(){
		int place = 1;
		ArrayList<Player> players = GameManager.getInstanceUniqueManager().getPlayers();
		Iterator<Player> it = players.iterator();
		while(it.hasNext()){
			if(this.getScore() >= it.next().getScore()){
				place++;
			}
		}
		if(place == GameManager.getInstanceUniqueManager().getNbJoueur()){
			System.out.println("la place du joueur = "+ place);
			System.out.println("le nombre de joueur = "+ GameManager.getInstanceUniqueManager().getNbJoueur());
			return true;
		}
		else{
			System.out.println("la place du joueur = "+ place);
			System.out.println("le nombre de joueur = "+ GameManager.getInstanceUniqueManager().getNbJoueur());
			return false;
		}
	}

	@Override
	//permet de choisir un joueur au besoin
	public Player pickTarget() throws TargetSelectionException {
		return strategy.pickTarget();
	}

	@Override
	//permet de savoir si on prend un point de neant ou deux points jour ou 2 points nuit pour une carte Neant
	public EnumCosmogonie pickOrigine(ActionCardWithOrigin carte) {
		return strategy.pickOrigine(carte);
	}

	@Override
	//permet de recuperer des croyants
	public List<Believer> pickCroyant(SpiritGuide carte) {
		return strategy.pickCroyant(carte);
	}

	@Override
	public String toString() {
		return super.toString();
		//return "Bot [strategy=" +" getStrategy()=" + getStrategy() + "]"+super.toString();
	}
}