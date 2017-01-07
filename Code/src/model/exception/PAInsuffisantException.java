package model.exception;

/**Classe qui traite le cas ou le joueur n'a pas assez de points d'action pour jouer sa carte*/
public class PAInsuffisantException extends Exception {

	private static final long serialVersionUID = 1L;

	/**Constructeur
	 * @param message le message a afficher
	 */
	public PAInsuffisantException(String message){
		super(message);
	}
}
