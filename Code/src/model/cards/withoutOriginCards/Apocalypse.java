package model.cards.withoutOriginCards;


import model.cards.ActionCard;
import model.player.Player;
/**Clase Apocalypse sans origine*/
public class Apocalypse extends ActionCard{

	/**Constructeur*/
	public Apocalypse() {
		super("Apocalypse");
	}

	@Override
	public void utiliserPouvoir(String commande, Player joueur) throws Exception {
		pouvoirs.get(commande).onAction(this, joueur);
		joueur.defausserCarte(this);
	}
	
	//TODO faire une methode du nom de apocalypse qui recupere le nombre de joueur et qui compte le nombre de croyants de chaque joueur
	
	@Override
	public String toString() {
		return "Apocalypse [nom=" + nom + "]";
	}
}
