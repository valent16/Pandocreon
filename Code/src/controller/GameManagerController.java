package controller;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.exception.ObservateurNotLinkedException;
import model.game.IObservableGameManager;
import model.player.Player;
import view.IObservateurGameManager;
import view.ObservateurJoueurReel;
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