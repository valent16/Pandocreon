package model.pouvoir.sacrificeCarte;

import java.util.Iterator;

import model.cards.ActionCard;
import model.cards.Card;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.enumType.EnumCosmogonie;
import model.enumType.EnumDogme;
import model.game.GameManager;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Sacrifice qui echange un de vos guides spirituels avec un d'une autre divinite a condition que la divinite possede le dogme NUIT, Mystique, Chaos. 
 * Vous choisissez les deux guides spirituels en question. 
 * Les croyants restent attaches aux memes cartes
 */
public class SacrificeEchangerGuides extends Pouvoir{

	/**Constructeur*/
	public SacrificeEchangerGuides() {
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		GameManager gameManager = GameManager.getInstanceUniqueManager();
		Iterator<Player> it = gameManager.getPlayers().iterator();
		while(it.hasNext()){
			Player p = it.next();
			if (p.getDivinity().getOrigine().equals(EnumCosmogonie.NUIT) 
					|| (p.getDivinity().getDogmes().contains(EnumDogme.MYSTIQUE))
					&& p.getDivinity().getDogmes().contains(EnumDogme.CHAOS)) {
				Iterator<ActionCard> itc = p.getHand().iterator();
				while (itc.hasNext()) { //on parcourt les cartes
					if (itc.next() instanceof SpiritGuide) {
						SpiritGuide spiritGuide = (SpiritGuide) itc.next();
						p.retirerCarte(spiritGuide);
						joueur.ajouterMain(spiritGuide);

						Iterator<Believer> believers = spiritGuide.getCroyantsConvertis().iterator();
						while(believers.hasNext()){ //defausser tous les cartes Croyants attachées
							Believer believer = believers.next();
							gameManager.defausserCarte((ActionCard) believer);
						}
						break;
					}
				}
			}
			System.out.println(joueur.getNom()+" a joué capacité de carte Guide Spirituel Exorciste.Les divinité d'origine Nuit ou ayant les dogmes Mystique et Chaos reprend dans sa main l'un de ses guides spirituels");
		}	
	}
}
