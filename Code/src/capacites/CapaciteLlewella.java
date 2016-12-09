package capacites;

import java.util.Iterator;

import model.cards.withoutOriginCards.Apocalypse;
import model.game.GameManager;
import model.player.Player;

public class CapaciteLlewella extends CapaciteSpeciale {

    // Capacite de la divinite Llewella : Peut obliger un joueur à poser une carte Apocalypse s'il en possede une
    
    public CapaciteLlewella() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        Apocalypse apocalypse;
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while(it.hasNext()) {
            for (int i=0; i<it.next().getMain().getMainCartesActions().size(); i++) {
                if (it.next().getHand().getMainCartesActions().get(i) instanceof Apocalypse) {
                    apocalypse = ((Apocalypse) (it.next().getMain().getMainCartesActions().get(i)));
                    break;
                }
            }
            break;
        }
        apocalypse.bouleversement(gameManager.getPlayers(), gameManager.getDefausse(), gameManager);
    }
    
}
