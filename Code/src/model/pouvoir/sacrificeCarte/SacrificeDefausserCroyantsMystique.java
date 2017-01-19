package model.pouvoir.sacrificeCarte;

import java.util.Iterator;

import model.cards.Card;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.CarteDogmatique;
import model.enumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;

/** Sacrifice qui defausse tous les croyants ayant le dogme Mystique actuellement au centre de la table*/
public class SacrificeDefausserCroyantsMystique extends Pouvoir{

	/**Constructeur*/
	public SacrificeDefausserCroyantsMystique() {
		super("sacrifice");	
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Believer> it = gameManager.getCroyants().iterator();
		while (it.hasNext()) {
			CarteDogmatique dogmaticCard = it.next();
			if (dogmaticCard.containsDogme(EnumDogme.MYSTIQUE)) //on test si leur dogme est mystique
				gameManager.defausserCarte(dogmaticCard); //defausse la carte
		}
		System.out.println(joueur.getNom()+" a défaussé tous les Croyants ayant le Dogme Mystique au centre de la table.");
	}
}
