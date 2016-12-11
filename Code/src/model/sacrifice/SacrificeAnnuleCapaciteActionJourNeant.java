package model.sacrifice;

import java.util.Iterator;

import java.util.LinkedList;
import java.util.Scanner;

import model.EnumType.EnumCosmogonie;
import model.cards.ActionCard;
import model.cards.OriginCards.CarteDogmatique;
import model.cards.OriginCards.SpiritGuide;
import model.game.GameManager;
import model.player.Human;
import model.player.Player;

/**defausse une carte action d'origine Jour ou Neant*/
public class SacrificeAnnuleCapaciteActionJourNeant extends Sacrifice {

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Scanner sc = new Scanner(System.in);
		LinkedList<Player> possiblePlayers = new LinkedList<Player>();//joueur dont on peut defausser la carte
		Iterator<Player> it = gameManager.getPlayers().iterator();
		while (it.hasNext()) { //pour chaque joueur
			Player p = it.next();
			if (!p.equals(player)) {
				Iterator<ActionCard> cards = p.getHand().iterator();
				while (cards.hasNext()) { //pour chaque carte
					CarteDogmatique dogmaticCard = (CarteDogmatique) cards.next();
					if (dogmaticCard instanceof SpiritGuide//test si on a un guide
							&& dogmaticCard.getOrigine().equals(EnumCosmogonie.NUIT) //test si il est d'origine nuit	
							|| dogmaticCard.getOrigine().equals(EnumCosmogonie.NEANT)){ //ou d'origine neant
						possiblePlayers.add(gameManager.getPlayers().indexOf(p) + 1, p);
					}
				}
			}
		}
		if (possiblePlayers.isEmpty()) { //si aucun joueur n'a de guide Neant ou nuit on stop 
			System.out.println("Aucun autre joueur ne possède une Guide Spirituel d'Origine Nuit ou Neant.");
		}else { //sinon
			int choixJoueur;
			if (player instanceof Human) {
				System.out.println("À quel joueur voulez-vous detruire une GuideSpirituel? " + possiblePlayers);
				choixJoueur = sc.nextInt();
				while (!possiblePlayers.contains(choixJoueur)) {
					System.out.println("Le choix impossible. Veuillez choisir un autre!");
					choixJoueur = sc.nextInt();
				}
			} /*else {
				do {
				//TODO choixJoueur = (Bot) player.getStrategie().choixJoueur((Bot) joueur, partie); //TODO la methode choixJoueur
				} while (!possiblePlayers.contains(choixJoueur));
			}

			Player playerTarget = gameManager.getPlayers().get(choixJoueur-1);
			Iterator<ActionCard> itc = playerTarget.getHand().iterator();
			while (itc.hasNext()) {
				SpiritGuide spiritGuide = (SpiritGuide) itc.next();
				if(spiritGuide instanceof SpiritGuide && spiritGuide.getOrigine().equals(EnumCosmogonie.NUIT) || spiritGuide.getOrigine().equals(EnumCosmogonie.NEANT)) {
					gameManager.defausserCarte(spiritGuide);
					playerTarget.retirerCarte(spiritGuide);

					Iterator<Believer> believers = spiritGuide.getCroyantsConvertis().iterator(); // TODO recupere les croyants que possede le guide
					while (believers.hasNext()) {
						Believer believer = believers.next();
						playerTarget.retirerCarte(believer);
						gameManager.deposerCroyant(believer);
						playerTarget.getScore();
						spiritGuide.supprimerCroyant(believer);
					}
					break;
				}*/
			sc.close();
		}
		//System.out.println(player.getNom() + " a detruit une Guide Spirituel d'origine Nuit ou Neant de " + playerTarget.getNom());
		
	}
}
