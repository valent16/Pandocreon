package controller;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import model.EnumType.EnumCosmogonie;
import model.cards.ActionCard;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.game.GameManager;
import model.player.Human;
import model.player.Player;
import view.ObservateurJoueurReel;
import view.console.VueJoueurReel;

/**Classe qui agit comme un controller du joueur humain en gerant la vue du joueur et le joueur */
public class JoueurController implements ObservateurJoueurReel {

	/**Attribut correspondant a la vue du joueur*/
	VueJoueurReel vueJoueur;

	/**Attribut correspondant au joueur humain*/
	Human joueur;

	/**Constructeur
	 * @param j l'humain qui sera utilisé par le controller 
	 */
	public JoueurController(Human j) {
		this.joueur = j;
		this.vueJoueur = new VueJoueurReel(this, j);
	}


	/**Methode permettant au joueur de jouer hors de son tour*/
	public void jouerCarteHorsTour(){
		//TODO MEthode a ecrire
	}

	/**Methode permettant au joueur de completer sa main*/
	public void completerMain(){
		if (joueur.getNbCartes() == Player.NB_CARTE_MAX){
			System.out.println("Votre jeu est deja complet");
			vueJoueur.jouerTour();
		}
		joueur.piocherCartes();
	}

	/**Methode permettant au joueur de se defauuser d'une carte
	 * @param carte la carte dont le joueur se defausse
	 */
	public void supprimerCarte(ActionCard carte){
		joueur.defausserCarte(carte);
	}

	/**Methode permettant au joueur de joueur une des cartes de sa main
	 * @param carte la carte que le joueur a choisi
	 * @param choix le choix qu'il a fait avec cette carte comme la sacrifier, ou la defausser par exemple
	 * @return une chaine de caractere pour prouver que l'action a ete effectue
	 */
	public String jouerCarte(ActionCard carte, String choix){
		while (!checkChoiceAction(carte, choix)){

		}
		return "l'action "+ choix + " a ete effectue sur la carte "+ carte;
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

	/**Methode permettant de verifier le choix de la cible du joueur
	 * @param answer le chiffre donne par l'humain
	 * @return true si le chiffre donne est correcte
	 */
	public boolean checkAnswerTarget(String answer){
		if (!answer.matches("[0-9]+"))
			return false;

		if (Integer.parseInt(answer) > GameManager.getInstanceUniqueManager().getNbJoueur()-2)
			return false;
		return true;
	}

	/**Methode permettant de verifier le choix de l'action d'une carte
	 * @param carte la carte en question
	 * @param choix le choix a faire sur la carte
	 * @return true si l'action est valide sur cette carte
	 */
	public boolean checkChoiceAction(ActionCard carte, String choix){
		if (!choix.matches("[0-9]+"))
			return false;

		if (Integer.parseInt(choix) >= carte.getPouvoirs().keySet().size())
			return false;
		return true;
	}

	/**Methode permettant de verifier le choix de l'origine faite par le joueur
	 * @param listeOrigine la liste des origines du joueur 
	 * @param choix ke choix de l'origine
	 * @return true si cette origine est utilisable
	 */
	public boolean checkChoiceOrigine(List<EnumCosmogonie> listeOrigine, String choix){
		if (!choix.matches("[0-9]+"))
			return false;
		if (Integer.parseInt(choix) >= listeOrigine.size())
			return false;
		return true;
	}

	/**Methode permettant de verifier le choix general du joueur
	 * @param choix le choix du joueur
	 * @param valMax la valeur maximal possible
	 * @return true si le choix est possible
	 */
	public boolean checkGeneralChoice(String choix, int valMax){
		if (!choix.matches("[0-9]+"))
			return false;

		if (Integer.parseInt(choix) > valMax)
			return false;
		return true;
	}

	/**Methode d'utiliser des cartes rattachees a un guide
	 * @param carte le croyant rattache au guide
	 */
	public void utiliserCarteRattachee(ActionCard carte){
		SpiritGuide guide;
		if(carte instanceof Believer){//utiliser le sacrifice du croyant
			Iterator<SpiritGuide> itGuide = joueur.getGuides().iterator();
			while(itGuide.hasNext()){
				guide = itGuide.next();
				if (guide.hasCroyant((Believer) carte)){
					guide.supprimerCroyant((Believer) carte);
					if (guide.hasNoCroyant()){
						joueur.defausserGuideRattache(guide);
					}
				}
			}
		}else{//utiliser le sacrifice du guide
			guide = (SpiritGuide) carte;
			Iterator<Believer> itCroyant = guide.getCroyantsConvertis().iterator();
			Believer croyant;
			while(itCroyant.hasNext()){
				croyant = itCroyant.next();
				GameManager.getInstanceUniqueManager().deposerCroyant(croyant);
				guide.supprimerCroyant(croyant);
			}
			System.out.println("coucou");
			joueur.defausserGuideRattache(guide);
		}
		//TODO sacrifier carte Carte
	}
}
