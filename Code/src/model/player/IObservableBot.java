package model.player;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.exception.ObservateurNotLinkedException;
import view.ObservateurJoueurReel;

/**Classe qui permet au controller bot de notifier la vue*/
public interface IObservableBot{

	//TODO A MODIF POUR QUE CA CORRESPONDE AU BOT REPRENDRE IOBSERVABLEJOUEUR
	/**Methode permettant d'ajouter un observateur
	 * @param o l'observateur a ajouter
	 */
	public void attacher(ObservateurJoueurReel o);

	/**Methode permettant de retirer un observateur
	 * @param o l'observateur a retirer
	 */
	public void detacher(ObservateurJoueurReel o);

	/**Methode permettant de notifier la vue pour que le joueur selectionner une cible
	 * @return le joueur selectionné
	 * @throws ObservateurNotLinkedException
	 */
	public Player notifySelectPlayer() throws ObservateurNotLinkedException;

	/**Methode permettant de notifier la vue pour que le joueur commence son tour de jeu 
	 * @throws ObservateurNotLinkedException
	 */
	public void notifyStartTour() throws ObservateurNotLinkedException;

	/**Methode permettant de notifier la vue pour que le joueur puisse selectionner le type de points d'actions a utiliser
	 * @param carte la carte a utiliser
	 * @return le type de PA selectionné
	 * @throws ObservateurNotLinkedException
	 */
	public EnumCosmogonie notifySelectOriginePA(ActionCardWithOrigin carte) throws ObservateurNotLinkedException;

	/**Methode permettant de notifier la vue pour que le joueur puisse selectionner les croyants a convertir
	 * @param guideSpirituel le guide qui veut convertir
	 * @return les croyants selectionné pour etre convertis
	 * @throws ObservateurNotLinkedException
	 */
	public List<Believer> notifySelectCroyant(SpiritGuide guideSpirituel) throws ObservateurNotLinkedException;
}