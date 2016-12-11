package model.pouvoir.sacrificeCarte;

import model.EnumType.EnumCosmogonie;
import model.cards.Card;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;
import model.sacrifice.Sacrifice;

/**Donne un point d'action d'origine Jour*/
public class SacrificeAjouterPointJour extends Pouvoir{
    // Donne un point d'action d'origine Jour
    
    public SacrificeAjouterPointJour() {
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		joueur.incrementerPointAction(EnumCosmogonie.JOUR, 1);
	}
}
