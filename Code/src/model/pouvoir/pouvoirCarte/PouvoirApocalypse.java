package model.pouvoir.pouvoirCarte;

import model.cards.Card;
import model.cards.OriginCards.ApocalypseWithOrigin;
import model.enumType.EnumCosmogonie;
import model.exception.PAInsuffisantException;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Classe qui possede le pouvoir de declencher une apocalypse*/
public class PouvoirApocalypse extends Pouvoir {
	
	/**Constructeur*/
	public PouvoirApocalypse() {
		super("Realise un grand bouleversement dans le jeu");
	}
	
	@Override
	public void onAction(Card carte, Player joueur) throws PAInsuffisantException {
		if (carte instanceof ApocalypseWithOrigin){
			if (((ApocalypseWithOrigin) carte).getOrigine() == EnumCosmogonie.NEANT){
				if (joueur.getDicoPA().get(EnumCosmogonie.NEANT) >= 1 
						|| joueur.getDicoPA().get(EnumCosmogonie.NUIT) >= 2 
						|| joueur.getDicoPA().get(EnumCosmogonie.JOUR) >= 2 )
					joueur.pickOrigine((ApocalypseWithOrigin) carte);
				else
					throw new PAInsuffisantException("le joueur n'a pas assez de PA pour utiliser cette carte");
			}else
				joueur.decrementerPointAction(((ApocalypseWithOrigin) carte).getOrigine(), 1);
		}

		if (GameManager.getInstanceUniqueManager().getNbJoueur()>=4)
			GameManager.getInstanceUniqueManager().eliminationJoueurFaible();
		else
			GameManager.getInstanceUniqueManager().determinerVainqueur();
	}
}
