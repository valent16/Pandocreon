package model.sacrifice;

import java.util.Iterator;
import java.util.LinkedList;

import model.EnumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;

public class SacrificeEmpecheSacrificeCroyantNM extends Sacrifice {
	// Empeche une divinité possédant le dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Player> it = gameManager.getPlayers().iterator();

		LinkedList<Player> possiblePlayers = new LinkedList<Player>();//liste des jouuers possibles
		while (it.hasNext()) {
			Player p = it.next();
			if (!p.equals(player) && p.getDivinity().getDogmes().contains(EnumDogme.NATURE)	|| p.getDivinity().getDogmes().contains(EnumDogme.MYSTIQUE)){
				possiblePlayers.add(p);
				//p.setDroitSacrifierCroyant(false);//TODO setDroitSacrifierCroyant
			}
		}
		if (possiblePlayers.isEmpty()) {
			System.out.println(player.getNom() + " a fait une mauvaise action. Aucune Divinité ne possède le Dogme Humain ou Mystique.");
		} else {
			System.out.println(player.getNom() + " a empêché les Divinités possédant le Dogme Humain ou Mystique de sacrifier un Croyant.");
		}

		for (Player p : gameManager.getPlayers()) {
			if (p.getDivinity().getDogmes().contains(EnumDogme.NATURE) || p.getDivinity().getDogmes().contains(EnumDogme.MYSTIQUE)) {
				p.setDroitSacrifierCroyant(false); //TODO Trouver une solution a ça 
			}
		}
	}
}
