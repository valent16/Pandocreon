package controller;

import java.util.List;

import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.enumType.EnumCosmogonie;
import model.player.Player;

public interface IObserverJoueurReel {
	
	/**Methode permettant au joueur par sa vue de choisir un autre joueur au besoin lors d'une action
	 * @return le joueur cible
	 */
	public Player selectTarget();
	
	/**Methode permettant au joueur par sa vue de choisir les croyants a convertir grace a un guide
	 * @param guideSpirituel le guide spirituel utilise pour convertir les croyants
	 * @return la liste des croyants choisi
	 */
	public List<Believer> selectCroyant(SpiritGuide guideSpirituel);
	
	/**Methode permettant au joueur par sa vue de demarrer son tour de jeu*/
	public void startTourJoueur();
	
	/**Methode permettant au joueur par sa vue de verifier si c'est points d'origine sont suffisant pour utiliser la carte
	 * @param carte la carte a joueur
	 * @return l'origine a utiliser
	 */
	public EnumCosmogonie selectOrigine(ActionCardWithOrigin carte);
	
	/**Methode permettant de mettre a jour les cartes du joueur*/
	public void miseAJourCarte();
	
	/**Methode permettant de mettre a jour les cartes rattachees au joueur*/
	public void miseAJourCarteRattachees();
	
	/**Methode permettant de mettre a jour les points d'action du joueur*/
	public void miseAJourPA();
	
	/**Methode permettant de mettre a jour la Divinite*/
	public void miseAJourDivinite();
}
