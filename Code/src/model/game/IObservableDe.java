package model.game;

import controller.IObserverGameManager;

public interface IObservableDe {
	/**Méthode permettant de notifier la vue lorsque la face du dé est changée*/
	public void notifyChangementFace();
	
	/**Methode permettant d'attacher un observer*/
	public void attacher(IObserverGameManager o);
	
	/**Methode permettant de detacher un observer*/
	public void detacher(IObserverGameManager o);
}
