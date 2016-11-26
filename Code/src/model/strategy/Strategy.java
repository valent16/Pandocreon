package model.strategy;

import model.game.GameManager;
import model.player.Bot;

/**Interface de startégie*/
public interface Strategy {	
	
	public void play();
	
	public void DefausserCartes(Bot bot, GameManager context);
    public void CompleterMain(Bot bot, GameManager context);
    public void JouerCroyant(Bot bot, GameManager context);
    public void JouerGuideSpirituel(Bot botr, GameManager context);
    public void JouerDeusEx(Bot bot, GameManager context);
    public void JouerApocalyspe(Bot bot, GameManager context);
}
