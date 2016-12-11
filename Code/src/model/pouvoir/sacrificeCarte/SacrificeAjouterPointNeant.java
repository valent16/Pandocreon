package model.pouvoir.sacrificeCarte;

import model.EnumType.EnumCosmogonie;
import model.cards.Card;
import model.player.Player;
import model.pouvoir.Pouvoir;
import model.sacrifice.Sacrifice;

/**Donne un point d'action d'origine Neant*/
public class SacrificeAjouterPointNeant extends Pouvoir{
    
	public SacrificeAjouterPointNeant() {
		super("sacrifice");
	}
	
	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		joueur.incrementerPointAction(EnumCosmogonie.NEANT, 1);
	}

}
