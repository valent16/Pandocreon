package model.gestionDonnees;

import java.util.Collection;
import java.util.LinkedList;

import model.cards.ActionCard;
import model.cards.Divinity;

public interface IDataLoad {
	public LinkedList<ActionCard> chargerCartes();
	public LinkedList<Divinity> chargerDivinites();
	public void chargerPartie();
}
