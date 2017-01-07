package model.exception;

/**Classe qui traite le cas ou le choix d'une cible n'est pas possible*/
public class TargetSelectionException extends Exception {

	private static final long serialVersionUID = 1L;

	/**Constructeur
	 * @param message le message a afficher
	 */
	public TargetSelectionException(String message){
		super(message);
	}
}
