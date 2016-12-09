package capacites;

import java.security.GeneralSecurityException;

import model.EnumType.Cosmogonie;
import model.game.De;
import model.game.GameManager;
import model.player.Player;

public class RelanceDeCosmogonie extends CapaciteSpeciale {

	// Relancez le de de Cosmogonie. Le tour se finit normalement sous la nouvelle influence

	public RelanceDeCosmogonie() {   }

	@Override
	public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
		De de = De.getInstanceDe();
		de.lancerDe();
		 
		//ajouter les points actions aux joueur
		switch(de.getFace()){
		case JOUR:
			for(Player j: gameManager.getPlayers()){
				switch(j.getHand().getCartesDivinites().getOrigine()){//recupere l'origine de sa divinité
				case JOUR :
					player.incrementerPointAction(Cosmogonie.JOUR, 2);
					break;
				case AUBE:
					player.incrementerPointAction(Cosmogonie.JOUR, 1);
					break;
				default:
					break;
				}
			}
			break;
		case NUIT:
			for(Player j: gameManager.getPlayers()){
				switch(j.getHand().getCartesDivinites().getOrigine()){
				case NUIT:	
					player.incrementerPointAction(Cosmogonie.NUIT, 2);
					break;
				case CREPUSCULE:
					player.incrementerPointAction(Cosmogonie.JOUR, 1);
					break;
				default:
					break;
				}
			}
			break;
		case NEANT:
			for(Player j: gameManager.getPlayers()) {
				if ("AUBE".equals(j.getHand().getCartesDivinites().getOrigine()) || "NEANT".equals(j.getHand().getCartesDivinites().getOrigine())) {
					player.incrementerPointAction(Cosmogonie.NEANT, 1);
				}
			}
			break;
		default:
			break;
		}
	}

}
