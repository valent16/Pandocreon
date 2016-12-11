package model.player;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.exception.ObservateurNotLinkedException;
import model.exception.PAInsuffisantException;
import model.exception.TargetSelectionException;
import view.ObservateurJoueurReel;

public class Human extends Player implements IObservableHumain{
	
	private ObservateurJoueurReel observateur;
	
	public void attacher(ObservateurJoueurReel o){
		observateur = o;
	}
	
	public void detacher(ObservateurJoueurReel o){
		observateur = null;
	}

	/**Constructeur de joueur qui est appelé pour créer un joueur humain*/
	public Human(String pseudo, int age) {
		super(pseudo, age);
	}
	
	@Override
	public void jouerTour() {
		incrementerPointActionWithDe();
		try{
			notifyStartTour();
		}catch(ObservateurNotLinkedException e){
			System.err.println(e.getMessage());
		}
	}
	
	//Permet de notifier la vue pour que le joueur puisse s�lectionner la une cible
	public Player notifySelectPlayer() throws ObservateurNotLinkedException{
		if (observateur == null){
			throw new ObservateurNotLinkedException("un observateur n'est pas li� � un joueur humain");
		}
		return observateur.selectTarget();
	}
	
	//Permet de notifier la vue pour faire d�marrer le tour du joueur
	public void notifyStartTour() throws ObservateurNotLinkedException{
		if (observateur == null){
			throw new ObservateurNotLinkedException("un observateur n'est pas li� � un joueur humain");
		}
		observateur.startTourJoueur();
	}
	
	//Permet de notifier la vue pour que le joueur puisse s�lectionner les croyants � convertir
	public List<Believer> notifySelectCroyant(SpiritGuide guideSpirituel) throws ObservateurNotLinkedException{
		if (observateur == null){
			throw new ObservateurNotLinkedException("un observateur n'est pas lie a un joueur humain");
		}
		return observateur.selectCroyant(guideSpirituel);
	}
	
	@Override
	public Player pickTarget() throws TargetSelectionException{
		try{
			return notifySelectPlayer();
		}catch(Exception e){
			System.err.println(e.getMessage());
			throw new TargetSelectionException("Erreur lors de la selection de la cible");
		}
	}
	
	@Override
	public EnumCosmogonie notifySelectOriginePA(ActionCardWithOrigin carte) throws ObservateurNotLinkedException{
		if (observateur == null){
			throw new ObservateurNotLinkedException("un observateur n'est pas li� � un joueur humain");
		}
		return observateur.selectOrigine(carte);
	}
	
	@Override
	public EnumCosmogonie pickOrigine(ActionCardWithOrigin carte) {
		try{
			return notifySelectOriginePA(carte);
		}catch(ObservateurNotLinkedException e){
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<Believer> pickCroyant(SpiritGuide carte) {
		try{
			return notifySelectCroyant(carte);
		}catch(ObservateurNotLinkedException e){
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}	
}
