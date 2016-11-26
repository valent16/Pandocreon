package model.player;

import model.strategy.*;

/**Un joueur qui représente un ordinateur avec une stratégie de jeu*/
public class Bot extends Player{
	public static final int AGE_BOT = 200;
	
	/**recupere la stratégie choisi au départ pour tous les bots*/
	private Strategy strategy;

	/**Constructeur de joueur qui est appelé pour créer un ordinateur*/
	public Bot(String pseudo, Strategy strat) {
		super(pseudo, AGE_BOT);
		this.setStrategy(strat);
	}

	/**Methode qui fait jouer les bots avec la difficulté choisie*/
	public static void play(Strategy strategy){
		strategy.play();
	}
	
	
	/**Methode qui configure les bots en difficulté facile*/
	public static void easyPlay() {
		System.out.println("BOT : difficulté facile");
		play(new EasyStrategy());
	}

	/**Methode qui configure les bots en difficulté moyenne*/
	public static void mediumPlay() {
		System.out.println("BOT : difficulté moyen");
		play(new MediumStrategy());	
	}

	/**Methode qui configure les bots en difficulté difficile*/
	public static void hardPlay() {
		System.out.println("BOT : difficulté difficile");
		play(new HardStrategy());
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
}