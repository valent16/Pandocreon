package model.sacrifice;

import java.util.Iterator;
import java.util.Scanner;

import model.cards.ActionCard;
import model.cards.OriginCards.SpiritGuide;
import model.game.GameManager;
import model.player.Human;
import model.player.Player;
/** Sacrificie qui choisi un des guides spirituels d'un autre joueur et l'un des votres. Lancez le dé de cosmogonie.
 * Si le de tombe sur jour ,le guide adverse est sacrifié, 
 * Sinon si le de tomber sur Nuit le votre est sacrifie, 
 * Sinon rien ne se passe*/
public class SacrificeDeCosmogonieGuide extends Sacrifice {

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();

		//test si le jouuer possede deja un guide
		Iterator<ActionCard> itc = player.getHand().iterator();
		boolean possedeGuide = false;
		while(itc.hasNext()){
			ActionCard card = itc.next();
			if(card instanceof SpiritGuide ){ 
				possedeGuide = true;
			}
		}

		int choixGuide;
		int choixJoueur;
		//int choixGuideCible;
		//SpiritGuide spiritGuideTarget;
		SpiritGuide spiritGuide = null;
		Scanner sc = new Scanner(System.in);

		if(possedeGuide){//si le joueur a un guide spirituel on continue
			if (player instanceof Human) { //demande au joueur qui a fait le sacrifice quel guide mise t'il
				System.out.println("Quelle carte Guide Spirituel souhaitez-vous parier?");
				Iterator<SpiritGuide> itg = player.getGuides().iterator();
				while (itg.hasNext()) {
					SpiritGuide guide = itg.next();
					System.out.println("Choisissez votre guide: " + player.getGuides().indexOf(guide) + " " + guide);
				}
			}

			choixGuide = sc.nextInt();
			while (choixGuide > player.getGuides().size() || choixGuide < 1) { //on verifie le choix du joueur
				System.out.println("!!!!!Le nombre non validé.Veuillez rechoisir");
				choixGuide = sc.nextInt();
			}

			spiritGuide = player.getGuides().get(choixGuide-1);

			//on choisit le guide d'un autre joueur
			System.out.println("Maintenant a quel joueur souhaitez-vous parier la Guide Spirituel ?");
			Iterator<Player> it = gameManager.getPlayers().iterator();
			while (it.hasNext()) {
				Player p = it.next();
				if (!p.equals(player) && ! p.getGuides().isEmpty()) { //on tests si le joueur a des guides
					System.out.println(p);
				}
			}
			choixJoueur = sc.nextInt();
			while (choixJoueur > gameManager.getNbJoueur() || choixJoueur < 1 //test si le nombre saisi est correcte
					|| gameManager.getPlayers().get(choixJoueur - 1).equals(player)  //test si le joueur n'est pas le meme qui a fait le sacrifice
					|| gameManager.getPlayers().get(choixJoueur - 1).getGuides().isEmpty()) { //test si le joueur a un guide

				System.out.println("!!!!!Le nombre saisi n'est pas correct ou le joueur choisi non valide!Veuillez reprendre au autre");
				choixJoueur = sc.nextInt();
			}

		}else{ // si c'est un bot
			//TODO choixGuide = (Bot) player.getStrategy().choixGuide( (Bot) player); //TODO methode choixGuide prendre le premier guide random
			//SpiritGuide spiritGuideTarget = player.getGuides().get(choixGuide-1);

			/**do{ //choisir un joueur qui a des cartes
				//TODO choixJoueur = (Bot) player.getStrategy().choixJoueur((Bot) player, gameManager); //TODO Pour prendre le joueur le plus faible
			}while (gameManager.getPlayers().get(choixJoueur - 1).getGuides().isEmpty()); //on demande un joueur qui a des guides*/

		}

		/*Player playerTarget = gameManager.getPlayers().get(choixJoueur - 1);
		if (player instanceof Human) {
			System.out.println("Les Guides Spirituels ayant guidé par " + playerTarget.getNom() + ": ");

			Iterator<SpiritGuide> itg = playerTarget.getGuides().iterator();
			while(itg.hasNext()) {
				SpiritGuide guide = itg.next();
				System.out.println("Choisissez le guide ennemi : " + player.getGuides().indexOf(guide) + " " + guide);
			}

			choixGuideCible = sc.nextInt();
			while (choixGuideCible > playerTarget.getGuides().size() || choixGuideCible < 1) {//le nombre saisi n'est pas en intervalle de champ
				System.out.println("!!!!!Le nombre non validé.Veuillez rechoisir");
				choixGuideCible = sc.nextInt();
			}

		}else{
			//TODO choixGuideCible = (Bot) player.getStrategy().choixGuide((Bot) playerTarget);
		}*/

		//TODO spiritGuideTarget = playerTarget.getGuides().get(choixGuideCible-1);

		int random = (int) (Math.random() * 3 + 1);
		if(random == 1){ //c'est jour
			//spiritGuideTarget.sacrifier(); //TODO lancer le sacrifice du Guide
			//TODO System.out.println(playerTarget.getNom() + " a perdu, il sacrifie son guide " + spiritGuideTarget);
		}
		if(random == 2 ){ //c'est nuit
			//TODO spiritGuide.sacrifier(); //TODO lancer le sacrifice du Guide
			//TODO System.out.println(player.getNom() + " a perdu, il sacrifie " + spiritGuide);
		}
		if(random == 3 ){ //c'est neant
			System.out.println("Rien ne se passe.");
		}
		sc.close();
	}
}
