package strategy;

import game.GameManager;
import player.Bot;

/**Interface de stratégie*/
public interface Strategy {	
	
	public void play();
	
	public void DefausserCartes(Bot bot, GameManager context);
    public void CompleterMain(Bot bot, GameManager context);
    public void JouerCroyant(Bot bot, GameManager context);
    public void JouerGuideSpirituel(Bot bot, GameManager context);
    public void JouerDeusEx(Bot bot, GameManager context);
    public void JouerApocalyspe(Bot bot, GameManager context);
}
