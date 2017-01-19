package model.pouvoir.sacrificeCarte;

import model.cards.Card;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Sacrifice qui permet de piocher deux cartes au hasard dans la main d'un autre joueur*/
public class SacrificePiocherDeuxCartes extends Pouvoir{
	
	/**Constructeur*/
	public SacrificePiocherDeuxCartes() {
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		//TODO A developper sacrifice qui fait piocher 2 cartes au joueur		
	}
}
