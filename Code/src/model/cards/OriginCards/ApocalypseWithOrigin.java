package model.cards.OriginCards;

import java.io.Serializable;

import model.EnumType.EnumCosmogonie;
import model.player.Player;

public class ApocalypseWithOrigin extends ActionCardWithOrigin implements Serializable {

	private static final long serialVersionUID = 1L;

	public ApocalypseWithOrigin( EnumCosmogonie origine) {
		super("Apocalypse", origine);
	}

	@Override
	public void utiliserPouvoir(String commande,Player joueur) throws Exception {
		pouvoirs.get(commande).onAction(this, joueur);
		joueur.defausserCarte(this);
	}

}
