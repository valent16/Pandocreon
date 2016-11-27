package model.cards.OriginCards;

import model.EnumType.EnumOrigineCA;
import model.cards.ActionCard;

public abstract class ActionCardWithOrigin extends ActionCard {
	
	private EnumOrigineCA origine;
	
	public ActionCardWithOrigin(String nom, EnumOrigineCA origine) {
		super(nom);
		this.origine = origine;
	}
}