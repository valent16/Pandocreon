package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.EnumType.EnumDogme;
import model.cards.ActionCard;
import model.cards.Card;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.exception.PAInsuffisantException;
import model.game.GameManager;
import model.player.Human;
import model.player.Player;
import view.Observateur;
import view.ObservateurJoueurReel;
import view.console.VueJoueurReel;

public class JoueurController implements ObservateurJoueurReel {

	VueJoueurReel vueJoueur;
	
	Human joueur;
	
	public JoueurController(Human j) {
		this.joueur = j;
		this.vueJoueur = new VueJoueurReel(this, j);
	}
	
	//Methode appelle lorsque le joueur est en tour d'attente
	public void jouerCarteHorsTour(){
		
	}
	
	public void completerMain(){
		if (joueur.getNbCartes() == Player.NB_CARTE_MAX){
			System.out.println("Votre jeu est deja complet");
			vueJoueur.jouerTour();
		}
		joueur.piocherCartes();
	}
	
	public void supprimerCarte(ActionCard carte){
		joueur.defausserCarte(carte);
	}
	
	public String jouerCarte(ActionCard carte, String choix){
		while (!checkChoiceAction(carte, choix)){
			
		}
		return "l'action a ete effectuee";
	}

	@Override
	public Player selectTarget() {
		String answer;
		int index;
		do{
			answer = vueJoueur.selectTarget();
		}
		while (!checkAnswerTarget(answer));
		index = Integer.parseInt(answer);
		if (index >= GameManager.getInstanceUniqueManager().getIndexJoueur(joueur)){
			return GameManager.getInstanceUniqueManager().getJoueurAtIndex(index+1);
		}
		return GameManager.getInstanceUniqueManager().getJoueurAtIndex(index);
	}

	@Override
	public List<Believer> selectCroyant(SpiritGuide carte) {
		return vueJoueur.selectCroyant(carte);
	}
	
	@Override
	public EnumCosmogonie selectOrigine(ActionCardWithOrigin card) {
		ArrayList<EnumCosmogonie> listePA = new ArrayList<EnumCosmogonie>();
		
		if(joueur.getDicoPA().get(EnumCosmogonie.NEANT) >= 1 ){
			listePA.add(EnumCosmogonie.NEANT);
		}if(joueur.getDicoPA().get(EnumCosmogonie.JOUR) >= 1 ){
			listePA.add(EnumCosmogonie.JOUR);
		}if(joueur.getDicoPA().get(EnumCosmogonie.NUIT) >= 1 ){
			listePA.add(EnumCosmogonie.NUIT);
		}
		
		vueJoueur.selectOrigine(listePA);
		
		
		return null;
	}

	@Override
	public void startTourJoueur() {
		vueJoueur.passageTour();
		vueJoueur.jouerTour();	
	}
	
	//Fonction de check du choix de la cible
	public boolean checkAnswerTarget(String answer){
		if (!answer.matches("[0-9]+")){
			return false;
		}
		
		if (Integer.parseInt(answer) > GameManager.getInstanceUniqueManager().getNbJoueur()-2){
			return false;
		}
		
		return true;
	}
	
	public boolean checkChoiceAction(ActionCard carte, String choix){
		if (!choix.matches("[0-9]+")){
			return false;
		}
		if (Integer.parseInt(choix) >= carte.getPouvoirs().keySet().size()){
			return false;
		}
		return true;
	}
	
	public boolean checkChoiceOrigine(List<EnumCosmogonie> listeOrigine, String choix){
		if (!choix.matches("[0-9]+")){
			return false;
		}
		if (Integer.parseInt(choix) >= listeOrigine.size()){
			return false;
		}
		return true;
	}
	
	public boolean checkGeneralChoice(String choix, int valMax){
		if (!choix.matches("[0-9]+")){
			return false;
		}
		if (Integer.parseInt(choix) > valMax){
			return false;
		}
		return true;
	}

	
}
