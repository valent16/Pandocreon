package model.pouvoir.pouvoirCarte;

import java.util.Iterator;
import java.util.List;
import model.EnumType.EnumCosmogonie;
import model.cards.Card;
import model.cards.OriginCards.SpiritGuide;
import model.cards.OriginCards.Believer;
import model.exception.NoCroyantLinkedAtConversion;
import model.exception.PAInsuffisantException;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;

public class ConversionCroyant extends Pouvoir {

	public ConversionCroyant() {
		super("Permet de convertir des croyants situes sur la table");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		int PAaUtiliser = 1;
		EnumCosmogonie origine;
		if(((SpiritGuide) carte).getOrigine() == EnumCosmogonie.NEANT){
			if (joueur.getDicoPA().get(EnumCosmogonie.NEANT)>= 1 ||joueur.getDicoPA().get(EnumCosmogonie.NUIT)>= 2 ||joueur.getDicoPA().get(EnumCosmogonie.JOUR)>= 2 ){
				origine = joueur.pickOrigine((SpiritGuide) carte);
				if (origine != EnumCosmogonie.NEANT){
					PAaUtiliser = 2;
				}
			}else{
				throw new PAInsuffisantException("le joueur n'a pas assez de PA pour utiliser cette carte");
			}
		}else{

			origine = ((SpiritGuide) carte).getOrigine();
			
			List<Believer> croyants = joueur.pickCroyant((SpiritGuide) carte);
			if (croyants.size() == 0){
				throw new NoCroyantLinkedAtConversion("Aucun croyant n'ont ete lie au guide");
			}
			joueur.decrementerPointAction(((SpiritGuide) carte).getOrigine(), 1);
		}
		List<Believer> croyants = joueur.pickCroyant((SpiritGuide) carte);
		if (croyants.size() == 0){
			throw new NoCroyantLinkedAtConversion("Aucun croyant n'ont ete lie au guide");
		}
		joueur.decrementerPointAction(origine, PAaUtiliser);

		Iterator<Believer> itCroyant = croyants.iterator();
		while(itCroyant.hasNext()){
			((SpiritGuide) carte).convertirCroyant(itCroyant.next());
		}

		GameManager.getInstanceUniqueManager().deposerCroyant(carte);
		joueur.ajouterCroyant(carte);
		joueur.retirerCarte(carte);
		joueur.rattacherGuide(carte);
	}
}
