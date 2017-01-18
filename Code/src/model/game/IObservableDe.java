package model.game;

import controller.IObserverGameManager;

public interface IObservableDe {
	/**Methode permettant de notifier la vue lorsque la face du d� est chang�e*/
	public void notifyChangementFace();
	
	/**Methode permettant d'attacher un observer*/
	public void attacher(IObserverGameManager o);
	
	/**Methode permettant de detacher un observer*/
	public void detacher(IObserverGameManager o);
}
