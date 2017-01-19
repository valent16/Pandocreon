package model.strategy;

import java.util.List;

import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.enumType.EnumCosmogonie;
import model.player.Bot;
import model.player.Player;
/**Stratégie de jeu facile pour les bots, les choix sont très simples*/
public class EasyStrategy implements Strategy {

	/**garde le bot qui joue en memoire pour recuperer ses sdonnees (cartes, score etc..)*/
	private Bot bot;

	/**
	 * Methode pour recuperer le bot qui joue
	 * @param bot le bot qui joue
	 */
	public void setBot(Bot bot) {
		this.bot = bot;
	}

	//Methode de jeu
	public void jouer(Bot b){
		this.setBot(b); //Passage des données du bot	
		bot.afficherHand();
	}

	@Override
	//depot de croyant
	public void depotCroyant() {
		
	}

	@Override
	//conversion de croyant
	public void convertirCroyants() {
	}

	@Override
	//lancement d'une apocalypse
	public void lancerApocalypse() {
		
	}

	@Override
	/**permet d'economiser ses points dans notre cas il pioche son tour*/
	public void defausser() {
		
	}

	@Override
	public Player pickTarget() {
		return null;
	}

	@Override
	public EnumCosmogonie pickOrigine(ActionCardWithOrigin carte) {
		return null;
	}

	@Override
	public List<Believer> pickCroyant(SpiritGuide guide) {
		return null;
	}
}