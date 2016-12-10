package model.strategy;

import model.player.Bot;

/**Interface de startégie*/
public interface Strategy {	
	
	public void setBot(Bot bot);
	public void jouerCarte();
	public void poserCroyant();
	//TODO faire une fonction choisirJoueur voir sur internet
}
