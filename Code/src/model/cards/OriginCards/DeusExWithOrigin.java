package model.cards.OriginCards;

import model.EnumType.EnumOrigineCA;

public class DeusExWithOrigin extends ActionCardWithOrigin {

	public DeusExWithOrigin(String nom, EnumOrigineCA origine) {
		super(nom, origine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliserPouvoir(String commande) {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return "DeusExWithOrigin [nom=" + nom + ", origine=" +this.getOrigine()+"]";
	}
}
