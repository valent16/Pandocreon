package view;

import controller.JoueurController;
import model.player.Human;

public abstract class ViewJoueur {
	Human joueur;
	
	JoueurController controller;

	public ViewJoueur(JoueurController controller, Human j){
		this.controller = controller;
		this.joueur = j;
	}
	
	public abstract void passageTour();
}