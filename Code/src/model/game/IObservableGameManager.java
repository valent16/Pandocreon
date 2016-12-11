package model.game;

import model.player.Player;

public interface IObservableGameManager {	
	
	public void notifyPlayerVictory(Player p);
	
	public void notifyPlayerDefeat(Player p);
}
