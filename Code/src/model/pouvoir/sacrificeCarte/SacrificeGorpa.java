package model.pouvoir.sacrificeCarte;

import java.util.Iterator;

import java.util.Scanner;

import model.cards.Card;
import model.enumType.EnumCosmogonie;
import model.exception.PAInsuffisantException;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;
/**Sacrifice de la divinite Gorpa : Recupere les points d'action d'une autre divinite en plus des siens. 
 * L'autre divinite ne reçoit aucun point d'action ce tour-ci
 */
public class SacrificeGorpa extends Pouvoir{
	
	/**Constructeur*/
	public SacrificeGorpa() {
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		System.out.println("À quel joueur voulez-vous absorber ces points d'actions ?");
		Iterator<Player> it = gameManager.getPlayers().iterator();
		while(it.hasNext()) {
			System.out.println((gameManager.getPlayers().indexOf(it.next())+" "+ joueur.getNom()));
		}
		Scanner sc = new Scanner(System.in);
		int choixJoueur = sc.nextInt();

		while(choixJoueur > gameManager.getCroyants().size() || choixJoueur < 1){
			System.out.println("!!!!!Le nombre non validé.Veuillez reprendre un autre!");
			choixJoueur = sc.nextInt();
		}
		Player playerTarget = gameManager.getPlayers().get(choixJoueur);

		///boucle pour abosrber les points
		for(EnumCosmogonie cosmogonie : EnumCosmogonie.values()){
			int pointActions = playerTarget.getDicoPA().get(cosmogonie);
			joueur.incrementerPointAction(cosmogonie, pointActions);
			try {
				playerTarget.decrementerPointAction(cosmogonie, pointActions);
			} catch (PAInsuffisantException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Vous n'avez pas le droit de recevoir des points d'action");
		sc.close();	
	}
}   
