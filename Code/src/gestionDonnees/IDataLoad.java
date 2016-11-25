package gestionDonnees;

import java.util.Collection;

import cards.ActionCard;
import cards.Divinity;

public interface IDataLoad {
	public Collection<ActionCard> chargerCartes();
	public Collection<Divinity> chargerDivinites();
	public void chargerPartie();
}