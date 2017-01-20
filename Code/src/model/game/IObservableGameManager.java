package model.game;

import model.player.Player;

/**Interface peremttant au controller d'interagir avec la vue lors des victoires et des defaites des joueurs*/
public interface IObservableGameManager {	
	
	/**Methode permettant de declarer la victoire d'un joueur
	 * @param p le joueur victorieux
	 */
	public void notifyPlayerVictory(Player p);
	
	/**Methode permettant de declarer la defaite d'un joueur
	 * @param p le joueur perdant
	 */
	public void notifyPlayerDefeat(Player p);
	
	/**Methode permettant de notifier le controlleur lors du changement de la liste de croyants sur table*/
	public void notifyChangementCroyants();
	
	/**Methode permettant de notifier le controller lors du changement de tour de jeu*/
	public void notifyChangementTour();
	
	/**Methode permettant de notifier le controller lors du changement de contenu de la liste de joueur*/
	public void notifyChangementJoueurs();
	
	/**Methode permettant de notifier le controller lors du changement du joueur actif*/
	public void notifyJoueurActif();
}
