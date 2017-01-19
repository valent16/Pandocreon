package model.pouvoir.sacrificeCarte;

import model.cards.Card;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Sacrifice qui fait revenir un guide spirituel revient dans la main de sa divinite. Ses croyants reviennent au centre de la table*/
public class SacrificeRetourGuideMainDivinite extends Pouvoir{

	/**Constructeur*/
	public SacrificeRetourGuideMainDivinite() {
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		//TODO A developper Sacrifice Faire revenir son guide dans sa main et les croyants au centre de la table
	}

}
