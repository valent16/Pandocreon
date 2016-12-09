package capacites;

import java.util.Iterator;

import model.game.GameManager;
import model.player.Player;

public class CapaciteBrewalen extends CapaciteSpeciale {

    // Capacite de la divinite Brewalen : Peut empecher l'utilisation d'une carte Apocalypse
    
    public CapaciteBrewalen() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while(it.hasNext()) {
           it.next().setDroitJouerApocalyspse(false); /////////bloque un joueur de lancer une Apocalypse
        }
    }
}
