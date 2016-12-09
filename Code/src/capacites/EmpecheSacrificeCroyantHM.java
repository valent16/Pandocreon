package capacites;

import java.util.Arrays;
import java.util.Iterator;

import model.game.GameManager;
import model.player.Player;

public class EmpecheSacrificeCroyantHM extends CapaciteSpeciale {

    // Empeche une divinité possédant le dogme Humain ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour
    public EmpecheSacrificeCroyantHM() {}

    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        for (Player p : gameManager.getPlayers()) {
            if (Arrays.toString(p.getHand().getCartesDivinites().getDogme()).contains("Humain") || Arrays.toString(p.getHand().getCartesDivinites().getDogme()).contains("Mystique")) {
                p.setDroitSacrifierCroyant(false);
            }
        }
    }


}
