package model.cards.OriginCards;

import java.io.Serializable;


import model.EnumType.EnumCosmogonie;
import model.cards.ActionCard;

/**Classe abstraite d'une carte action avec une origine*/
public abstract class ActionCardWithOrigin extends ActionCard implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**Attribut representant l'origine de la carte*/
	private EnumCosmogonie origine;
	
	/**Constructeur
	 * @param nom le nom de la carte
	 * @param origine l'origine de la carte
	 */
	public ActionCardWithOrigin(String nom, EnumCosmogonie origine) {
		super(nom);
		this.setOrigine(origine);
	}
	
	/**Getter Origine
	 * @return l'origine de la carte
	 */
	public EnumCosmogonie getOrigine() {
		return origine;
	}
	
	/**Setter Origine
	 * @param origine l'origine a donner a la carte
	 */
	private void setOrigine(EnumCosmogonie origine) {
		this.origine = origine;
	}

	@Override
	public String toString() {
		return "ActionCardWithOrigin [origine=" + origine + ", nom=" + nom + "]";
	}
}
