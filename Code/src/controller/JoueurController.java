package controller;

import model.cards.ActionCard;
import model.cards.Card;
import model.exception.PAInsuffisantException;
import model.player.Human;
import model.player.Player;
import view.Observateur;
import view.console.VueJoueurReel;

public class JoueurController implements Observateur {

	VueJoueurReel vueJoueur;
	
	Human joueur;
	
	public JoueurController(Human j) {
		this.joueur = j;
		this.vueJoueur = new VueJoueurReel(this, j);
	}
	
	@Override
	public void update() {
		vueJoueur.passageTour();
		vueJoueur.jouerTour();
	}
	
	//M�thode appel�e lorsque le joueur est en tour d'attente
	public void jouerCarteHorsTour(){
		
	}
	
	public void completerMain(){
		if (joueur.getNbCartes() == Player.NB_CARTE_MAX){
			System.out.println("Votre jeu est d�j� complet");
			vueJoueur.jouerTour();
		}
		joueur.piocherCartes();
	}
	
	public void supprimerCarte(ActionCard carte){
		joueur.defausserCarte(carte);
	}
	
	public String jouerCarte(ActionCard carte){
		
		
		
		try{
			
			
			
			//joueur.JouerCarte(carte);
		}catch(PAInsuffisantException e){
			return e.getMessage();
		}
		return "l'action a �t� effectu�e";
	}

}
