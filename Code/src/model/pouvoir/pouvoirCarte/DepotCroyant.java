package model.pouvoir.pouvoirCarte;

import model.cards.Card;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;

public class DepotCroyant extends Pouvoir{

	public DepotCroyant() {
		super("Permet de d�poser un coyant sur la table de jeu");
	}
	
	@Override
	public void onAction(Card carte, Player joueur) {
		GameManager.getInstanceUniqueManager().deposerCroyant(carte);
		joueur.ajouterCroyantPendantTour(carte);
	}
}
