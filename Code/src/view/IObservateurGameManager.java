package view;
import model.player.Player;

/**Interface pour gerer le gestionnaire de partie*/
public interface IObservateurGameManager {
	
	/**Methode permettant d'annoncer a la vue la defaite d'un joueur*/
	public void annoncerDefaitJoueur(Player joueur);
	
	/**Methode permettant d'annoncer a la vue la victoire d'un joueur*/
	public void annoncerVictoireJoueur(Player joueur);
}
