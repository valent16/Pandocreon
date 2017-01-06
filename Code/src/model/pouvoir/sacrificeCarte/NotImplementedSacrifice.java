package model.pouvoir.sacrificeCarte;

import model.cards.Card;
import model.player.Player;
import model.pouvoir.Pouvoir;

public class NotImplementedSacrifice extends Pouvoir {
	public NotImplementedSacrifice() {
		super("not implemented sacrifice");
	}
	
	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		System.out.println("sacrifice non implemente");
	}
}
