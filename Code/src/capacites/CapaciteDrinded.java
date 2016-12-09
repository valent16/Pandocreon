package capacites;

import java.util.Iterator;

import model.game.GameManager;
import model.player.Player;

public class CapaciteDrinded extends CapaciteSpeciale {

    // Capacite de la divinite Drinded : Peut empecher le sacrifice d'un des guides spirituels de n'importe quel joueur
    
    public CapaciteDrinded() {}
   
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager){
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while(it.hasNext()) {
            it.next().setDroitSacrifierGuide(false); //Faire une methode pour empecher le sacrifice du guie par ce joueur
        }
    }
    
}
