package model.sacrifice;

import java.util.Iterator;

import model.EnumType.EnumCosmogonie;
import model.EnumType.EnumDogme;
import model.cards.OriginCards.Believer;
import model.game.GameManager;
import model.player.Player;

/**Tous les croyants d'origine NUIT ou NEANT et d'ayant le dogme Nature, actuellement sur la table sont defausses. Les capacites speciales ne sont pas jouees*/
public class SacrificeDefausserCroyantsNuitNeantNature extends Sacrifice{

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Believer> it = gameManager.getCroyants().iterator();
		while(it.hasNext()){
			Believer believer = it.next();
			if (believer.getOrigine().equals(EnumCosmogonie.NUIT)
					|| believer.getOrigine().equals(EnumCosmogonie.NEANT) 
					&& believer.getDogmes().contains(EnumDogme.NATURE)){
				gameManager.retirerCroyant(believer); //on le retire de la table
				gameManager.defausserCarte(believer); //on defausse ces croyants
			}
		}
		System.out.println("Vous avez defausser les cartes Croyants d'origine Nuit ou Néant et ayant le Dogme Nature ");
	}
	
}
