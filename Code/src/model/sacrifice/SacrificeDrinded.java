package model.sacrifice;

import java.util.Iterator;

import model.game.GameManager;
import model.player.Player;

public class SacrificeDrinded extends Sacrifice {

	// Sacrifice de la divinite Drinded : Peut empecher le sacrifice d'un des guides spirituels de n'importe quel joueur

	@Override
	public void effectuerSacrifice(Player player){
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Player> it = gameManager.getPlayers().iterator();
		while(it.hasNext()) {
			//TODO Faire une methode pour empecher le sacrifice du guie par ce joueur it.next().setDroitSacrifierGuide(false); 
		}
	}
}
