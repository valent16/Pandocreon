package model.cards.OriginCards;

import java.io.Serializable;
import model.EnumType.Cosmogonie;
import model.player.Player;

public class DeusExWithOrigin extends ActionCardWithOrigin implements Serializable{

	public DeusExWithOrigin(String nom, Cosmogonie origine) {
		super(nom, origine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliserPouvoir(String commande,Player joueur) {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return "DeusExWithOrigin [nom=" + nom + ", origine=" +this.getOrigine()+"]";
	}
}
