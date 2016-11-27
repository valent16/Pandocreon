package model.cards.OriginCards;

import java.util.ArrayList;

import model.EnumType.EnumDogme;
import model.EnumType.EnumOrigineCA;

/**Classe représentant une carte guide spirituel*/
public class SpiritGuide extends CarteDogmatique{
	
	private int pointPriere;
	
	private int nbCarteCroyant;
	
	public SpiritGuide(String nom, EnumOrigineCA origine, ArrayList<EnumDogme> dogmes,String nomTypeCarte, int pointPriere, int nbCarteCroyant) {
		super(nom, origine, dogmes, nomTypeCarte);
		this.pointPriere = pointPriere;
		this.nbCarteCroyant = nbCarteCroyant;
	}

	@Override
	public void utiliserPouvoir(String commande) {
		// TODO Auto-generated method stub
	}
}
