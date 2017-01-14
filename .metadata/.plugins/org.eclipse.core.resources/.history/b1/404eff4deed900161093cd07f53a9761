package model.cards.withoutOriginCards;

import java.io.Serializable;

import model.cards.ActionCard;
import model.player.Player;
/**Classe DeusEx sans origine*/
public class DeusEx extends ActionCard implements Serializable{

	private static final long serialVersionUID = 1L;

	private String description;
	
	/**Constructeur*/
	public DeusEx(String nom, String description) {
		super(nom);
		
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public void utiliserPouvoir(String commande, Player joueur) throws Exception {
		// TODO A developper Appeller le pouvoir des DEUS EX
	}

	@Override
	public String toString() {
		return "DeusEx [nom=" + nom + "]";
	}
}