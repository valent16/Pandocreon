package model.game;

import model.player.Player;

/**Interface peremttant au controller d'interagir avec la vue lors des victoires et des defaites des joueurs*/
public interface IObservableGameManager {	
	
	/**Methode permettant de declarer de la victoire d'un joueur
	 * @param p le joueur victorieux
	 */
	public void notifyPlayerVictory(Player p);
	
	/**Methode permettant de declarer de la defaite d'un joueur
	 * @param p le joueur perdant
	 */
	public void notifyPlayerDefeat(Player p);
}
