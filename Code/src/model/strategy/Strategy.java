package model.strategy;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.player.Bot;
import model.player.Player;

/**Interface de startégie*/
public interface Strategy {	
	
	/**Methode permettant de recuperer les données du bot*/
	public void setBot(Bot bot);
	
	public void jouer(Bot bot);
	
	public void depotCroyant();
	
	public void convertirCroyants();
	
	public void lancerApocalypse();
	
	public void defausser();
	
	public Player pickTarget();
	
	public EnumCosmogonie pickOrigine(ActionCardWithOrigin carte);
	
	public List<Believer> pickCroyant(SpiritGuide carte);

	
}
