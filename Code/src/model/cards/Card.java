package model.cards;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import model.player.Player;
import model.pouvoir.Pouvoir;

/**Classe abstraite qui represente n'importe quel carte*/
public abstract class Card {

	/**Identifiant unique de la carte*/
	private UUID id = UUID.randomUUID();

	/**Attribut representant l'ensemble des pouvoirs des cartes*/
	protected HashMap<String, Pouvoir> pouvoirs = new HashMap<String, Pouvoir>();
	
	/**Attribut representant le nom de la carte*/
	protected String nom;

	/**Constructeur de la classe Carte pour instancier le cartes (Croyants, Guide, Apocalypse, Divinité)
	 * @param nom le nom de la carte
	 */
	public Card(String nom){
		this.nom = nom;
	}
	
	/**
	 * Getter de l'attribut nom
	 * @return nom de la carte
	 */
	public String getNom(){
		return nom;
	}
	
	@Override
	public String toString() {
		return "Card [nom=" + nom + "]";
	}
	
	/**
	 * Getter de l'identifiant de l'instance
	 * @return l'id de la carte
	 */
	public UUID getID(){
		return this.id;
	}
	
	/**Methode permettant d'utiliser le pouvoir de la carte
	 * @param commande: le pouvoir en question
	 * @param joueur: le joueur qui lance le pouvoir
	 * @throws Exception dans le cas ou le joueur n'a pas assez de points
	 */
	public abstract void utiliserPouvoir(String commande, Player joueur) throws Exception;

	/**getter permettant de recuperer l'ensemble des pouvoirs de la carte
	 * @return l'ensemble des pouvoirs
	 */
	public Map<String, Pouvoir>  getPouvoirs(){
		return Collections.unmodifiableMap(pouvoirs);
	}
	
	/**Methode permettant d'ajouter des pouvoirs a la carte
	 * @param commande: le nom de la commande pour appeler le pouvoir
	 * @param pouvoir: le pouvoir a ajouter
	 */
	public void ajouterPouvoir(String commande, Pouvoir pouvoir){
		this.pouvoirs.put(commande, pouvoir);
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
	
	
}
