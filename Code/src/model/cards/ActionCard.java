package model.cards;

/**Classe abstraite représente les cartes actions*/
public abstract class ActionCard extends Card {

	public ActionCard(String nom) {
		super(nom);
	}

	@Override
	public String toString() {
		return "ActionCard [nom=" + nom + "]";
	}
}
