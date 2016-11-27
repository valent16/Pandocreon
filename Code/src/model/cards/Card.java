package model.cards;

import java.util.ArrayList;

/**Classe abstraite qui represente n'importe quel carte*/
public abstract class Card {

	/**nom du fichier de l'image de la carte*/
	private String img;
	
	/**nom de la carte*/
	protected String nom;

	/**Constructeur de la classe Carte pour instncirer le cartes (Croyants, Guide, Apocalypse, Divinité)
	 * @param nom le nom de la carte
	 */
	public Card(String nom){
		this.nom = nom;
	}

	/**renvoie img (le nom du fichier qui contiens l'image de la carte)*/
	public String getImg(){
		return this.img;
	}
	
	public abstract void utiliserPouvoir(String commande);
	
}
