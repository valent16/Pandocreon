package model.sacrifice;

import java.util.Iterator;

import java.util.Scanner;

import model.game.GameManager;
import model.player.Human;
import model.player.Player;

/**Permet de sacrifier un croyant appartenant a une divinite adverse*/
public class SacrificeBeneficierCapaciteCroyantsAutreDivinite extends Sacrifice{

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		int choixJoueur = 0;
		//int choixCroyant = 0;
		Scanner sc = new Scanner(System.in);
		if (player instanceof Human) { //si le joueur est un humain 
			System.out.println("A quel joueur appartient le croyant que vous avez envie de sacrifier ?");
			Iterator<Player> it = gameManager.getPlayers().iterator();
			while(it.hasNext()){
				Player p = it.next();
				System.out.println( gameManager.getPlayers().indexOf(p) + " "+ p );
			}

			choixJoueur = sc.nextInt();
			while (choixJoueur > gameManager.getPlayers().size() || choixJoueur < 1) {
				System.out.println("Le nombre non validé!");
				choixJoueur = sc.nextInt();
			}
			if(gameManager.getCroyants().isEmpty()) { //si il n'y aucun croyant sur la table
				System.out.println("La table est vide");
				sc.close();
				return;
			}
		}else{
			do {
				//TODO choixJoueur = ((Bot) player).getStrategy().choixJoueur((Bot) player, gameManager);
			} while (gameManager.getCroyants().isEmpty());
		}

		/*Player playerTarget = gameManager.getPlayers().get(choixJoueur);
		if (player instanceof Human) {
			System.out.println("Les Croyants guidée de " + playerTarget.getNom() + ": ");

			Iterator<Believer> it = gameManager.getCroyants().iterator();
			while (it.hasNext()) {
				ActionCard card = it.next();
				if (card instanceof Believer) {
					System.out.println("Carte " + card);
				}
			}
			choixCroyant = sc.nextInt(); //le nombre saisi n'est pas en intervalle de champ
			while (choixCroyant > gameManager.getPlayers().size() || choixCroyant < 1) {
				System.out.println("!!!!!Le nombre non validé.Veuillez rechoisir");
				choixCroyant = sc.nextInt();
			}
			//la carte choisie n'est pas guide spirituel
			while (!(gameManager.getCroyants().get(choixCroyant) instanceof Believer)) {
				System.out.println("!!!!Veuillez choisir une carte validée!");
				choixCroyant = sc.nextInt();
			}
		} else {
			//TODO choixCroyant = ((Bot) player).getStrategie().choixCroyant((Bot) player);

		}*/

		//TODO Believer croyantChoisie = (Believer) gameManager.getCroyants().get(choixCroyant);

		//croyantChoisie.sacrifier(player, gameManager); //TODO Appeller la methode sacrifice pour sacrifier le croyant
	
		//SpiritGuide carteGuide = (SpiritGuide) croyantChoisie.get; //TODO methode pour recuperer le guide d'un croyant


		/*if (carteGuide..isEmpty()) { //si la carte guide n'a plus de carte Croyant, il est défaussée
			gameManager.getDefausse().defausserCarte(carteGuide);
			player.getGuides().remove(carteGuide);
		}
		System.out.println(player.getNom()+" a bénéficié la capacité d'une Croyant de "+playerTarget.getNom());*/
		sc.close();
	}
}
