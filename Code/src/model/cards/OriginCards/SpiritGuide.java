package model.cards.OriginCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import model.enumType.EnumCosmogonie;
import model.enumType.EnumDogme;
import model.player.Player;

/**Classe représentant un guide spirituel*/
public class SpiritGuide extends CarteDogmatique{

	/**Attribut representant les points que possede le guide*/
	private int pointPriere;

	/**Attribut representant le nombre de croyants convertis*/
	private int nbCarteCroyant;

	/**Attribut representant les croyants convertis*/
	private ArrayList<Believer> croyantsConvertis = new ArrayList<Believer>();

	/**Constructeur
	 * @param nom le nom du guide
	 * @param origine l'origine du guide
	 * @param dogmes les dogmes du guide
	 * @param nbCarteCroyant le nombre de cartes croyant convertibles par le guide
	 * @param sacrifice la description du sacrifice du guide
	 */
	public SpiritGuide(String nom, EnumCosmogonie origine, ArrayList<EnumDogme> dogmes, int nbCarteCroyant, String sacrifice) {
		super(nom, origine, dogmes, sacrifice);
		this.pointPriere = 0;
		this.nbCarteCroyant = nbCarteCroyant;
	}

	/**Getter Croyants Convertis
	 * @return les croyants convertis par le guide
	 */
	public List<Believer> getCroyantsConvertis(){
		return Collections.unmodifiableList(croyantsConvertis);
	}

	@Override
	public void utiliserPouvoir(String commande,Player joueur) throws Exception {
		this.pouvoirs.get(commande).onAction(this, joueur);
		joueur.retirerCarte(this);
	}

	/**Methode verifiant si un croyant a au moins un dogme en commun avec le guide
	 * @param croyant le croyant a tester
	 * @return true si le croyant et le guide ont au moins un dogmes au commaun sinon false
	 */
	public boolean isCroyantCompatible(Believer croyant){
		Iterator<EnumDogme> itDogmeCroyant = croyant.getDogmes().iterator();
		EnumDogme d;
		while(itDogmeCroyant.hasNext()){
			d = itDogmeCroyant.next();
			if (this.getDogmes().contains(d)){
				return true;
			}
		}
		return false;
	}

	/**Methode verifiant si un guide possede un croyant specifique
	 * @param croyant le croyant a tester
	 * @return true si le croyant est deja converti par le guide sinon false
	 */
	public boolean hasCroyant(Believer croyant){
		if (croyantsConvertis.contains(croyant)){
			return true;
		}
		return false;
	}

	/**Methode verifiant si un guide ne possede aucun croyant
	 * @return true si le guide ne possede aucun croyant sinon false
	 */
	public boolean hasNoCroyant(){
		if (croyantsConvertis.size() == 0){
			return true;
		}
		return false;
	}

	/**Methode permettant supprimer un croyant d'un guide
	 * @param croyant le croyant a supprimer
	 */
	public void supprimerCroyant(Believer croyant){
		croyantsConvertis.remove(croyant);
	}
	
	/**Methode permettant convertir un croyant
	 * @param croyant le croyant a convertir
	 */
	public void convertirCroyant(Believer croyant){
		croyantsConvertis.add(croyant);
	}

	/**Getter nombre maximal de croyants
	 * @return le nombre maximal de croyants convertibles
	 */
	public int getNbMaxCroyant(){
		return nbCarteCroyant;
	}

	@Override
	public String toString() {
		return "SpiritGuide nom=" + nom + "[pointPriere=" + pointPriere + ", nbCarteCroyant=" + nbCarteCroyant + ", ]"+super.toString();
	}
}
