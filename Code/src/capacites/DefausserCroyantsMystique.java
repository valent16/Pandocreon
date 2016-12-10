package capacites;

import java.util.Iterator;

import model.cards.ActionCard;
import model.game.GameManager;
import model.player.Player;

public class DefausserCroyantsMystique extends CapaciteSpeciale {

    // Defausse tous les croyants ayant le dogme Mystique actuellement au centre de la table
    
    public DefausserCroyantsMystique() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        Iterator<ActionCard> it = gameManager.getCroyants().iterator();
        while (it.hasNext()) {
        	//si c'est un croyant on la defausse
        }
    }
}
