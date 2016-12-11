package model.sacrifice;

import java.util.Iterator;

import model.EnumType.EnumDogme;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.CarteDogmatique;
import model.game.GameManager;
import model.player.Player;

/** Sacrifice qui defausse tous les croyants ayant le dogme Mystique actuellement au centre de la table*/
public class SacrificeDefausserCroyantsMystique extends Sacrifice{

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Believer> it = gameManager.getCroyants().iterator();
		while (it.hasNext()) {
			CarteDogmatique dogmaticCard = it.next();
			if (dogmaticCard.containsDogme(EnumDogme.MYSTIQUE)) { //on test si leur dogme est mystique
				gameManager.defausserCarte(dogmaticCard); //defausse la carte
			}
		}
		System.out.println(player.getNom()+" a défaussé tous les Croyants ayant le Dogme Mystique au centre de la table.");
	}
}
