package model.cards.withoutOriginCards;

import java.io.Serializable;

import model.cards.ActionCard;
import model.player.Player;

public class Apocalypse extends ActionCard implements Serializable{

	

	public Apocalypse() {
		super("Apocalypse");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliserPouvoir(String commande, Player joueur) throws Exception {
		pouvoirs.get(commande).onAction(this, joueur);
		joueur.defausserCarte(this);
	}
	
	//faire une methode du nom de apocalypse qui recupere le nombre de joueur et qui compte le nombre de croyants de chaque joueur
	
	@Override
	public String toString() {
		return "Apocalypse [nom=" + nom + "]";
	}
}
