package model.cards;


/**Classe abstraite qui represente une carte action*/
public abstract class ActionCard extends Card{

	/**Constructeur
	 * @param nom le nom de la carte
	 */
	public ActionCard(String nom) {
		super(nom);
	}

	@Override
	public String toString() {
		return "ActionCard [nom=" + nom + "]";
	}
}
