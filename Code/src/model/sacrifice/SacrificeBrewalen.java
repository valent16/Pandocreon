package model.sacrifice;

import java.util.Iterator;

import model.game.GameManager;
import model.player.Player;

/**Sacrifice de la divinite Brewalen : Empeche l'utilisation d'une carte Apocalypse*/
public class SacrificeBrewalen extends Sacrifice{
    
    @Override
    public void effectuerSacrifice(Player player) {
    	GameManager gameManager = GameManager.getInstanceUniqueManager();
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while(it.hasNext()) {
          // it.next().setDroitJouerApocalyspse(false); ///////// TODO bloque un joueur de lancer une Apocalypse
        }
    }
}
