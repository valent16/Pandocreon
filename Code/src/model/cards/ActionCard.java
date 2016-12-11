package model.cards;

import java.io.Serializable;

/**Classe abstraite représente les cartes actions*/
public abstract class ActionCard extends Card implements Serializable{

	private static final long serialVersionUID = 1L;

	public ActionCard(String nom) {
		super(nom);
	}

	@Override
	public String toString() {
		return "ActionCard [nom=" + nom + "]";
	}
}
