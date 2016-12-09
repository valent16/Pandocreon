package capacites;

import java.util.Arrays;
import java.util.Iterator;
import joueur.Joueur;
import model.game.GameManager;
import model.player.Player;
import partie.Partie;

public class EmpecheSacrificeCroyantNM extends CapaciteSpeciale {
    // Empeche une divinité possédant le dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour
    
    public EmpecheSacrificeCroyantNM(){}

    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        Iterator<Player> it = gameManager.getPlayers().iterator();
        while (it.hasNext()) {
            Player j = it.next();
            if(Arrays.toString(j.getMain().getCartesDivinites().getDogme()).contains("Nature")||Arrays.toString(j.getMain().getCartesDivinites().getDogme()).contains("Mystique")){
                j.setDroitSacrifierCroyant(false);
            }
        }
    }

}
