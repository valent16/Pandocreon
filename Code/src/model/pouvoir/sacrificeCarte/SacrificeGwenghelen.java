package model.pouvoir.sacrificeCarte;

import java.util.Iterator;

import model.cards.Card;
import model.cards.OriginCards.SpiritGuide;
import model.enumType.EnumCosmogonie;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Sacrifice de la divinite Gwenghelen : Recupere autant de points d'Action d'origine Neant que le nombre de guides spirituels que la divinite possede*/
public class SacrificeGwenghelen extends Pouvoir{
	
	/**Constructeur*/
	public SacrificeGwenghelen(){
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		int nbSpiritGuide = 0;
		Iterator<SpiritGuide> it = joueur.getGuides().iterator();
		while (it.hasNext()) {
			nbSpiritGuide++;
		}
		joueur.incrementerPointAction(EnumCosmogonie.NEANT, nbSpiritGuide);
		System.out.println("Vous avez récupéré "+nbSpiritGuide+" points d'Action supplémentaires d'origine Neant");
	}
}
