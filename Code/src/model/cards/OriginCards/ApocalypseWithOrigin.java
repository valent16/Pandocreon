package model.cards.OriginCards;

import java.io.Serializable;

import model.EnumType.EnumCosmogonie;
import model.player.Player;

public class ApocalypseWithOrigin extends ActionCardWithOrigin implements Serializable {

	public ApocalypseWithOrigin( EnumCosmogonie origine) {
		super("Apocalypse", origine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliserPouvoir(String commande,Player joueur) throws Exception {
		pouvoirs.get(commande).onAction(this, joueur);
		joueur.defausserCarte(this);
	}

}
