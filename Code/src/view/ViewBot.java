package view;

import model.player.Bot;

public abstract class ViewBot {
	Bot bot;
	
	//JoueurController controller;

	/**public ViewBot(JoueurController controller, Human j){
		this.controller = controller;
		this.joueur = j;
	}*/
	
	public ViewBot(Bot b){
		bot = b;
	}
	
	public abstract void passageTour();
}
