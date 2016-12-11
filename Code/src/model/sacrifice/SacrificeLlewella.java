package model.sacrifice;

import java.util.Iterator;

import model.cards.withoutOriginCards.Apocalypse;
import model.game.GameManager;
import model.player.Player;

/**Sacrifice de la divinite Llewella : Peut obliger un joueur à poser une carte Apocalypse s'il en possede une*/
public class SacrificeLlewella extends Sacrifice { 

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Apocalypse apocalypse = null;
		Iterator<Player> it = gameManager.getPlayers().iterator();
		while(it.hasNext()) {
			for (int i=0; i<it.next().getHand().size(); i++) {
				if (it.next().getHand().get(i) instanceof Apocalypse) {
					apocalypse = ((Apocalypse) (it.next().getHand().get(i)));
					break;
				}
			}
		}
		if(apocalypse != null){
			try {
				apocalypse.utiliserPouvoir("declencher apocalypse", player);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
