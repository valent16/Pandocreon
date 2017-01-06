package model.pouvoir.sacrificeCarte;

import model.cards.Card;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Sacrifice qui permet de prendre trois cartes dans la main d'un autre joueur et de les placer dans notre main*/
public class SacrificePrendreTroisCartes extends Pouvoir{

    public SacrificePrendreTroisCartes() {
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		// TODO //recuperer 3 cartes
		
	}
}