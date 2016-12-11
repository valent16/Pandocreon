package capacites;

import java.util.Iterator;
import java.util.Scanner;

import model.EnumType.Cosmogonie;
import model.exception.PAInsuffisantException;
import model.game.GameManager;
import model.player.Player;

public class CapaciteGorpa extends CapaciteSpeciale {

	// Capacite de la divinite Gorpa : Peut recuperer les points d'action d'une autre divinite en plus des siens. L'autre divinite ne reçoit aucun point d'action ce tour-ci

	public CapaciteGorpa() {}

	@Override
	public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {	
		System.out.println("À quel joueur voulez-vous absorber ces points d'actions ?");
		Iterator<Player> it = gameManager.getPlayers().iterator();
		while(it.hasNext()) {
			System.out.println((gameManager.getPlayers().indexOf(it.next())+" "+ player.getNom()));
		}
		Scanner sc = new Scanner(System.in);
		int choixJoueur = sc.nextInt();

		while(choixJoueur > gameManager.getCroyants().size() || choixJoueur < 1){
			System.out.println("!!!!!Le nombre non validé.Veuillez reprendre un autre!");
			choixJoueur = sc.nextInt();
		}
		Player playerTarget = gameManager.getPlayers().get(choixJoueur);

		///boucle pour abosrber les points
		for(Cosmogonie cosmogonie : Cosmogonie.values()){
			int pointActions = playerTarget.getDicoPA().get(cosmogonie);
			player.incrementerPointAction(cosmogonie, pointActions);
			try {
				playerTarget.decrementerPointAction(cosmogonie, pointActions);
			} catch (PAInsuffisantException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Vous n'avez pas le droit de recevoir des points d'action");
	}
}   
