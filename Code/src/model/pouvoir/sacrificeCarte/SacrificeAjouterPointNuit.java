package model.pouvoir.sacrificeCarte;

import model.EnumType.EnumCosmogonie;
import model.cards.Card;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Donne un point d'action d'origine Nuit*/
public class SacrificeAjouterPointNuit extends Pouvoir{

	public SacrificeAjouterPointNuit() {
		super("sacrifice");
	}
	
	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		joueur.incrementerPointAction(EnumCosmogonie.NUIT, 1);
	}
}