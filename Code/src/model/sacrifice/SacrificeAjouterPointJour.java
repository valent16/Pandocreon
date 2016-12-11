package model.sacrifice;

import model.EnumType.EnumCosmogonie;
import model.game.GameManager;
import model.player.Player;

public class SacrificeAjouterPointJour extends Sacrifice{
    // Donne un point d'action d'origine Jour
    
    @Override
    public void effectuerSacrifice(Player player, GameManager gameManager){
    	player.incrementerPointAction(EnumCosmogonie.JOUR, 1);

    }

}
