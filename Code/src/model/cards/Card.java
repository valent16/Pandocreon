package model.cards;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import model.player.Player;
import model.pouvoir.Pouvoir;

/**Classe abstraite qui represente n'importe quel carte*/
public abstract class Card implements Serializable{
	private UUID id = UUID.randomUUID();

	protected HashMap<String, Pouvoir> pouvoirs = new HashMap<String, Pouvoir>();
	
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
	
	@Override
	public String toString() {
		return "Card [nom=" + nom + "]";
	}
	
	
	public UUID getID(){
		return this.id;
	}
	
	public abstract void utiliserPouvoir(String commande, Player joueur) throws Exception;

	
	public Map<String, Pouvoir>  getPouvoirs(){
		return Collections.unmodifiableMap(pouvoirs);
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Card)) return false;
	    if (((Card) other).getID() == this.getID()){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	//Methode permettant d'ajouter des pouvoirs a une carte
	public void ajouterPouvoir(String commande, Pouvoir pouvoir){
		this.pouvoirs.put(commande, pouvoir);
	}
	
}
