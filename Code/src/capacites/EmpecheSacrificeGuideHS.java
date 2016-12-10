package capacites;

import java.util.Arrays;

import model.EnumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;

public class EmpecheSacrificeGuideHS extends CapaciteSpeciale {

    // Empeche une divinité possédant le dogme Humain ou Symboles de sacrifier un de ses guides spirituels durant ce tour
    
    public EmpecheSacrificeGuideHS() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        for (Player p : gameManager.getPlayers()) {
            if(p.getDivinity().getDogmes().contains(EnumDogme.HUMAIN) || p.getDivinity().getDogmes().contains(EnumDogme.SYMBOLE)){
                p.setDroitSacrifierGuide(false);
            }
        }
    }
}
