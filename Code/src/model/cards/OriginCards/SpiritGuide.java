package model.cards.OriginCards;

import java.io.Serializable;
import java.util.ArrayList;

import model.EnumType.EnumDogme;
import model.EnumType.EnumOrigineCA;

/**Classe représentant une carte guide spirituel*/
public class SpiritGuide extends CarteDogmatique implements Serializable{
	
	private int pointPriere;
	
	private int nbCarteCroyant;
	
	public SpiritGuide(String nom, EnumOrigineCA origine, ArrayList<EnumDogme> dogmes, int nbCarteCroyant, String sacrifice) {
		super(nom, origine, dogmes, sacrifice);
		this.pointPriere = 0;
		this.nbCarteCroyant = nbCarteCroyant;
	}

	@Override
	public void utiliserPouvoir(String commande) {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return "SpiritGuide [pointPriere=" + pointPriere + ", nbCarteCroyant=" + nbCarteCroyant + ", nom=" + nom + "]"+super.toString();
	}
}
