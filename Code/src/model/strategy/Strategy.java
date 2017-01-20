package model.strategy;

import java.util.List;

import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.enumType.EnumCosmogonie;
import model.player.Bot;
import model.player.Player;

/**Interface de startégie*/
public interface Strategy {	
	
	/**Methode permettant de recuperer les donnees du bot
	 * @param bot le bot qui joue
	 */
	public void setBot(Bot bot);
	
	/**Methode permettant de faire jouer le bot
	 * @param bot le bot qui joue
	 */
	public void jouer(Bot bot);
	
	/**Methode permettant au bot de deposer des croyants*/
	public void depotCroyant();
	
	/**Methode permettant au bot de deposer des croyants*/
	public void convertirCroyants();
	
	/**Methode permettant au bot de lancer une apocalypse*/
	public void lancerApocalypse();
	
	/**Methode permettant au bot de se defausser de certaines cartes*/
	public void defausser();
	
	/**Methode permettant de choisir un joueur pour le bot
	 * @return le joueur cible
	 */
	public Player pickTarget();
	
	/**Methode permettant de choisi les points d'action a jouer
	 * @param carte la carte action que le bot joue
	 * @return la cosmogonie sont les points vont etre depenser pour joueur la carte
	 */
	public EnumCosmogonie pickOrigine(ActionCardWithOrigin carte);
	
	/**Methode permettant de choisir les croyants a convertir
	 * @param carte le guide spirituel qui va convertir
	 * @return la liste des croyants que le guide va convertir
	 */
	public List<Believer> pickCroyant(SpiritGuide carte);

	
}
