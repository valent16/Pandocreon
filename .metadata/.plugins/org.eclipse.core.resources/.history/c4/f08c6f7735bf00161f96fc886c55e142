package capacites;

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
			for(Player p: gameManager.getPlayers()){
				switch(p.getDivinity().getOrigine()){//recupere l'origine de sa divinité
				case JOUR :
					p.incrementerPointAction(Cosmogonie.JOUR, 2);
					break;
				case AUBE:
					p.incrementerPointAction(Cosmogonie.JOUR, 1);
					break;
				default:
					break;
				}
			}
			break;
		case NUIT:
			for(Player p: gameManager.getPlayers()){
				switch(p.getDivinity().getOrigine()){
				case NUIT:	
					p.incrementerPointAction(Cosmogonie.NUIT, 2);
					break;
				case CREPUSCULE:
					p.incrementerPointAction(Cosmogonie.JOUR, 1);
					break;
				default:
					break;
				}
			}
			break;
		case NEANT:
			for(Player p : gameManager.getPlayers()) {
				if ("AUBE".equals(p.getDivinity().getOrigine()) || "NEANT".equals(p.getDivinity().getOrigine()) )
					p.incrementerPointAction(Cosmogonie.NEANT, 1);
			}
			break;
		default:
			break;
		}
	}

}
