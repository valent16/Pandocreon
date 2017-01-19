package model.cards.OriginCards;


import model.enumType.EnumCosmogonie;
import model.player.Player;

/**Classe qui represente une carte Apocalypse avec une Origine*/
public class ApocalypseWithOrigin extends ActionCardWithOrigin{

	/**Constructeur
	 * @param origine l'origine de la carte apocalypse
	 */
	public ApocalypseWithOrigin( EnumCosmogonie origine) {
		super("Apocalypse", origine);
	}

	@Override
	public void utiliserPouvoir(String commande,Player joueur) throws Exception {
		pouvoirs.get(commande).onAction(this, joueur);
		joueur.defausserCarte(this);
	}

}
