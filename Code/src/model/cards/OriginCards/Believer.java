package model.cards.OriginCards;

import java.util.ArrayList;

import model.EnumType.EnumDogme;
import model.EnumType.EnumOrigineCA;

/**Classe représentant une carte croyant*/
public class Believer extends CarteDogmatique{

	private int nbPrieres;
	
	public Believer(String nom, EnumOrigineCA origine, ArrayList<EnumDogme> dogmes, String nomTypeCarte, int nbPrieres) {
		super(nom, origine, dogmes, nomTypeCarte);
		this.nbPrieres = nbPrieres;
	}

	@Override
	public void utiliserPouvoir(String commande) {
		// TODO Auto-generated method stub
		
	}

}