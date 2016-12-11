package model.sacrifice;

import java.util.Iterator;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.SpiritGuide;
import model.player.Player;

/**Sacrifice de la divinite Gwenghelen : Recupere autant de points d'Action d'origine Neant que le nombre de guides spirituels que la divinite possede*/
public class SacrificeGwenghelen extends Sacrifice{
	
	@Override
	public void effectuerSacrifice(Player player){
		int nbSpiritGuide = 0;
		Iterator<SpiritGuide> it = player.getGuides().iterator();
		while (it.hasNext()) {
			nbSpiritGuide++;
		}
		player.incrementerPointAction(EnumCosmogonie.NEANT, nbSpiritGuide);
		System.out.println("Vous avez récupéré "+nbSpiritGuide+" points d'Action supplémentaires d'origine Neant");
	} 
}