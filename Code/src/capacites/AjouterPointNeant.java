package capacites;

import model.EnumType.EnumCosmogonie;
import model.game.GameManager;
import model.player.Player;

public class AjouterPointNeant extends CapaciteSpeciale {
   
    // Donne un point d'action d'origine Neant

        public AjouterPointNeant() {}

    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
    	player.incrementerPointAction(EnumCosmogonie.NEANT, 1);
    }

}
