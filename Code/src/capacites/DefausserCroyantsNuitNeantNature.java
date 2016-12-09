package capacites;

import model.game.GameManager;
import model.player.Player;

public class DefausserCroyantsNuitNeantNature extends CapaciteSpeciale {

    // Tous les croyants d'origine Nuit ou Neant et ayant le dogme Nature, actuellement sur la table sont defausses. Les capacites speciales ne sont pas jouees
    
    public DefausserCroyantsNuitNeantNature() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
    }
    
}
