package view;
import model.player.Player;


public interface IObservateurGameManager {
	public void annoncerDefaitJoueur(Player joueur);
	
	public void annoncerVictoireJoueur(Player joueur);
}