package controller;
import model.player.Player;

/**Interface pour gerer le gestionnaire de partie*/
public interface IObserverGameManager {
	
	/**Methode permettant d'annoncer a la vue la defaite d'un joueur*/
	public void annoncerDefaitJoueur(Player joueur);
	
	/**Methode permettant d'annoncer a la vue la victoire d'un joueur*/
	public void annoncerVictoireJoueur(Player joueur);
	
	/**Methode permettant de mettre a jour la liste de croyants sur la table a jour*/
	public void miseAJourCroyants();
	
	/**Methode permettant de mettre a jour la liste des joueurs de la partie*/
	public void miseAJourJoueurs();
	
	/**Methode permettant de mettre a jour le nombre de tour de la partie*/
	public void miseAJourNbTour();
	
	/**Methode permettant de mettre a jour le de de la partie*/
	public void miseAJourDe();
}