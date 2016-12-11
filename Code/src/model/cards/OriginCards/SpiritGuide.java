package model.cards.OriginCards;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import model.EnumType.EnumDogme;
import model.player.Player;
import model.EnumType.EnumCosmogonie;

/**Classe représentant une carte guide spirituel*/
public class SpiritGuide extends CarteDogmatique implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int pointPriere;
	
	private int nbCarteCroyant;
	
	private ArrayList<Believer> croyantsConvertis = new ArrayList<Believer>();
	
	public SpiritGuide(String nom, EnumCosmogonie origine, ArrayList<EnumDogme> dogmes, int nbCarteCroyant, String sacrifice) {
		super(nom, origine, dogmes, sacrifice);
		this.pointPriere = 0;
		this.nbCarteCroyant = nbCarteCroyant;
	}

	public List<Believer> getCroyantsConvertis(){
		return Collections.unmodifiableList(croyantsConvertis);
	}
	
	@Override
	public void utiliserPouvoir(String commande,Player joueur) throws Exception {
		this.pouvoirs.get(commande).onAction(this, joueur);
		joueur.retirerCarte(this);
	}
	
	//permet de savoir si un croyant a au moins un dogme en commun avec le guide
	public boolean isCroyantCompatible(Believer croyant){
		Iterator<EnumDogme> itDogmeCroyant = croyant.getDogmes().iterator();
		EnumDogme d;
		while(itDogmeCroyant.hasNext()){
			d = itDogmeCroyant.next();
			if (this.getDogmes().contains(d)){
				return true;
			}
		}
		return false;
	}
	
	//Permet de determiner si un guide poss�de un croyant donne
	public boolean hasCroyant(Believer croyant){
		if (croyantsConvertis.contains(croyant)){
			return true;
		}
		return false;
	}
	
	public boolean hasNoCroyant(){
		if (croyantsConvertis.size() == 0){
			return true;
		}
		return false;
	}
	
	//permet de supprimer un croyant d'un guide
	public void supprimerCroyant(Believer croyant){
		croyantsConvertis.remove(croyant);
	}
	
	//methode permettant de convertir un croyant
	public void convertirCroyant(Believer croyant){
		croyantsConvertis.add(croyant);
	}
	
	public int getNbMaxCroyant(){
		return nbCarteCroyant;
	}

	@Override
	public String toString() {
		return "SpiritGuide nom=" + nom + "[pointPriere=" + pointPriere + ", nbCarteCroyant=" + nbCarteCroyant + ", ]"+super.toString();
	}
}
