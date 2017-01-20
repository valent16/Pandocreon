package model.game;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import controller.IObserverGameManager;
import model.enumType.EnumCosmogonie;

public class De implements IObservableDe {
	/**Liste des observers du de*/
	private List<IObserverGameManager> observersDe = new ArrayList<IObserverGameManager>();
	
	/**Attribut representant un attribut de classe de*/
	private volatile static De de;
	
	/**Attribut representant la face du de*/
	private EnumCosmogonie face;
	
	/**Constructeur*/
	private De(){}

	/**Methode permetttant d'avoir une seule instance de De
	 * @return de le De de la classe
	 */
	public static synchronized De getInstanceDe(){
		if (de == null){
			synchronized (De.class){ //pour gerer le multi-thread
				if (de == null) 
					de = new De();
			}
		}
		return de;
	}

	/**Methode permettant de lancer de maniere random un de pour obtenir une face*/
	public void lancerDe(){
		int random = (int) (Math.random() * 3 + 1);
		if(random == 1){
			de.face = EnumCosmogonie.JOUR;
		}
		if(random == 2 ){
			de.face = EnumCosmogonie.NUIT;
		}
		if(random == 3 ){
			de.face = EnumCosmogonie.NEANT;
		}
		
		notifyChangementFace();
	}

	/**Getter origine sur lequel le de est retombe
	 * @return la face du De
	 */
	public EnumCosmogonie getFace(){
		return face;
	}
	
	/**Setter origne du de
	 * @param face la nouvelle face du De
	 */
	public void setFace(EnumCosmogonie face){
		this.face = face;
	}

	@Override
	public void notifyChangementFace() {
		Iterator<IObserverGameManager> it = observersDe.iterator();
		while (it.hasNext()){
			it.next().miseAJourDe();
		}
	}

	@Override
	public void attacher(IObserverGameManager o) {
		observersDe.add(o);
	}

	@Override
	public void detacher(IObserverGameManager o) {
		observersDe.remove(o);
	}
}
