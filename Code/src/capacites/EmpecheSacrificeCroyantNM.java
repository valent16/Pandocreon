package capacites;

import java.util.Arrays;
import java.util.Iterator;
import model.EnumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;

public class EmpecheSacrificeCroyantNM extends CapaciteSpeciale {
    // Empeche une divinité possédant le dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour
    
    public EmpecheSacrificeCroyantNM(){}

    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while (it.hasNext()) {
            Player p = it.next();
            if(p.getDivinity().getDogmes().contains(EnumDogme.NATURE)|| p.getDivinity().getDogmes().contains(EnumDogme.MYSTIQUE)){
                p.setDroitSacrifierCroyant(false);
            }
        }
    }

}
