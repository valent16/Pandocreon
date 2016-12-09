package model.cards.withoutOriginCards;

import java.io.Serializable;

import model.cards.ActionCard;

public class DeusEx extends ActionCard implements Serializable{

	public DeusEx(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliserPouvoir(String commande) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "DeusEx [nom=" + nom + "]";
	}
}