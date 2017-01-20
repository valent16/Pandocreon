package model.player;

import java.util.LinkedList;
import java.util.List;

import controller.IObserverJoueurReel;
import model.cards.ActionCard;
import model.cards.Card;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.enumType.EnumCosmogonie;
import model.exception.ObservateurNotLinkedException;
import model.exception.PAInsuffisantException;
import model.exception.TargetSelectionException;
import model.game.GameManager;

/**Un joueur qui represente un humain*/
public class Human extends Player implements IObservableHumain{
	
	/**Attribut representant l'observateur du joueur*/
	private IObserverJoueurReel observateur;
	
	/**Methode permettant d'initialiser son observateur
	 * @param o le controleur a ajouter
	 */
	public void attacher(IObserverJoueurReel o){
		observateur = o;
	}
	
	/**Methode permettant de retirer son observateur
	 * @param o le controleur a anlever
	 */
	public void detacher(IObserverJoueurReel o){
		observateur = null;
	}

	/**Constructeur de joueur qui est appelee pour creer un joueur humain
	 * @param pseudo le nom du joueur
	 * @param age l'age du joueur
	 */
	public Human(String pseudo, int age) {
		super(pseudo, age);
	}
	
	@Override
	public void jouerTour() {
		incrementerPointActionWithDe();
		notifyChangementPA();
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

	@Override
	public void notifyChangementHand() {
		observateur.miseAJourCarte();
	}

	@Override
	public void notifyChangementCarteRattachees() {
		observateur.miseAJourCarteRattachees();
	}

	@Override
	public void notifyChangementPA() {
		observateur.miseAJourPA();
	}	
	
	@Override
	public void piocher(){
		hand.push(GameManager.getInstanceUniqueManager().piocherCarte());
		notifyChangementHand();
	}
	
	@Override
	public void ajouterMain(ActionCard card){
		this.hand.push(card);
		notifyChangementHand();
	}
	
	@Override
	public void rattacherGuide(Card carte){
		if(carte instanceof SpiritGuide){
			this.guidesRattaches.add((SpiritGuide) carte);
		}
		notifyChangementCarteRattachees();
	}
	
	@Override
	public void defausserCarte(ActionCard carte){
		hand.remove(carte);
		GameManager.getInstanceUniqueManager().defausserCarte(carte);
		System.out.println("dans la bonne m�thode");
		notifyChangementHand();
	}
	
	@Override
	public void defausserGuideRattache(SpiritGuide guide){
		guidesRattaches.remove(guide);
		GameManager.getInstanceUniqueManager().defausserCarte(guide);
		notifyChangementHand();
	}
	
	@Override
	public void incrementerPointAction(EnumCosmogonie typePA, int nbPA){
		dicoPA.put(typePA, dicoPA.get(typePA) + nbPA);
		notifyChangementPA();
	}
	
	@Override
	public void decrementerPointAction(EnumCosmogonie typePA, int nbPA) throws PAInsuffisantException{
		if ((dicoPA.get(typePA) - nbPA) < 0){
			throw new PAInsuffisantException("Pas assez de point d'action "+dicoPA.get(typePA));
		}
		dicoPA.replace(typePA, dicoPA.get(typePA), dicoPA.get(typePA) - nbPA);
		notifyChangementPA();
	}
	
	@Override
	public void defausserCartes(LinkedList<ActionCard> cartes){
		hand.removeAll(cartes);
		GameManager.getInstanceUniqueManager().defausserCarte(cartes);
		notifyChangementHand();
	}
	
	@Override
	public void piocherDivinite(){
		this.divinity = GameManager.getInstanceUniqueManager().piocherDivinite();
		notifyChangementDivinite();
	}

	@Override
	public void notifyChangementDivinite() {
		observateur.miseAJourDivinite();
	}
}
