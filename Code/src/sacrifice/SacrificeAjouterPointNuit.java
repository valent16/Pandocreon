package capacites;

import model.EnumType.Cosmogonie;
import model.game.GameManager;
import model.player.Player;


public class SacrificeAjouterPointNuit extends Sacrifice{

    // Donne un point d'action d'origine Nuit

    @Override
    public void effectuerSacrifice(Player player, GameManager gameManager) {
    	player.incrementerPointAction(Cosmogonie.NUIT, 1);
    }

}