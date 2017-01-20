package model.cards.OriginCards;

import model.enumType.EnumCosmogonie;
import model.player.Player;

/**Classe DeusEx avec une origine*/
public class DeusExWithOrigin extends ActionCardWithOrigin{

	private String description;
	
	/**Constructeur 
	 * @param nom le nom de la carte DeusEx
	 * @param origine l'origine de la carte DeusExe
	 * @param description la description de la carte
	 */
	public DeusExWithOrigin(String nom, EnumCosmogonie origine, String description) {
		super(nom, origine);
		
		this.description = description;
	}
	
	/**
	 * Getter de la description de la carte
	 * @return description de la carte
	 */
	public String getDescription(){
		return description;
	}

	@Override
	public void utiliserPouvoir(String commande,Player joueur) throws Exception{
		//TODO A developper le pouvoir des deusEx
	}

	@Override
	public String toString() {
		return "DeusExWithOrigin [nom=" + nom + ", origine=" +this.getOrigine()+"]";
	}
}
