package capacites;

import model.EnumType.EnumCosmogonie;
import model.game.GameManager;
import model.player.Player;

public class AjouterPointJour extends CapaciteSpeciale {
    // Donne un point d'action d'origine Jour
    
    public AjouterPointJour() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager){
    	player.incrementerPointAction(EnumCosmogonie.JOUR, 1);
    }

}
