package model.pouvoir.pouvoirCarte;

import model.EnumType.EnumCosmogonie;
import model.cards.Card;
import model.cards.OriginCards.SpiritGuide;
import model.exception.PAInsuffisantException;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;

public class ConversionCroyant extends Pouvoir {

	public ConversionCroyant() {
		super("Permet de convertir des croyants situ�s sur la table");
		
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		if(((SpiritGuide) carte).getOrigine() == EnumCosmogonie.NEANT){
			if (joueur.getDicoPA().get(EnumCosmogonie.NEANT)>= 1 ||joueur.getDicoPA().get(EnumCosmogonie.NUIT)>= 2 ||joueur.getDicoPA().get(EnumCosmogonie.JOUR)>= 2 ){
				joueur.pickOrigine((SpiritGuide) carte);
			}else{
				throw new PAInsuffisantException("le joueur n'a pas assez de PA pour utiliser cette carte");
			}
		}else{
			joueur.decrementerPointAction(((SpiritGuide) carte).getOrigine(), 1);
		}
		GameManager.getInstanceUniqueManager().deposerCroyant(carte);
		joueur.ajouterCroyantPendantTour(carte);
	}
}
