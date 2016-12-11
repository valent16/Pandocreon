package model.sacrifice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import model.EnumType.EnumDogme;
import model.cards.ActionCard;
import model.cards.OriginCards.CarteDogmatique;
import model.cards.OriginCards.SpiritGuide;
import model.game.GameManager;
import model.player.Player;

/**Sacrifice de la divinite Shingva : Peut imposer le sacrifice d'un guide spirituel ayant le dogme Symboles ou Nature*/
public class SacrificeShingva extends Sacrifice {

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Scanner sc = new Scanner(System.in);
		int choixJoueur;
		LinkedList<SpiritGuide> spiritGuides = new LinkedList<SpiritGuide>();
		Iterator<Player> it = gameManager.getPlayers().iterator();
		while(it.hasNext()) { //on parcourt les joueurs
			Player p = it.next();
			Iterator<ActionCard> itc = p.getHand().iterator();

			while(itc.hasNext()){//pour chaque carte du joueur
				CarteDogmatique dogmaticCard = (CarteDogmatique) itc.next();
				if (dogmaticCard instanceof SpiritGuide){ //on test si lacarte est un guide 

					if(dogmaticCard.containsDogme(EnumDogme.SYMBOLE) || dogmaticCard.containsDogme(EnumDogme.NATURE)){//on teste si elle possede un dogme nature ou symbole
						System.out.println(dogmaticCard); //on affiche la carte
					}
				}
			}
		}

		System.out.println("Quelle Guide Spirituel voulez-vous sacrifier? tapez 0 si vous voulez cancel votre sacrifice");
		choixJoueur = sc.nextInt();
		
		while (choixJoueur != 0) {
			//if(!player.aDroitSacrifierGuide()) { //TODO on doit tester si il a le droit de sacrifier le guide
				System.out.println("!!!!!Un autre joueur vous a empêché de sacrifier une Guide Spirituel ! votre sacrifice est annulé");
				choixJoueur = 0;
			//}
			//else {
				SpiritGuide spiritGuide = spiritGuides.get(choixJoueur);
				//spiritGuide.getSacrifice().effectuerSacrifice(player, gameManager); //TODO sacrifier spiritGuide
				gameManager.defausserCarte(spiritGuide);
				System.out.println("Vous avez sacrifié un Guide Spirituel ayant le dogme Symbole ou Nature");
			//}
		}
		sc.close();
	}

}
