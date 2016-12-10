package sacrifice;

import java.util.Iterator;

import model.cards.withoutOriginCards.Apocalypse;
import model.game.GameManager;
import model.player.Player;

public class SacrificeLlewella extends Sacrifice {

    // Sacrifice de la divinite Llewella : Peut obliger un joueur à poser une carte Apocalypse s'il en possede une
    
    @Override
    public void effectuerSacrifice(Player player, GameManager gameManager) {
        Apocalypse apocalypse;
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while(it.hasNext()) {
            for (int i=0; i<it.next().getHand().size(); i++) {
                if (it.next().getHand().get(i) instanceof Apocalypse) {
                    apocalypse = ((Apocalypse) (it.next().getHand().get(i)));
                    break;
                }
            }
            break;
        }
        // TODO appeller la methode apocalypse avec apocalypse.<nom_methdode()>
    }
    
}
