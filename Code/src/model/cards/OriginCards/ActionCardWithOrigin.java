package model.cards.OriginCards;

import java.io.Serializable;


import model.EnumType.EnumCosmogonie;
import model.cards.ActionCard;

public abstract class ActionCardWithOrigin extends ActionCard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EnumCosmogonie origine;
	
	public EnumCosmogonie getOrigine() {
		return origine;
	}

	public ActionCardWithOrigin(String nom, EnumCosmogonie origine) {
		super(nom);
		this.setOrigine(origine);
	}
	
	private void setOrigine(EnumCosmogonie origine) {
		this.origine = origine;
	}


	@Override
	public String toString() {
		return "ActionCardWithOrigin [origine=" + origine + ", nom=" + nom + "]";
	}
}
