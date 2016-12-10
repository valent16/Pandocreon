package model.cards.OriginCards;

import java.io.Serializable;

import java.util.ArrayList;

import model.EnumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;
import model.EnumType.Cosmogonie;

/**Classe reprÃ©sentant une carte croyant*/
public class Believer extends CarteDogmatique implements Serializable{

	private int nbPrieres;
	
	public Believer(String nom, Cosmogonie origine, ArrayList<EnumDogme> dogmes, int nbPrieres, String sacrifice) {
		super(nom, origine, dogmes, sacrifice);
		this.nbPrieres = nbPrieres;
	}

	@Override
	public void utiliserPouvoir(String commande, Player joueur) {
		//décrémentation des points d'action du joueur
		
		joueur.retirerCarte(this);
		pouvoirs.get(commande).onAction(this, joueur);
	}

	@Override
	public String toString() {
		return "Believer [nbPrieres=" + nbPrieres + "]"+ super.toString();
	}

	
}
