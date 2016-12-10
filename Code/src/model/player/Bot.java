package model.player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import model.EnumType.Cosmogonie;
import model.cards.ActionCard;
import model.cards.OriginCards.Believer;
import model.exception.TargetSelectionException;
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
		HashMap<Cosmogonie, Integer> pointsAction = this.getDicoPA();
		System.out.println("les points du BOT "+ this.getNom() +": "  
			+ pointsAction.get(Cosmogonie.JOUR) +" Point Jour | "
			+ pointsAction.get(Cosmogonie.NUIT) +" Point Nuit | " + 
			+ pointsAction.get(Cosmogonie.NEANT) +" Point Néant"); 
		

		//depart
		int action = (int) (Math.random() * 5) + 1;
		System.out.println("LACTION "+action);
		switch (action){
		case 1: //jouer une carte random et active son pouvoir
			if(this.getHand().size() != 0){	//si le bot possede des cartes
				strategy.jouerCarte();
				System.out.println("action2");
			}else{//sinon on rappelle la methode pour jouer
				jouerTour(); 
			}
			break;

		case 2://poser un croyant
			if(getBelievers().size() !=0){
				System.out.println("on a des croyants");
				this.afficherHand();
				//appeler une methode strategy.poserCroyant()
			}else{
				System.out.println("on a pas des croyants");
			}
			break;

		case 3: //recuperer des croyants avec le guide spirituel

			System.out.println("action3");
			break;

		case 4: //defausser une ou plusieurs carte
			//si sa main est vide on rappelle la methode jouer
			//this.defausserCartes(partie.getCartesDefaussees());
			System.out.println("action4");
			break;

		case 5: //completer sa main jusqu'a 7
			if(this.getHand().size() < 7){
				//this.completerMain(partie.getPioche());
				//this.jouer(partie);
				System.out.println("pas assez de carte");
			}else{
				this.jouerTour();
			}
			break;
		case 6: //Sacrifier une carte
			break;
		case 7://jouer une apocalypse
			break;
		default:
			break;
		}
	}


	//recupere la strategie des Bots
	public static Strategy getStrategy() {
		return strategy;
	}
	
	//recupere tous les croyants de la main du bot
	private LinkedList<Believer> getBelievers(){
		Iterator<ActionCard> it = getHand().iterator();
		LinkedList<Believer> believers = new LinkedList<Believer>();
		while(it.hasNext()){
			ActionCard card = it.next(); 
			if(card instanceof Believer){//si c'est un croyant on l'ajoute dans la liste finale
				believers.add((Believer) card);
			}
		}
		return believers;
	}

	//permet de setup le niveau dses bots
	private void setStrategy(Strategy strategy) {
		Bot.strategy = strategy;
	}

	@Override
	public String toString() {
		return super.toString();
		//return "Bot [strategy=" +" getStrategy()=" + getStrategy() + "]"+super.toString();
	}

	@Override
	public Player pickTarget() throws TargetSelectionException {
		// TODO Auto-generated method stub
		return null;
	}
}