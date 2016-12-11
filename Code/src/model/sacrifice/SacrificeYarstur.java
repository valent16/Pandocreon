package model.sacrifice;

import java.util.Iterator;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.Believer;
import model.game.GameManager;
import model.player.Player;

/**Sacrifice de la divinite Yarstur : Detruit toutes les cartes de Croyants au centre de la table d'origine Neant*/
public class SacrificeYarstur extends Sacrifice {

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Believer> it = gameManager.getCroyants().iterator();
		while (it.hasNext()) {
			Believer believer = it.next();
			if (believer.getOrigine().equals(EnumCosmogonie.NEANT)) { //test si le croyant est d'origine neant
				gameManager.retirerCroyant(believer);
				gameManager.defausserCarte(believer);
			}
		}
		System.out.println(player.getNom()+" a détruit toutes les cartes croyants d'origine Neant");
	}
}
