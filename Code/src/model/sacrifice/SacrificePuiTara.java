package model.sacrifice;

import java.util.Iterator;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.Believer;
import model.game.GameManager;
import model.player.Player;

/**le Sacrifice de la divinite PuiTara qui consiste a detruire toutes les cartes croyants au centre de la table d'origine jour*/
public class SacrificePuiTara extends Sacrifice{

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Believer> it = gameManager.getCroyants().iterator();
		while (it.hasNext()) {
			Believer believer = it.next();
			if (believer.getOrigine() == EnumCosmogonie.JOUR) { //on check si l'origne du croyant est jour
				gameManager.retirerCroyant(believer); //on la supprime de la table
				gameManager.defausserCarte(believer); //on la defausse
			}
		}
		System.out.println(player.getNom()+" a détruit toutes les cartes croyants au centre de la table d'origine Jour");
	}
}
