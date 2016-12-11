package model.cards.OriginCards;

import model.EnumType.EnumCosmogonie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.EnumType.EnumDogme;

public abstract class CarteDogmatique extends ActionCardWithOrigin implements Serializable{

	private static final long serialVersionUID = 1L;

	private ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();
	
	private String sacrifice;
	
//	private String nomTypeCarte;
	
	public CarteDogmatique(String nom, EnumCosmogonie origine, ArrayList<EnumDogme> dogmes, String sacrifice) {
		super(nom, origine);
		//faire une deepCopy
		this.dogmes = dogmes;
//		this.nomTypeCarte = nomTypeCarte;
		this.sacrifice = sacrifice;
	}

	public List<EnumDogme> getDogmes(){
		return Collections.unmodifiableList(dogmes);
	}
	
	public boolean containsDogme(EnumDogme dogme){
		return dogmes.contains(dogme);
	}
	
	@Override
	public String toString() {
		return "CarteDogmatique [ origine="+getOrigine() +"  dogmes=" + dogmes + ", nom=" + nom + ", sacrifice="+sacrifice+"]";
	}
}
