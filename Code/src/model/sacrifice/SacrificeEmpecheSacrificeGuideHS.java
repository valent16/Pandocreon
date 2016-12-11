package model.sacrifice;

import java.util.Iterator;
import java.util.LinkedList;

import model.EnumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;

/**Sacrifice qui empeche une divinité possédant le dogme Humain ou Symboles de sacrifier un de ses guides spirituels durant ce tour*/
public class SacrificeEmpecheSacrificeGuideHS extends Sacrifice {

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Player> it = gameManager.getPlayers().iterator();
		LinkedList<Player> possiblePlayer = new LinkedList<Player>();
		while (it.hasNext()) {
			Player p = it.next();
			if (! p.equals(player) && p.getDivinity().getDogmes().contains(EnumDogme.HUMAIN) || p.getDivinity().getDogmes().contains(EnumDogme.SYMBOLE)){
				possiblePlayer.add(p);
				//p.setDroitSacrifierGuide(false); //TODO a faire
			}
		}
		if (possiblePlayer.isEmpty()) {
			System.out.println(player.getNom() + " a fait une mauvaise action. Aucune Divinité ne possède le Dogme Chaos ou Mystique.");
		} else {
			System.out.println(player.getNom() + " a empêché les Divinités possédant le Dogme Chaos ou Mystique de sacrifier une Guide durant ce tour.");
		}
	}
}
