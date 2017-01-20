package model.game;

import controller.IObserverGameManager;

public interface IObservableDe {
	/**Methode permettant de notifier la vue lorsque la face du de est changee*/
	public void notifyChangementFace();
	
	/**Methode permettant d'attacher un observer
	 * @param o l'obervateur du gameManager
	 */
	public void attacher(IObserverGameManager o);
	
	/**Methode permettant de detacher un observer
	 * @param o l'obervateur du gameManager
	 */
	public void detacher(IObserverGameManager o);
}
