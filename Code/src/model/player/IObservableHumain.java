package model.player;

import java.util.List;

import controller.IObserverJoueurReel;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.enumType.EnumCosmogonie;
import model.exception.ObservateurNotLinkedException;

/**Classe qui permet au controller joueur de notifier la vue*/
public interface IObservableHumain {

	/**Methode permettant d'ajouter un observateur
	 * @param o l'observateur a ajouter
	 */
	public void attacher(IObserverJoueurReel o);

	/**Methode permettant de retirer un observateur
	 * @param o l'observateur a retirer
	 */
	public void detacher(IObserverJoueurReel o);

	/**Methode permettant de notifier la vue pour que le joueur puisse selectionner une cible
	 * @return le joueur selectionne
	 * @throws ObservateurNotLinkedException exception levee si un observateur n'est pas lie au joueur
	 */
	public Player notifySelectPlayer() throws ObservateurNotLinkedException;

	/**Methode permettant de notifier la vue pour que le joueur commence son tour de jeu 
	 * @throws ObservateurNotLinkedException exception levee si un observateur n'est pas lie au joueur
	 */
	public void notifyStartTour() throws ObservateurNotLinkedException;

	/**Methode permettant de notifier la vue pour que le joueur puisse selectionner le type de points d'action a utiliser
	 * @param carte la carte a utiliser
	 * @return le type de PA selectionne
	 * @throws ObservateurNotLinkedException exception levee si un observateur n'est pas lie au joueur
	 */
	public EnumCosmogonie notifySelectOriginePA(ActionCardWithOrigin carte) throws ObservateurNotLinkedException;

	/**Methode permettant de notifier la vue pour que le joueur puisse selectionner les croyants a convertir
	 * @param guideSpirituel le guide qui veut convertir
	 * @return les croyants selectionnes pour etre convertis
	 * @throws ObservateurNotLinkedException exception levee si un observateur n'est pas lie au joueur
	 */
	public List<Believer> notifySelectCroyant(SpiritGuide guideSpirituel) throws ObservateurNotLinkedException;
	
	/**Methode permettant de notifier le controller du changement de jeu du joueur*/
	public void notifyChangementHand();
	
	/**Methode permettant de notifier le controller du changement des cartes rattachees au joueur*/
	public void notifyChangementCarteRattachees();
	
	/**Methode permettant de notifier le controller du changement des points d'action du joueur*/
	public void notifyChangementPA();
	
	/**Methode permettant de notifier le controller du changement de la divinite du joueur*/
	public void notifyChangementDivinite();
}
