package capacites;

import model.game.GameManager;
import model.player.Player;

public class DetruireGuideJourNeant extends CapaciteSpeciale {

    // Detruit une carte guide spirituel d'origine jour ou neant, dont la capacite speciale n'a pas d'effet. Les croyants attaches reviennent au centre de la table.
    public DetruireGuideJourNeant(){}

    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
    }
}
