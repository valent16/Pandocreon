package capacites;

import java.util.Iterator;

import model.game.GameManager;
import model.player.Player;

public class SacrificeBrewalen extends Sacrifice{

    // Sacrifice de la divinite Brewalen : Peut empecher l'utilisation d'une carte Apocalypse
    
    @Override
    public void effectuerSacrifice(Player player, GameManager gameManager) {
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while(it.hasNext()) {
           it.next().setDroitJouerApocalyspse(false); ///////// TODO bloque un joueur de lancer une Apocalypse
        }
    }
}
