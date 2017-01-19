package view;

/**Interface qui gere les observateur*/
public interface Observateur {
	
	/**methode permettant lors d'une modification sur un observable d'effectuer un traitement*/
	public void update();
}
