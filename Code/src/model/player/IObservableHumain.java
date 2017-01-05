package model.player;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.exception.ObservateurNotLinkedException;
import view.ObservateurJoueurReel;

public interface IObservableHumain {
	
	public void attacher(ObservateurJoueurReel o);
	
	public void detacher(ObservateurJoueurReel o);
	
	//Permet de notifier la vue pour que le joueur puisse selectionner une cible
	public Player notifySelectPlayer() throws ObservateurNotLinkedException;
	
	//Permet de notifier la vue pour faire d�marrer le tour du joueur
	public void notifyStartTour() throws ObservateurNotLinkedException;
	
	public EnumCosmogonie notifySelectOriginePA(ActionCardWithOrigin carte) throws ObservateurNotLinkedException;
	
	//Permet de notifier la vue pour que le joueur puisse s�lectionner les croyants � convertir
	public List<Believer> notifySelectCroyant(SpiritGuide guideSpirituel) throws ObservateurNotLinkedException;
}
