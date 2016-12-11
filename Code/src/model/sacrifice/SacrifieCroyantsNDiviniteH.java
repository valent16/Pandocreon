package model.sacrifice;

import java.util.Iterator;

import model.EnumType.EnumCosmogonie;
import model.EnumType.EnumDogme;
import model.cards.ActionCard;
import model.cards.OriginCards.Believer;
import model.game.GameManager;
import model.player.Player;

/**Sacrifie tous les croyants d'origine Neant d'une divinite ayant le dogme Humain*/
public class SacrifieCroyantsNDiviniteH extends Sacrifice{

	@Override
	public void effectuerSacrifice(Player player) {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Player> it = gameManager.getPlayers().iterator();
		while(it.hasNext()){
			Player p = it.next();
			if(p.equals(player) && p.getDivinity().getDogmes().contains(EnumDogme.HUMAIN)){
				Iterator<ActionCard> believers = p.getHand().iterator();
				while(believers.hasNext()){
					Believer believer = (Believer) believers.next();
					if(believer.getOrigine().equals(EnumCosmogonie.NEANT) && believer instanceof Believer){
						//TODO believer.sacrifier sacrifier believer
						gameManager.retirerCroyant(believer);
						gameManager.defausserCarte(believer);
					}
				}
			}
		}
		System.out.println(player.getNom()+" a sacrifié tous les Croyants d'Origine Néant d'une Divinité ayant le Dogme Humain");
	}
}
	
