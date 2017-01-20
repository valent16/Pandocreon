package view;

/**Interface pour mettre a jour le joueur humain*/
public interface IViewJoueurReel {

	/**Methode permettant de mettre a jour la main du joueur*/
	public void majDeckCarte();
	
	/**Methode permettant de mettre a jour les points d'actions du joueur*/
	public void majPointsAction();
	
	/**Methode permettant de mettre a jour les croyants et guides rattaches joueur*/
	public void majCartesRattachees();
	
	/**Methode permettant de mettre a jour la divinite du joueur*/
	public void majDivinite();
	
	/**Methode permettant de faire demarrer le tour du joueur*/
	public void startTour();
	
	/**Methode permettant de savoir si le tour de jeu du joueur est fini
	 * @return true si le tour du joueur est fini sinon false
	 */
	public boolean isTourFinished();
}
