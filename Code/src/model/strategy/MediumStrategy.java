package model.strategy;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.player.Bot;
import model.player.Player;

/**Stratégie de jeu moyenne pour les bots*/
public class MediumStrategy implements Strategy {

	//il regarde son score s'il est devant les autres joueurs il lance des sacrifices et le spouvoir de sa divinite:
	// dans l'ordre sa fiat:
	//- test sil apocalypse si il a la il l'active
	//-sinon test si deus EX si il a il l'active 
	//- test croyant si il a, il la sacrifie
	//- si divinite il la sacrifie 
	//- si aucune carte il pioche
	//par contre si il est derriere
	//reprendre la startegie easy a savoir poser des criyants et les recuperers quand on peut

	/**garde le bot qui joue en memoire pour recuperer ses donnees (cartes, score etc..)*/
	private Bot bot;

	/**
	 * Methode pour recuperer le bot qui joue
	 * @param bot le bot qui joue
	 */
	public void setBot(Bot bot) {
		this.bot = bot;
	}
	
	public void jouer(Bot b){
		this.setBot(b); //Passage des données du bot
		System.out.println(bot.getNom());
		//TODO
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

	@Override
	//permet d'economiser ses points dans notre cas il pioche si il a moins de 7 cartes sinon il passe son tour
	public void economy() {
		//TODO
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
}
