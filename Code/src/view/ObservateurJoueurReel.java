package view;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.player.Human;
import model.player.Player;

public interface ObservateurJoueurReel {
	public Player selectTarget();
	
	public List<Believer> selectCroyant(SpiritGuide guideSpirituel);
	
	public void startTourJoueur();
	
	public EnumCosmogonie selectOrigine(ActionCardWithOrigin carte);
}
