package model.cards.OriginCards;



import model.cards.ActionCard;
import model.enumType.EnumCosmogonie;

/**Classe abstraite d'une carte action avec une origine*/
public abstract class ActionCardWithOrigin extends ActionCard {
	
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
