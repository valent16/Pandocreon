package game;

import cards.Card;

/////////////////////IL FAUT CREER UNE PIOCHE AUTREMENT*/
public class Pioche {

	static CardGame pioche;

	/**Constructeur qui permet d'avoir toutes les cartes du jeu dans la pioche*/
	public Pioche(){
		pioche = Game.cardgame;
	}

	public static void Melanger() {
		pioche.melangerCarte();
	}

	public static CardGame getCarte() {
		return pioche;
	}

	public static Card tirerCarte(){
		return CardGame.JDC.pop();
	}	
}
