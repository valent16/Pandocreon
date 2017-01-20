package model.cards.withoutOriginCards;


import model.cards.ActionCard;
import model.player.Player;
/**Classe Apocalypse sans origine*/
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
		
	@Override
	public String toString() {
		return "Apocalypse [nom=" + nom + "]";
	}
}
