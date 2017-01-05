package model.pouvoir;

import model.cards.Card;
import model.player.Player;

public abstract class Pouvoir {

	/**Attribut decrivant le pouvoir*/
	private String description;

	/**Constructeur
	 * @param description la description du pouvoir
	 */
	public Pouvoir(String description){
		this.setDescription(description);
	}

	/**Getter de descripton
	 * @return la description du pouvoir
	 */
	public String getDescription() {
		return description;
	}

	/**Setter de la decription
	 * @param description la description du pouvoir
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**Methode permettant de lancer le pouvoir d'une carte
	 * @param carte la carte dont le pouvoir est lancer
	 * @param joueur le joueur qui lance le pouvoir de cette carte
	 * @throws Exception exception
	 */
	public abstract void onAction(Card carte, Player joueur) throws Exception;
}
