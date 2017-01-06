package model.sacrifice;

import model.EnumType.EnumCosmogonie;
import model.player.Player;

/**Donne un point d'action d'origine Jour*/
public class SacrificeAjouterPointJour extends Sacrifice{
    // Donne un point d'action d'origine Jour

    @Override
    public void effectuerSacrifice(Player player){
    	player.incrementerPointAction(EnumCosmogonie.JOUR, 1);
    }
}
