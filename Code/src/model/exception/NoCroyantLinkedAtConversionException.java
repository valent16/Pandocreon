package model.exception;

/**Classe qui traite le cas ou aucun croyant n'est convetible*/
public class NoCroyantLinkedAtConversionException extends Exception{

	private static final long serialVersionUID = 1L;

	/**Constructeur
	 * @param message le message a afficher
	 */
	public NoCroyantLinkedAtConversionException(String message){
		super(message);
	}
}
