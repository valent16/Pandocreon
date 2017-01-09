package model.cards;

import java.io.Serializable;
import java.util.ArrayList;

import model.EnumType.EnumDogme;
import model.EnumType.EnumOrigineDivinite;
import model.player.Player;

/**Classe representant une carte Divinite*/
public class Divinity extends Card implements Serializable{

	private static final long serialVersionUID = 1L;

	/**Attribut representant la description de la carte*/
	private String description;
	
	/**Attribut representant les dogmes de la carte*/
	private ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();
	
	/**Attribut representant l'origine de la carte*/
	private EnumOrigineDivinite origine;

	/**Constructeur d'une carte divinité
	 * @param nom le nom de la divinite
	 * @param dogmes les dogmes de la divinite
	 * @param description la description de la carte
	 * @param origine l'origine de la divinite
	 */
	public Divinity(String nom, ArrayList<EnumDogme> dogmes, String description, EnumOrigineDivinite origine) {
		super(nom);
		//faire une deep copy 
		this.description = description;
		this.dogmes = dogmes;
		this.origine = origine;
	}

	@Override
	public void utiliserPouvoir(String commande, Player joueur) throws Exception {
		//TODO pas eu le temps de developper cette methode
	}
	
	/**Getter pour recuperer l'origine de la divinite
	 * @return l'origine de la divinite
	 */
	public EnumOrigineDivinite getOrigine(){
		return origine;
	}
	
	/**Getter pour recuperer les dogmes de la divinite
	 * @return les dogmes de la divinite
	 */
	public ArrayList<EnumDogme> getDogmes(){
		return dogmes;
	}

	@Override
	public String toString() {
		return super.toString()+" Divinity [description=" + description + ", dogmes=" + dogmes + ", origine="+ this.origine +"]";
	}
}
