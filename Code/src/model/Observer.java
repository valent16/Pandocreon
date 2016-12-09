package model;

import java.util.Iterator;
import java.util.LinkedList;

import view.Observateur;

public abstract class Observer {
	
	private LinkedList<Observateur> listeObservateur = new LinkedList<Observateur>();
	
	public void attacher(Observateur o){
		listeObservateur.push(o);
	}
	
	public void detacher(Observateur o){
		listeObservateur.remove(o);
	}
	
	public void notifier(){
		Iterator<Observateur> itObservateur = listeObservateur.iterator();
		Observateur o;
		while(itObservateur.hasNext()){
			o = itObservateur.next();
			o.update();
		}
	}
}