package capacites;

import java.util.Arrays;

import model.game.GameManager;
import model.player.Player;

public class EmpecheSacrificeGuideCM extends CapaciteSpeciale {

    // Empeche une divinité possédant le dogme Chaos ou Mystique de sacrifier un de ses guides spirituels durant ce tour
    
    public EmpecheSacrificeGuideCM() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        for (Player player : gameManager.getPlayers()) {
            if(Arrays.toString(player.getHand().getCartesDivinites().getDogme()).contains("Chaos")||Arrays.toString(player.getHand().getCartesDivinites().getDogme()).contains("Mystique")){
                player.setDroitSacrifierGuide(false);
            }
        }
    }

    
}
