package model.pouvoir.sacrificeCarte;

import java.util.Iterator;

import model.cards.Card;
import model.cards.OriginCards.Believer;
import model.enumType.EnumCosmogonie;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Sacrifice de la divinite Yarstur : Detruit toutes les cartes de Croyants au centre de la table d'origine Neant*/
public class SacrificeYarstur extends Pouvoir{

	/**Constructeur*/
	public SacrificeYarstur() {
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Believer> it = gameManager.getCroyants().iterator();
		while (it.hasNext()) {
			Believer believer = it.next();
			if (believer.getOrigine().equals(EnumCosmogonie.NEANT)) { //test si le croyant est d'origine neant
				gameManager.retirerCroyant(believer);
				gameManager.defausserCarte(believer);
			}
		}
		System.out.println(joueur.getNom()+" a détruit toutes les cartes croyants d'origine Neant");
	}
}
