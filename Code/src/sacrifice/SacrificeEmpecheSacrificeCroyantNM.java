package capacites;

import java.util.Iterator;
import model.EnumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;

public class SacrificeEmpecheSacrificeCroyantNM extends Sacrifice {
    // Empeche une divinité possédant le dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour

    @Override
    public void effectuerSacrifice(Player player, GameManager gameManager) {
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while (it.hasNext()) {
            Player p = it.next();
            if(p.getDivinity().getDogmes().contains(EnumDogme.NATURE)|| p.getDivinity().getDogmes().contains(EnumDogme.MYSTIQUE)){
               //TODO trouver une solution p.setDroitSacrifierCroyant(false);
            }
        }
    }

}
