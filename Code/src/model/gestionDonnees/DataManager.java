package model.gestionDonnees;

/**Classe possedant les methodes de sauvegarde et de chargement du jeu*/
public class DataManager {
	
	/**Attribut representant le gestionnaire de sauvegarde de la partie*/
	private IDataSave gestionnaireSauvegarde;
	
	/**Attribut representant le gestionnaire de chargement de la partie*/
	private IDataLoad gestionnaireChargement;
	
	/**Constructeur
	 * @param gestionnaireSauvegarde le gestionnaire de sauvegarde (XML)
	 * @param gestionnaireChargement le gestionnaire de chargement (XML)
	 */
	public DataManager(IDataSave gestionnaireSauvegarde, IDataLoad gestionnaireChargement){
		this.gestionnaireSauvegarde = gestionnaireSauvegarde;
		this.gestionnaireChargement = gestionnaireChargement;
	}
	
	/**Getter du gestionnaire de sauvegarde
	 * @return le gestionnaire de sauvegarde
	 */
	public IDataSave getGestionnaireSauvegarde(){
		return this.gestionnaireSauvegarde;
	}
	
	/**getter du gestionnaire de chargement
	 * @return le gestionnaire de chargement
	 */
	public IDataLoad getGestionnaireChargement(){
		return this.gestionnaireChargement;
	}
}
