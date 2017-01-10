package view;

import model.player.Player;

public interface IViewGame {
	public void majJoueurs();
	
	public void majFaceDe();
	
	public void majTableCroyant();
	
	public void majNbTours();
	
	public void afficherVainqueur(Player p);
	
	public void afficherDefaite(Player p);
	
	public void changementJoueur();
	
	public void majJoueurActif();
}
