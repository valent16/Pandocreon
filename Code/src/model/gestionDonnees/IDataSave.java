package model.gestionDonnees;

import model.game.GameManager;

/**Interface de sauvegarde d'une partie*/
public interface IDataSave {
	
	/**Methode permettant de sauvegarder une partie*/
	public void enregistrerPartie(GameManager gestionnaire);
}
