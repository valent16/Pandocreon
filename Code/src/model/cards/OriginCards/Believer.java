package model.cards.OriginCards;

import java.util.ArrayList;

import model.enumType.EnumCosmogonie;
import model.enumType.EnumDogme;
import model.player.Player;
/**Classe representant une carte croyant*/
public class Believer extends CarteDogmatique{

	/**Attribut representant le nombre de point que rapporte le croyant*/
	private int nbPrieres;

	/**Constructeur
	 * @param nom le nom du croyant
	 * @param origine l'origine du croyant
	 * @param dogmes les dogmes du croyant
	 * @param nbPrieres le nombre de prieres du croyant
	 * @param sacrifice la description du sacrifice du croyant
	 */
	public Believer(String nom, EnumCosmogonie origine, ArrayList<EnumDogme> dogmes, int nbPrieres, String sacrifice) {
		super(nom, origine, dogmes, sacrifice);
		this.nbPrieres = nbPrieres;
	}

	/**Getter nombre de priere
	 * @return le nombre de prieres du croyant
	 */
	public int getNbPrieres(){
		return nbPrieres;
	}
	
	@Override
	public void utiliserPouvoir(String commande, Player joueur) throws Exception {
		pouvoirs.get(commande).onAction(this, joueur);//decrementation des points d'action du joueur
		
		joueur.retirerCarte(this); //decrementation des points d'action du joueur
	}
	
	@Override
	public String toString() {
		return "Believer nom=" + nom + "[nbPrieres=" + nbPrieres + "]"+ super.toString();
	}
}
