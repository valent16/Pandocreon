package model.pouvoir.sacrificeCarte;

import model.cards.Card;
import model.enumType.EnumCosmogonie;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Donne un point d'action d'origine Nuit*/
public class SacrificeAjouterPointNuit extends Pouvoir{

	/**Constructeur*/
	public SacrificeAjouterPointNuit() {
		super("sacrifice");
	}
	
	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		joueur.incrementerPointAction(EnumCosmogonie.NUIT, 1);
	}
}