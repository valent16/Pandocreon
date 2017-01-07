package model.exception;

/**Classe qui traite le cas ou l'observateur ne peut etre ajouter*/
public class ObservateurNotLinkedException extends Exception {

	private static final long serialVersionUID = 1L;

	/**Constructeur
	 * @param message le message a afficher
	 */
	public ObservateurNotLinkedException(String message){
		super(message);
	}
}
