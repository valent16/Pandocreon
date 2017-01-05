package model.cards.OriginCards;

import java.io.Serializable;
import java.util.ArrayList;

import model.EnumType.EnumDogme;
import model.player.Player;
import model.EnumType.EnumCosmogonie;

/**Classe représentant une carte croyant*/
public class Believer extends CarteDogmatique implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int nbPrieres;
	
	public Believer(String nom, EnumCosmogonie origine, ArrayList<EnumDogme> dogmes, int nbPrieres, String sacrifice) {
		super(nom, origine, dogmes, sacrifice);
		this.nbPrieres = nbPrieres;
	}

	public int getNbPrieres(){
		return nbPrieres;
	}
	
	@Override
	public void utiliserPouvoir(String commande, Player joueur) throws Exception {
		//decrementation des points d'action du joueur
		pouvoirs.get(commande).onAction(this, joueur);
		
		
		//tester si le pouvoir utilisé est un sacrifice ou un dépot de croyant
		joueur.retirerCarte(this);
	}
	
	@Override
	public String toString() {
		return "Believer nom=" + nom + "[nbPrieres=" + nbPrieres + "]"+ super.toString();
	}
}
