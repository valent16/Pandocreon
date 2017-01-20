package view;

import model.player.Player;

/**Interface pour mettre a jour la partie*/
public interface IViewGame {
	
	/**Methode permettant de mettre a jour les joueurs*/
	public void majJoueurs();
	
	/**Methode permettant de mettre a jour le De*/
	public void majFaceDe();
	
	/**Methode permettant de mettre a jour la table des croyants*/
	public void majTableCroyant();
	
	/**Methode permettant de mettre a jour le nombre de tours*/
	public void majNbTours();
	
	/**Methode permettant d'afficher la victoire d'un joueur
	 * @param player le joueur vainqueur
	 */
	public void afficherVainqueur(Player player);
	
	/**Methode permettant d'afficher la defaite d'un joueur
	 * @param player le joueur perdant
	 */
	public void afficherDefaite(Player player);
	
	/**Methode permettant de mettre a jour le l'interface representant la liste de joueurs*/
	public void changementJoueur();
	
	/**Methode permettant de mettre a jour le joueur actif*/
	public void majJoueurActif();
}	
