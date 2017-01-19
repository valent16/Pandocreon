package model.pouvoir.sacrificeCarte;

import model.cards.Card;
import model.enumType.EnumCosmogonie;
import model.game.De;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Sacrifice qui relance le de de Cosmogonie. Le tour se finit normalement sous la nouvelle influence*/
public class SacrificeRelanceDeCosmogonie extends Pouvoir{

	/**Constructeur*/
	public SacrificeRelanceDeCosmogonie() {
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		De de = De.getInstanceDe();
		de.lancerDe();
		 
		//ajouter les points actions aux joueur
		switch(de.getFace()){
		case JOUR:
			for(Player p: gameManager.getPlayers()){
				switch(p.getDivinity().getOrigine()){//recupere l'origine de sa divinité
				case JOUR :
					p.incrementerPointAction(EnumCosmogonie.JOUR, 2);
					break;
				case AUBE:
					p.incrementerPointAction(EnumCosmogonie.JOUR, 1);
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
					p.incrementerPointAction(EnumCosmogonie.NUIT, 2);
					break;
				case CREPUSCULE:
					p.incrementerPointAction(EnumCosmogonie.JOUR, 1);
					break;
				default:
					break;
				}
			}
			break;
		case NEANT:
			for(Player p : gameManager.getPlayers()) {
				if ("AUBE".equals(p.getDivinity().getOrigine()) || "NEANT".equals(p.getDivinity().getOrigine()) )
					p.incrementerPointAction(EnumCosmogonie.NEANT, 1);
			}
			break;
		default:
			break;
		}	
	}
}
