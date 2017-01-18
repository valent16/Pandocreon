package model.cards.OriginCards;

import model.EnumType.EnumCosmogonie;
import model.player.Player;

/**Classe DeusEx avec une origine*/
public class DeusExWithOrigin extends ActionCardWithOrigin{

	private String description;
	
	/**Constructeur 
s	 * @param nom le nom de la carte DeusEx
	 * @param origine l'origine de la carte DeusEx
	 */
	public DeusExWithOrigin(String nom, EnumCosmogonie origine, String description) {
		super(nom, origine);
		
		this.description = description;
	}
	
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
