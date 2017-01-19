package model.pouvoir.pouvoirCarte;

import java.util.Iterator;
import java.util.List;

import model.cards.Card;
import model.cards.OriginCards.SpiritGuide;
import model.enumType.EnumCosmogonie;
import model.cards.OriginCards.Believer;
import model.exception.NoCroyantLinkedAtConversionException;
import model.exception.PAInsuffisantException;
import model.game.GameManager;
import model.player.Human;
import model.player.Player;
import model.pouvoir.Pouvoir;
/**Pouvoir du guide spirituel pour convertir un croyant*/
public class ConversionCroyant extends Pouvoir {

	/**Constructeur*/
	public ConversionCroyant() {
		super("Permet de convertir des croyants situes sur la table");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws PAInsuffisantException, NoCroyantLinkedAtConversionException{
		int PAaUtiliser = 1;
		EnumCosmogonie origine;
		if(((SpiritGuide) carte).getOrigine() == EnumCosmogonie.NEANT){
			if (joueur.getDicoPA().get(EnumCosmogonie.NEANT)>= 1 ||joueur.getDicoPA().get(EnumCosmogonie.NUIT)>= 2 ||joueur.getDicoPA().get(EnumCosmogonie.JOUR)>= 2 ){
				origine = joueur.pickOrigine((SpiritGuide) carte);
				if (origine != EnumCosmogonie.NEANT)
					PAaUtiliser = 2;
			}else
				throw new PAInsuffisantException("le joueur n'a pas assez de PA pour utiliser cette carte");
		}else
			origine = ((SpiritGuide) carte).getOrigine();


		List<Believer> croyants = joueur.pickCroyant((SpiritGuide) carte);
		if (croyants.size() == 0)
			throw new NoCroyantLinkedAtConversionException("Aucun croyant n'ont ete lie au guide");

		joueur.decrementerPointAction(origine, PAaUtiliser);
		Believer c;
		Iterator<Believer> itCroyant = croyants.iterator();
		while(itCroyant.hasNext()){
			c = itCroyant.next();
			GameManager.getInstanceUniqueManager().retirerCroyant(c);
			((SpiritGuide) carte).convertirCroyant(c);
		}
		joueur.getScore();
		joueur.retirerCarte(carte);
		joueur.rattacherGuide(carte);

		if (joueur instanceof Human)
			((Human)joueur).notifyChangementCarteRattachees();
	}
}
