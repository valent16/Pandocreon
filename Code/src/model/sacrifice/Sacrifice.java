package model.sacrifice;

import model.player.Player;

//Classe mere des sacrifices
public abstract class Sacrifice {
    
	/**Methode qui applique le sacrifice de la carte*/
    public abstract void effectuerSacrifice(Player player);
    
    /* TODO Trouver un moyen de lier les capacités avec les cartes peut être avec des id.*/
    
    public String getText(){
    	return this.getClass().getSimpleName().toString();
    }
}