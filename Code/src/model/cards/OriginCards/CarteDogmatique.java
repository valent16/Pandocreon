package model.cards.OriginCards;

import model.EnumType.EnumOrigineCA;

import java.io.Serializable;
import java.util.ArrayList;

import model.EnumType.EnumDogme;

public abstract class CarteDogmatique extends ActionCardWithOrigin implements Serializable{

	private ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();
	
	private String sacrifice;
	
//	private String nomTypeCarte;
	
	public CarteDogmatique(String nom, EnumOrigineCA origine, ArrayList<EnumDogme> dogmes, String sacrifice) {
		super(nom, origine);
		//faire une deepCopy
		this.dogmes = dogmes;
//		this.nomTypeCarte = nomTypeCarte;
		this.sacrifice = sacrifice;
	}

	@Override
	public String toString() {
		return "CarteDogmatique [dogmes=" + dogmes + ", nom=" + nom + ", sacrifice="+sacrifice+"]";
	}
}
