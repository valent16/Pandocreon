package model.player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.ActionCard;
import model.cards.OriginCards.ActionCardWithOrigin;
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

	/**Methode qui fait jouer les bots avec la difficulté choisie*/
	public void jouerTour(){	
		incrementerPointActionWithDe();
		strategy.jouer(this);
	}

	//recupere la strategie des Bots
	public static Strategy getStrategy() {
		return strategy;
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
			if (card instanceof Apocalypse){
				return true;
			}
		}
		return false;
	}

	//recupere une carte croyant de la main du bot de maniere Random
	public Believer getBeliever(){
		LinkedList<ActionCard> liste = (LinkedList<ActionCard>) getHand();
		Collections.shuffle(liste);
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

	//recupere tous un guide spirit de la main du bot de maniere Random
	public SpiritGuide getSpiritGuide(){
		List<ActionCard> liste = getHand();
		Collections.shuffle(liste);
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

	//recupere une apocalypse de la main du bot
	public Apocalypse getApocalypse(){
		List<ActionCard> liste = getHand();
		Collections.shuffle(liste);
		Iterator<ActionCard> it = liste.iterator();
		Apocalypse apocalypse = null;
		while(it.hasNext()){
			ActionCard card = it.next(); 
			if(card instanceof Apocalypse){ //on retourne la premiere apocalypse croyant de la liste
				apocalypse = (Apocalypse) card;
			}
		}
		return apocalypse;
	}

	//permet de setup le niveau des bots
	private void setStrategy(Strategy strategy) {
		Bot.strategy = strategy;
	}

	//Methode qui permet au bot de se defausser d'une carte de maniere random utiliser pour la strategie Random et Easy
	public void defausserRandom(){
		int indexCard = (int) (Math.random() * this.getNbCartes())+1;
		ActionCard card = this.getHand().get(indexCard);
		this.defausserCarte(card);
		System.out.println("se defausse de la carte "+ card);
	}

	//Methode qui permet de jouer une carte Random utiliser pour la strategie Random et Easy
	public void jouerCarteRandom(){
		int indexCard = (int) (Math.random() * this.getNbCartes())+1;
		ActionCard card = this.getHand().get(indexCard);
		System.out.println("Le bot "+ this.getNom()+" active le pouvoir de la carte "+ this.getHand().get(indexCard));
		System.out.println("Il faut appeller la bonne methode LA CARTE A ACTIVER LE POUVOIR "+ this.getHand().get(indexCard));
		System.out.println(card.getPouvoirs());
		//card.utiliserPouvoir(commande, joueur); //TODO activer le pouvoir de la carte
	}
	
	//permet au bot de deposer un croyant de sa main a la table
	public void DepotCroyant(){
		Believer believer = this.getBeliever();//recupere le croyant
		GameManager.getInstanceUniqueManager().deposerCroyant(believer);
		this.ajouterCroyant(believer);
		System.out.println("le bot "+ this.getNom() +" a posé le croyant "+ believer);
	}

	@Override
	public Player pickTarget() throws TargetSelectionException {
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

	@Override
	public String toString() {
		return super.toString();
		//return "Bot [strategy=" +" getStrategy()=" + getStrategy() + "]"+super.toString();
	}
}