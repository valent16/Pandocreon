package model.strategy;

import model.player.Bot;

/**Interface de startégie*/
public interface Strategy {	
	
	public void jouer(Bot bot);
	public void choisirCarte();
}
