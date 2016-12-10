package capacites;

import java.util.Arrays;

import model.EnumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;

public class EmpecheSacrificeGuideCM extends CapaciteSpeciale {

    // Empeche une divinité possédant le dogme Chaos ou Mystique de sacrifier un de ses guides spirituels durant ce tour
    
    public EmpecheSacrificeGuideCM() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        for (Player p : gameManager.getPlayers()) {
            if(p.getDivinity().getDogmes().contains(EnumDogme.CHAOS) || p.getDivinity().getDogmes().contains(EnumDogme.MYSTIQUE)){
                p.setDroitSacrifierGuide(false);
            }
        }
    }

    
}
