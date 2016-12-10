package sacrifice;

import model.EnumType.Cosmogonie;
import model.game.GameManager;
import model.player.Player;

public class SacrificeAjouterPointNeant extends Sacrifice{
   
    // Donne un point d'action d'origine Neant
    
    @Override
    public void effectuerSacrifice(Player player, GameManager gameManager) {
    	player.incrementerPointAction(Cosmogonie.NEANT, 1);
    }

}
