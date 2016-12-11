package model.game;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.exception.ObservateurNotLinkedException;
import model.player.Player;
import view.ObservateurJoueurReel;

public interface IObservableGameManager {	
	
	public void notifyPlayerVictory(Player p);
	
	public void notifyPlayerDefeat(Player p);
}