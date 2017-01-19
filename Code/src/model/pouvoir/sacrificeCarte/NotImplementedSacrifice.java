package model.pouvoir.sacrificeCarte;

import model.cards.Card;
import model.player.Player;
import model.pouvoir.Pouvoir;
/**Sacrifice qui permet a une carte de ne pas avoir de sacrifice*/
public class NotImplementedSacrifice extends Pouvoir {
	
	/**Constructeur*/
	public NotImplementedSacrifice() {
		super("not implemented sacrifice");
	}
	
	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		System.out.println("sacrifice non implemente");
	}
}
