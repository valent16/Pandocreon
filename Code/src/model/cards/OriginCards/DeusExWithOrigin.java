package model.cards.OriginCards;

import java.io.Serializable;
import model.EnumType.EnumCosmogonie;
import model.player.Player;

/**Classe DeusEx avec une origine*/
public class DeusExWithOrigin extends ActionCardWithOrigin implements Serializable{

	private static final long serialVersionUID = 1L;

	/**Constructeur 
s	 * @param nom le nom de la carte DeusEx
	 * @param origine l'origine de la carte DeusEx
	 */
	public DeusExWithOrigin(String nom, EnumCosmogonie origine) {
		super(nom, origine);
	}

	@Override
	public void utiliserPouvoir(String commande,Player joueur) throws Exception{
		//TODO pas eu le temps de developper cette methode
	}

	@Override
	public String toString() {
		return "DeusExWithOrigin [nom=" + nom + ", origine=" +this.getOrigine()+"]";
	}
}
