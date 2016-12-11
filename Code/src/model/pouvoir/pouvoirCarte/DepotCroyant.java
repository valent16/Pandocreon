package model.pouvoir.pouvoirCarte;
import model.cards.OriginCards.Believer;
import model.exception.PAInsuffisantException;
import model.EnumType.EnumCosmogonie;
import model.cards.Card;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;

public class DepotCroyant extends Pouvoir{
	public DepotCroyant() {
		super("Permet de d�poser un coyant sur la table de jeu");
	}
	
	@Override
	public void onAction(Card carte, Player joueur) throws Exception{
		if(((Believer) carte).getOrigine() == EnumCosmogonie.NEANT){
			if (joueur.getDicoPA().get(EnumCosmogonie.NEANT)>= 1 ||joueur.getDicoPA().get(EnumCosmogonie.NUIT)>= 2 ||joueur.getDicoPA().get(EnumCosmogonie.JOUR)>= 2 ){
				joueur.pickOrigine((Believer) carte);
			}else{
				throw new PAInsuffisantException("le joueur n'a pas assez de PA pour utiliser cette carte");
			}
		}else{
			joueur.decrementerPointAction(((Believer) carte).getOrigine(), 1);
		}
		GameManager.getInstanceUniqueManager().deposerCroyant(carte);
		joueur.ajouterCroyantPendantTour(carte);
	}
}
