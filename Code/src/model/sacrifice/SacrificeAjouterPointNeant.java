package model.sacrifice;

import model.EnumType.EnumCosmogonie;
import model.player.Player;

/**Donne un point d'action d'origine Neant*/
public class SacrificeAjouterPointNeant extends Sacrifice{
    
    @Override
    public void effectuerSacrifice(Player player) {
    	player.incrementerPointAction(EnumCosmogonie.NEANT, 1);

    }

}
