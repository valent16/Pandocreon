package model.cards.OriginCards;

import model.EnumType.EnumOrigineCA;

import java.util.ArrayList;

import model.EnumType.EnumDogme;

public abstract class CarteDogmatique extends ActionCardWithOrigin {

	private ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();
	
	private String nomTypeCarte;
	
	public CarteDogmatique(String nom, EnumOrigineCA origine, ArrayList<EnumDogme> dogmes, String nomTypeCarte) {
		super(nom, origine);
		//faire une deepCopy
		this.dogmes = dogmes;
		this.nomTypeCarte = nomTypeCarte;
	}

}
