package model.gestionDonnees;

import java.util.LinkedList;

import model.cards.ActionCard;
import model.cards.Divinity;

/**Interface de chargement d'une partie*/
public interface IDataLoad {
	
	/**Methode permettant de charger les cartes du jeu
	 * @return les cartes chargees
	 */
	public LinkedList<ActionCard> chargerCartes();
	
	/**Methode permettant de charger les divintes
	 * @return les cartes divinites chargees
	 */
	public LinkedList<Divinity> chargerDivinites();
	
	/**Methode permettant de charger une partie*/
	public void chargerPartie();
}
