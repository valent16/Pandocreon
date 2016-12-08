package model.cards.OriginCards;

import model.EnumType.EnumOrigineCA;
import model.cards.ActionCard;

public abstract class ActionCardWithOrigin extends ActionCard {
	
	private EnumOrigineCA origine;
	
	public EnumOrigineCA getOrigine() {
		return origine;
	}

	public ActionCardWithOrigin(String nom, EnumOrigineCA origine) {
		super(nom);
		this.setOrigine(origine);
	}
	
	private void setOrigine(EnumOrigineCA origine) {
		this.origine = origine;
	}


	@Override
	public String toString() {
		return "ActionCardWithOrigin [origine=" + origine + ", nom=" + nom + "]";
	}
}
