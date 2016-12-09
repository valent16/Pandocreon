package capacites;

import java.util.Arrays;

import model.game.GameManager;
import model.player.Player;

public class EmpecheSacrificeGuideHS extends CapaciteSpeciale {

    // Empeche une divinité possédant le dogme Humain ou Symboles de sacrifier un de ses guides spirituels durant ce tour
    
    public EmpecheSacrificeGuideHS() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        for (Player player : gameManager.getPlayers()) {
            if(Arrays.toString(player.getHand().getCartesDivinites().getDogme()).contains("Humain")||Arrays.toString(player.getHand().getCartesDivinites().getDogme()).contains("Symboles")){
                player.setDroitSacrifierGuide(false);
            }
        }
    }
}
