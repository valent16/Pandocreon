package capacites;

import model.EnumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;

public class SacrificeEmpecheSacrificeCroyantHM extends Sacrifice {

    // Empeche une divinité possédant le dogme Humain ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour

    @Override
    public void effectuerSacrifice(Player player, GameManager gameManager) {
        for (Player p : gameManager.getPlayers()) {
            if (p.getDivinity().getDogmes().contains(EnumDogme.HUMAIN) || p.getDivinity().getDogmes().contains(EnumDogme.MYSTIQUE)) {
               //TODO Trouver une solution a ça p.setDroitSacrifierCroyant(false);
            }
        }
    }


}
