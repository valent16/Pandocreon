package player;

import strategy.*;

/**Classe qui représente un ordinateur avec une stratégie de jeu*/
public class Bot extends Player{

	/**Constructeur de joeuur qui est appelé*/
	public Bot(String pseudo) {
		super(pseudo);
	}

	////////////////////////////////////////////////////////////
	public static void jouer(Strategy strategy,int num){
		strategy.jouer(num);
	}
	
////////////////////////////////////////////////////////////
	public static void JouerFacile(int i) {
		System.out.println("BOT : difficulté facile");
		jouer(new EasyStrategy(), i);
	}

////////////////////////////////////////////////////////////
	public static void JouerMoyen(int i) {
		System.out.println("BOT : difficulté moyen");
		jouer(new MediumStrategy(), i);	
	}

////////////////////////////////////////////////////////////
	public static void JouerDifficile(int i) {
		System.out.println("BOT : difficulté difficile");
		jouer(new HardStrategy(), i);
	}
}