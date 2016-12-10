package capacites;

import java.util.Iterator;

import model.EnumType.Cosmogonie;
import model.cards.OriginCards.SpiritGuide;
import model.game.GameManager;
import model.player.Player;

public class CapaciteGwenghelen extends CapaciteSpeciale {

	// Capacite de la divinite Gwenghelen : Recupere autant de points d'Action d'origine Neant que le nombre de guides spirituels que la divinite possede

	public CapaciteGwenghelen() {}

	@Override
	public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
		int nbSpiritGuide = 0;
		Iterator<SpiritGuide> it = player.getGuides().iterator();
		while (it.hasNext()) {
			nbSpiritGuide++;
		}
		player.incrementerPointAction(Cosmogonie.NEANT, nbSpiritGuide);
		System.out.println("Vous avez récupéré "+nbSpiritGuide+" points d'Action supplémentaires d'origine Neant");
	} 
}
