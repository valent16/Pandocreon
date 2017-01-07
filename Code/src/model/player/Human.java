package model.player;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.exception.ObservateurNotLinkedException;
import model.exception.TargetSelectionException;
import view.ObservateurJoueurReel;

/**Un joueur qui représente un humain*/
public class Human extends Player implements IObservableHumain{
	
	/**Attribut representant le controlleur du joueur*/
	private ObservateurJoueurReel observateur;
	
	/**Methode permettant de lui ajouter un controleur
	 * @param o le controleur a ajouter
	 */
	public void attacher(ObservateurJoueurReel o){
		observateur = o;
	}
	
	/**Methode permettant de lui enlever un controleur
	 * @param o le controleur a anlever
	 */
	public void detacher(ObservateurJoueurReel o){
		observateur = null;
	}

	/**Constructeur de joueur qui est appelé pour créer un joueur humain
	 * @param pseudo le nom du joueur
	 * @param age l'age du joueur
	 */
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
	
	@Override
	public Player notifySelectPlayer() throws ObservateurNotLinkedException{
		if (observateur == null){
			throw new ObservateurNotLinkedException("un observateur n'est pas lie a un joueur humain");
		}
		return observateur.selectTarget();
	}
	
	@Override
	public void notifyStartTour() throws ObservateurNotLinkedException{
		if (observateur == null){
			throw new ObservateurNotLinkedException("un observateur n'est pas lie a un joueur humain");
		}
		observateur.startTourJoueur();
	}
	
	@Override
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
			throw new ObservateurNotLinkedException("un observateur n'est pas lie a un joueur humain");
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
