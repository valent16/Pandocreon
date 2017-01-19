package model.gestionDonnees;

import model.game.GameManager;

/**Interface de sauvegarde d'une partie*/
public interface IDataSave {
	
	/**Methode permettant de sauvegarder une partie
	 * @param gestionnaire le gestionnaire de partie a sauvegarder
	 */
	public void enregistrerPartie(GameManager gestionnaire);
}
