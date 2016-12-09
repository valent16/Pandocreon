package capacites;

import model.game.GameManager;
import model.player.Player;

//Classe mere des capacites
public abstract class CapaciteSpeciale {
     
    public abstract void effectuerCapaciteSpeciale(Player player, GameManager gameManager);
    
}
