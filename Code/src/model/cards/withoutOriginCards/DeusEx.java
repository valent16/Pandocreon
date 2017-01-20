package model.cards.withoutOriginCards;


import model.cards.ActionCard;
import model.player.Player;
/**Classe DeusEx sans origine*/
public class DeusEx extends ActionCard{

	private String description;
	
	/**Constructeur
	 * @param nom le nom de la carte
	 * @param description la description de la carte
	 */
	public DeusEx(String nom, String description) {
		super(nom);
		
		this.description = description;
	}

	/**
	 * Getter sur la description de la carte
	 * @return description de la carte
	 */
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