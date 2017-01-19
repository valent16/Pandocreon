package model.pouvoir.sacrificeCarte;

import java.util.Iterator;
import model.cards.Card;
import model.cards.withoutOriginCards.Apocalypse;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Sacrifice de la divinite Killinstred: peut obliger un joueur à poser une carte Apocalypse s'il en possède une*/
public class SacrificeKillinstred extends Pouvoir{

	/**Constructeur*/
	public SacrificeKillinstred() {
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Apocalypse apocalypse = null;
		Iterator<Player> it = gameManager.getPlayers().iterator();
		while(it.hasNext()) {// on parcout les joueurs
			Player p = it.next();
			for (int i=0; i< p.getHand().size(); i++) { //on parcourt leurs mains
				if (p.getHand().get(i) instanceof Apocalypse) { //on check si ils ont une apocalypse et on break;s
					apocalypse = (Apocalypse) (p.getHand().get(i));
					break;
				}
			}
			break;
		}
		try {
			apocalypse.utiliserPouvoir("declencher apocalypse", joueur);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
