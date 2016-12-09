package capacites;

import model.game.GameManager;
import model.player.Player;

public class CapaciteGorpa extends CapaciteSpeciale {

    // Capacite de la divinite Gorpa : Peut recuperer les points d'action d'une autre divinite en plus des siens. L'autre divinite ne reçoit aucun point d'action ce tour-ci
    
    public CapaciteGorpa() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        
    }   
}
