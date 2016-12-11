package model.sacrifice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import model.EnumType.EnumDogme;
import model.cards.ActionCard;
import model.cards.OriginCards.SpiritGuide;
import model.game.GameManager;
import model.player.Player;

/**Sacrifice de la divinite Shingva : Peut imposer le sacrifice d'un guide spirituel ayant le dogme Symboles ou Nature*/
public class SacrificeShingva extends Sacrifice {

	@Override
	public void effectuerSacrifice(Player player, GameManager gameManager) {
		Scanner sc = new Scanner(System.in);
		int choixJoueur;
		LinkedList<SpiritGuide> spiritGuides = new LinkedList<SpiritGuide>();
		Iterator<Player> it = gameManager.getPlayers().iterator();
		while(it.hasNext()) { //on parcourt les joueurs
			Player p = it.next();
			Iterator<ActionCard> itc = p.getHand().iterator();
			
			while(itc.hasNext()){//pour chaque carte du joueur
				ActionCard actionCard = itc.next();
				if (actionCard instanceof GuideSpirituel && p.getHand().get(i).		containsD("Symboles")||Arrays.toString(it.next().getMain().getMainCartesActions().get(i).getDogme()).contains("Nature")) {
					//it.next().getMain().getMainCartesActions().get(i).afficherCarte();
				}//p.getDivinity().getDogmes().contains(EnumDogme.NATURE)


			}
		}
	
		System.out.println("Quelle carte Guide Spirituel voulez-vous sacrifier? (0: Annuler)");
	choixJoueur = input.nextInt();
	while (choixJoueur != 0) {
		if(!player.aDroitSacrifierGuide()) {
			System.out.println("!!!!!Un autre joueur vous a empêché de sacrifier une Guide Spirituel ! Veuillez annuler votre action (0: Annuler)");
			choixJoueur = sc.nextInt();
		}
		else {
			GuideSpirituel carteGuideSpirituel = listeGuides.get(choixJoueur);
			carteGuideSpirituel.getCapaciteSpeciale().effectuerCapaciteSpeciale(joueur, partie);
			partie.getDefausse().defausserCarte(carteGuideSpirituel);
			System.out.println("Vous avez sacrifié un Guide Spirituel ayant le dogme Symbole ou Nature");
		}
	}
}

}
