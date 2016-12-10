package model.cards;

import java.io.Serializable;
import java.util.ArrayList;

import model.EnumType.EnumDogme;
import model.EnumType.EnumOrigineDivinite;
import model.player.Player;

//Represente une carte divinité
public class Divinity extends Card implements Serializable{

	private String description;
	
	private ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();
	
	private EnumOrigineDivinite origine;

	/**Création d'une carte divinité*/
	public Divinity(String nom, ArrayList<EnumDogme> dogmes, String description, EnumOrigineDivinite origine) {
		super(nom);
		//faire une deep copy 
		this.description = description;
		this.dogmes = dogmes;
		this.origine = origine;
	}

	@Override
	public void utiliserPouvoir(String commande, Player joueur) {
		// TODO Auto-generated method stub	
	}
	
	
	public EnumOrigineDivinite getOrigine(){
		return origine;
	}
	
	public ArrayList<EnumDogme> getDogmes(){
		return dogmes;
	}

	@Override
	public String toString() {
		return super.toString()+" Divinity [description=" + description + ", dogmes=" + dogmes + ", origine="+ this.origine +"]";
	}
}
