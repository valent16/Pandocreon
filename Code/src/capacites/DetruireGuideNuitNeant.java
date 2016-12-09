package capacites;

import model.game.GameManager;
import model.player.Player;

public class DetruireGuideNuitNeant extends CapaciteSpeciale {

    // Detruit une carte Guide Spirituel d'origine Nuit ou Neant, dont la capacite speciale n'a pas d'effet. Les croyants attaches reviennent au centre de la table
    public DetruireGuideNuitNeant() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager){
    }
    
}
