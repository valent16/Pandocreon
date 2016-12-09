package model.cards.OriginCards;

import java.io.Serializable;


import model.EnumType.Cosmogonie;
import model.cards.ActionCard;

public abstract class ActionCardWithOrigin extends ActionCard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cosmogonie origine;
	
	public Cosmogonie getOrigine() {
		return origine;
	}

	public ActionCardWithOrigin(String nom, Cosmogonie origine) {
		super(nom);
		this.setOrigine(origine);
	}
	
	private void setOrigine(Cosmogonie origine) {
		this.origine = origine;
	}


	@Override
	public String toString() {
		return "ActionCardWithOrigin [origine=" + origine + ", nom=" + nom + "]";
	}
}
