package capacites;

import model.EnumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;

public class SacrificeEmpecheSacrificeGuideHS extends Sacrifice {

    // Empeche une divinité possédant le dogme Humain ou Symboles de sacrifier un de ses guides spirituels durant ce tour
    
    @Override
    public void effectuerSacrifice(Player player, GameManager gameManager) {
        for (Player p : gameManager.getPlayers()) {
            if(p.getDivinity().getDogmes().contains(EnumDogme.HUMAIN) || p.getDivinity().getDogmes().contains(EnumDogme.SYMBOLE)){
              //TODO trouver une solution a ça  p.setDroitSacrifierGuide(false);
            }
        }
    }
}
