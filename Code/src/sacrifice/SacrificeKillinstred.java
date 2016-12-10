package capacites;

import java.util.Iterator;

import model.cards.withoutOriginCards.Apocalypse;
import model.game.GameManager;
import model.player.Player;

public class SacrificeKillinstred extends Sacrifice{

	// Sacrifice de la divinite Killinstred: peut obliger un joueur à poser une carte Apocalypse s'il en possède une

	@Override
	public void effectuerSacrifice(Player player, GameManager gameManager) {
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
		// TODO appeller la methode apocalypse avec apocalypse.<nom_methdode()>
	}
}
