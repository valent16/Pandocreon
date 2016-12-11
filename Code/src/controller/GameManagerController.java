package controller;

import model.player.Player;
import view.IObservateurGameManager;
import view.console.VueGameManager;

public class GameManagerController implements IObservateurGameManager{

	VueGameManager vue;
	
	public GameManagerController(VueGameManager vue){
		this.vue = vue;
	}
	
	@Override
	public void annoncerDefaitJoueur(Player joueur) {
		// TODO Auto-generated method stub
		vue.afficherElimintation(joueur);
	}

	@Override
	public void annoncerVictoireJoueur(Player joueur) {
		// TODO Auto-generated method stub
		vue.afficherVictoireJoueur(joueur);
	}


}
