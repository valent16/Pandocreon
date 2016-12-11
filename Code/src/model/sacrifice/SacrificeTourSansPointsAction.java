package model.sacrifice;

import java.util.Iterator;

import model.game.GameManager;
import model.player.Player;

public class SacrificeTourSansPointsAction extends Sacrifice {
    
    // Jusqu'à la fin du tour, plus aucune divinite ne reçoit de points d'action
   
	@Override
    public void effectuerSacrifice(Player player, GameManager gameManager) {
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while(it.hasNext()){
            Player j = it.next();
            j.setDroitRecevoirPointAction(false); //TODO a autoriser de recevoir des points d'action
        }
    }

}
