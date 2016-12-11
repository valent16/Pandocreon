package model.sacrifice;

import java.util.Iterator;
import model.cards.OriginCards.Believer;
import model.game.GameManager;
import model.player.Player;

public class SacrificeDefausserCroyantsMystique extends Sacrifice{

    // Defausse tous les croyants ayant le dogme Mystique actuellement au centre de la table
    
    @Override
    public void effectuerSacrifice(Player player, GameManager gameManager) {
        Iterator<Believer> it = gameManager.getCroyants().iterator();
        while (it.hasNext()) {
        	//si c'est un croyant on la defausse
        }
    }
}
