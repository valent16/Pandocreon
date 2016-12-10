package capacites;

import model.game.GameManager;
import model.player.Player;

//Classe mere des sacrifices
public abstract class Sacrifice {
    
	//Methode qui applique le sacrifice de la carte
    public abstract void effectuerSacrifice(Player player, GameManager gameManager);
    
}
