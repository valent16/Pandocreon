package model.gestionDonnees;

import java.util.Collection;

import model.cards.ActionCard;
import model.cards.Divinity;

public interface IDataLoad {
	public Collection<ActionCard> chargerCartes();
	public Collection<Divinity> chargerDivinites();
	public void chargerPartie();
}