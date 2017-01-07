package model.gestionDonnees;

/**Classe possedant les methodes de sauvegarde et de chargement du jeu*/
public class DataManager {
	
	/**Attribut representant la sauvegarde de la partie*/
	private IDataSave gestionnaireSauvegarde;
	
	/**Attribut representant le chargement de la partie*/
	private IDataLoad gestionnaireChargement;
	
	/**Constructeur
	 * @param gestionnaireSauvegarde le type de sauvegarde (XML)
	 * @param gestionnaireChargement le type de chargement (XML)
	 */
	public DataManager(IDataSave gestionnaireSauvegarde, IDataLoad gestionnaireChargement){
		this.gestionnaireSauvegarde = gestionnaireSauvegarde;
		this.gestionnaireChargement = gestionnaireChargement;
	}
	
	/**Getter de la sauvegarde
	 * @return le gestionnaire de sauvegarde
	 */
	public IDataSave getGestionnaireSauvegarde(){
		return this.gestionnaireSauvegarde;
	}
	
	/**getter du chargement
	 * @return le gestionnaire de chargement
	 */
	public IDataLoad getGestionnaireChargement(){
		return this.gestionnaireChargement;
	}
}
