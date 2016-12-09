package model.player;

import model.strategy.*;

/**Un joueur qui représente un ordinateur avec une stratégie de jeu*/
public class Bot extends Player{
	public static final int AGE_BOT = 200;
	
	/**recupere la stratégie choisi au départ pour tous les bots*/
	private static Strategy strategy;

	/**Constructeur de joueur qui est appelé pour créer un ordinateur*/
	public Bot(String pseudo, Strategy strat) {
		super(pseudo, AGE_BOT);
		setStrategy(strat);
	}

	/**Methode qui fait jouer les bots avec la difficulté choisie*/
//	public static void play(Strategy strategy){
//		strategy.play();
//	}
	
	/*
	public static void easyPlay() {
		System.out.println("BOT : difficulté facile");
		play(new EasyStrategy());
	}

	public static void mediumPlay() {
		System.out.println("BOT : difficulté moyen");
		play(new MediumStrategy());	
	}

	public static void hardPlay() {
		System.out.println("BOT : difficulté difficile");
		play(new HardStrategy());
	}
	*/
	
	
	public void jouerTour(){
		incrementerPointActionWithDe();
		//Appel � la strat du Bot
	}

	public static Strategy getStrategy() {
		return strategy;
	}

<<<<<<< HEAD
	private void setStrategy(Strategy strategy) {
		this.strategy = strategy;
=======
	public static void setStrategy(Strategy strat) {
		strategy = strat;
>>>>>>> 8452066bdd43a7c52b8504de641b891348049a9b
	}
	
	@Override
	public String toString() {
		return super.toString();
		//return "Bot [strategy=" +" getStrategy()=" + getStrategy() + "]"+super.toString();
	}
}