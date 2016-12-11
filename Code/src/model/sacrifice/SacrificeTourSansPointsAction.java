package model.sacrifice;

import java.util.Iterator;

import model.game.GameManager;
import model.player.Player;

/**Sacrifice qui permet jusqu'a la fin du tour, plus aucune divinite ne reçoit de points d'action*/
public class SacrificeTourSansPointsAction extends Sacrifice {
   
	@Override
    public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while(it.hasNext()){
            Player p = it.next();
           // p.setDroitRecevoirPointAction(false); //TODO a autoriser de recevoir des points d'action
        }
    }

}
