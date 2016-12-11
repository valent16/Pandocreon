package model.pouvoir;

import model.cards.Card;
import model.player.Player;

public abstract class Pouvoir {
	
	private String description;
	
	public Pouvoir(String description){
		this.description = description;
	}
	
	public abstract void onAction(Card carte, Player joueur) throws Exception;
}
