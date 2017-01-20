package model;

import java.util.Iterator;
import java.util.LinkedList;
import view.Observateur;

/**Classe Observer pour mettre a jour les joueurs*/
public abstract class Observer {
	
	/**Attribut qui contient tous les observateurs*/
	private LinkedList<Observateur> listeObservateur = new LinkedList<Observateur>();
	
	/**Methode permettant d'ajouter un nouvel observateur
	 * @param o le nouvel observateur a ajoute
	 */
	public void attacher(Observateur o){
		listeObservateur.push(o);
	}
	
	/**Methode permettant de supprimer un observateur
	 * @param o l'observateur a supprimer
	 */
	public void detacher(Observateur o){
		listeObservateur.remove(o);
	}
	
	/**Methode permettant de mettre a jour les observateurs*/
	public void notifier(){
		Iterator<Observateur> itObservateur = listeObservateur.iterator();
		Observateur o;
		while(itObservateur.hasNext()){
			o = itObservateur.next();
			o.update();
		}
	}
}
