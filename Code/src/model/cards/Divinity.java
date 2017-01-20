package model.cards;

import java.util.ArrayList;

import model.enumType.EnumDogme;
import model.enumType.EnumOrigineDivinite;
import model.player.Player;

public class Divinity extends Card{

	/**Attribut representant la description de la carte*/
	private String description;
	
	/**Attribut representant les dogmes de la carte*/
	private ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();
	
	/**Attribut representant l'origine de la carte*/
	private EnumOrigineDivinite origine;

	/**Constructeur d'une carte divinite
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
	
	/**
	 * Getter sur la description de la divinite
	 * @return description de la divinite
	 */
	public String getDescription(){
		return description;
	}

	@Override
	public void utiliserPouvoir(String commande, Player joueur) throws Exception {
		//TODO A developper le pouvoir des divinites
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
