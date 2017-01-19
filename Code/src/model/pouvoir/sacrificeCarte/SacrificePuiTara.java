package model.pouvoir.sacrificeCarte;

import java.util.Iterator;

import model.cards.Card;
import model.cards.OriginCards.Believer;
import model.enumType.EnumCosmogonie;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**le Sacrifice de la divinite PuiTara qui consiste a detruire toutes les cartes croyants au centre de la table d'origine jour*/
public class SacrificePuiTara extends Pouvoir{

	/**Constructeur*/
	public SacrificePuiTara() {
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Believer> it = gameManager.getCroyants().iterator();
		while (it.hasNext()) {
			Believer believer = it.next();
			if (believer.getOrigine() == EnumCosmogonie.JOUR) { //on check si l'origne du croyant est jour
				gameManager.retirerCroyant(believer); //on la supprime de la table
				gameManager.defausserCarte(believer); //on la defausse
			}
		}
		System.out.println(joueur.getNom()+" a détruit toutes les cartes croyants au centre de la table d'origine Jour");	
	}
}
