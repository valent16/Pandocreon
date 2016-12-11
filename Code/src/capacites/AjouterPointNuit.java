package capacites;

import model.EnumType.EnumCosmogonie;
import model.game.GameManager;
import model.player.Player;


public class AjouterPointNuit extends CapaciteSpeciale {

    // Donne un point d'action d'origine Nuit
    public AjouterPointNuit() {}

    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
    	player.incrementerPointAction(EnumCosmogonie.NUIT, 1);
    }

}