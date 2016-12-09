package capacites;

import java.util.Iterator;

import model.game.GameManager;
import model.player.Player;

public class TourSansPointsAction extends CapaciteSpeciale {
    
    // Jusqu'à la fin du tour, plus aucune divinite ne reçoit de points d'action
    
    public TourSansPointsAction() {}

    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while(it.hasNext()){
            Player j = it.next();
            j.setDroitRecevoirPointAction(false);
        }
    }

}
