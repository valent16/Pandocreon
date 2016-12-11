package sacrifice;

import model.EnumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;

/**Sacrifice qui empeche une divinité possédant le dogme Chaos ou Mystique de sacrifier un de ses guides spirituels durant ce tour*/
public class SacrificeEmpecheSacrificeGuideCM extends CapaciteSpeciale {
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        for (Player p : gameManager.getPlayers()) {
            if(p.getDivinity().getDogmes().contains(EnumDogme.CHAOS) || p.getDivinity().getDogmes().contains(EnumDogme.MYSTIQUE)){
                //TODO trouver une solution a ca :: p.setDroitSacrifierGuide(false);
            }
        }
    }

    
}
