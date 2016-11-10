package player;

import strategy.*;

/**Classe qui représente un ordinateur avec une stratégie de jeu*/
public class Bot extends Player{

	/**Constructeur de joueur qui est appelé pour créer un ordinateur*/
	public Bot(String pseudo) {
		super(pseudo);
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
}