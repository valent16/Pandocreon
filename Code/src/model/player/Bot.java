package model.player;

import java.util.Iterator;
import java.util.LinkedList;

import model.cards.ActionCard;
import model.cards.OriginCards.Believer;
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

	//permet de setup le niveau dses bots
	private void setStrategy(Strategy strategy) {
		Bot.strategy = strategy;
	}
	
	@Override
	public String toString() {
		return super.toString();
		//return "Bot [strategy=" +" getStrategy()=" + getStrategy() + "]"+super.toString();
	}
}