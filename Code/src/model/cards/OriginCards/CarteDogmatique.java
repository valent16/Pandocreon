package model.cards.OriginCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.enumType.EnumCosmogonie;
import model.enumType.EnumDogme;

/**Classe Abstraite representant une carte avec des Dogmes et une origine*/ 
public abstract class CarteDogmatique extends ActionCardWithOrigin{

	/**Attribut listant les dogmes*/
	private ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();
	
	/**Attribut decrivant le sacrifice*/
	private String sacrifice;
		
	/**Constructeur
	 * @param nom le nom de la carte
	 * @param origine l'origine de la carte
	 * @param dogmes les dogmes de la carte
	 * @param sacrifice la description du sacrifice de la carte
	 */
	public CarteDogmatique(String nom, EnumCosmogonie origine, ArrayList<EnumDogme> dogmes, String sacrifice) {
		super(nom, origine);
		this.dogmes = dogmes;
		this.sacrifice = sacrifice;
	}
	
	/**
	 * Getter de la description du sacrifice
	 * @return description du sacrifice
	 */
	public String getTextSacrifice(){
		return this.sacrifice;
	}
	

	/**Getter des Dogmes 
	 * @return les dogmes de la carte
	 */
	public List<EnumDogme> getDogmes(){
		return Collections.unmodifiableList(dogmes);
	}
	
	/**Methode verifiant si la carte contient un dogme specifique
	 * @param dogme le dogme a tester
	 * @return true si la carte possede le dogme demander sinon false
	 */
	public boolean containsDogme(EnumDogme dogme){
		return dogmes.contains(dogme);
	}
	
	@Override
	public String toString() {
		return "CarteDogmatique [ origine="+getOrigine() +"  dogmes=" + dogmes + ", nom=" + nom + ", sacrifice="+sacrifice+"]";
	}
}
