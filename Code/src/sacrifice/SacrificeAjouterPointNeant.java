package sacrifice;

import model.EnumType.EnumCosmogonie;
import model.game.GameManager;
import model.player.Player;

public class SacrificeAjouterPointNeant extends Sacrifice{
   
    // Donne un point d'action d'origine Neant
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
    	player.incrementerPointAction(EnumCosmogonie.NEANT, 1);

    }

}
