package model.strategy;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.player.Bot;
import model.player.Player;

/**Stratégie de jeu difficile pour les bots*/
public class HardStrategy implements Strategy {

	/**garde le bot qui joue en memoir pour recuperer ses sdonnees (cartes, score etc..)*/
	private Bot bot;

	/**
	 * Methode pour recuperer le bot qui joue
	 * @param bot le bot qui joue
	 */
	@Override
	public void setBot(Bot bot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jouer(Bot b) {
		this.setBot(b); //Passage des données du bot
		System.out.println(bot.getNom());
		bot.afficherHand();
		System.out.print("ACTION DU BOT: "+ bot.getNom() + " ");	
	}

	@Override
	//permet d'economiser ses points dans notre cas il pioche si il a moins de 7 cartes sinon il passe son tour
	public void economy() {
		//if(getHand < 7)
			//piocher
		//else
			//bot.passerTour(); // TODO passerTour	
	}

	@Override
	public Player pickTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumCosmogonie pickOrigine(ActionCardWithOrigin carte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Believer> pickCroyant(SpiritGuide carte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void depotCroyant() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void convertirCroyants() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lancerApocalypse() {
		// TODO Auto-generated method stub
		
	}
}
