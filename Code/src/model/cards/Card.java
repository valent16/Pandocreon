package model.cards;

import java.util.ArrayList;

/**Classe abstraite qui represente n'importe quel carte*/
public abstract class Card {

	/**nom du fichier de l'image de la carte*/
	private String img;
	
	protected String nom;

	/**L'origine de la carte*/
	protected Origin origine;

	/**Liste des dogmes de la carte*/
	protected ArrayList<Dogme> dogmes = new ArrayList<Dogme>(3);
	
	/**Etat de la carte*/
	protected Etat etat;

	/**énumeration pour l'origine des cartes*/
	protected enum Origin{
		JOUR,
		AUBE,
		CREPUSCULE,
		NUIT
	}

	/**énumeration pour les dogmes des cartes*/
	protected enum Dogme{
		NATURE,
		HUMAIN,
		SYMBOLE,
		MYSTIQUE,
		CHAOS
	}
	
	 /**Définir les etats possibles de cartes*/
	 protected enum Etat {ENTAS,PIOCHEE,JOUEE,DEFAUSSEE}

	/**Constructeur de la classe Carte pour instncirer le cartes (Croyants, Guide, Apocalypse, Divinité)
	 * @param nom le nom de la carte
	 * @param origine l'origine de la carte 
	 * @param dogme1 le dogme 1 de la carte
	 * @param dogme2 le dogme 2 de la carte
	 * @param dogme3 le dogme 3 de la carte
	 */
	public Card(String nom, Origin origine, Dogme dogme1, Dogme dogme2, Dogme dogme3 ){
		this.nom = nom;
		this.origine = origine;
		this.dogmes.add(dogme1);
		this.dogmes.add(dogme2);
		this.dogmes.add(dogme3);	
	}

	/**renvoie img (le nom du fichier qui contiens l'image de la carte)*/
	public String getImg(){
		return this.img;
	}

	/**getter pour l'origine de la carte*/
	public Origin getOrigine() {
		return origine;
	}

	/**setter pour l'origine de la carte*/
	public void setOrigine(Origin origine) {
		this.origine = origine;
	}

	/**getter pour les dogmes de la carte*/
	public ArrayList<Dogme> getDogmes() {
		return dogmes;
	}

	/**setter pour les dogmes de la carte*/
	public void setDogmes(ArrayList<Dogme> dogmes) {
		this.dogmes = dogmes;
	}

}
