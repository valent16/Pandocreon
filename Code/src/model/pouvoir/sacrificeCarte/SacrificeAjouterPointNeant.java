package model.pouvoir.sacrificeCarte;

import model.cards.Card;
import model.enumType.EnumCosmogonie;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Donne un point d'action d'origine Neant*/
public class SacrificeAjouterPointNeant extends Pouvoir{
    
	/**Constructeur*/
	public SacrificeAjouterPointNeant() {
		super("sacrifice");
	}
	
	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		joueur.incrementerPointAction(EnumCosmogonie.NEANT, 1);
	}
}
