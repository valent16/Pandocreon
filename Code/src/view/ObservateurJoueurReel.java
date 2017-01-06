package view;

import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.player.Player;

public interface ObservateurJoueurReel {
	
	/**Methode permettant au joueur par sa vue de choisir un autre joueur au besoin lors d'une action*/
	public Player selectTarget();
	
	/**Methode permettant au joueur par sa vue de choisir les croyants a convertir grace a un guide
	 * @param carte le guide spirituel utilise pour convertir les croyants
	 */
	public List<Believer> selectCroyant(SpiritGuide guideSpirituel);
	
	
	/**Methode permettant au joueur par sa vue de demarrer son tour de jeu*/
	public void startTourJoueur();
	/**Methode permettant au joueur par sa vue de verifier si c'est points d'origine sont suffisant pour utiliser la carte*/
	public EnumCosmogonie selectOrigine(ActionCardWithOrigin carte);
}
