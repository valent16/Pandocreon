package controller;

import model.player.Player;
import view.IViewGame;
import view.console.VueGameManager;

/**Classe qui agit comme un controller du gestionnaur de partie en gerant la vue de la partie et le gestionnaire de partie*/
public class GameManagerController implements IObserverGameManager, IObserverDe{

	/**Attribut correspondant a la vue du gestionnaire de partie*/
//	VueGameManager vue;
	
	IViewGame vue;
	
	/**Constructeur
	 * @param vue la vue du gestionnaire de partie
	 */
	public GameManagerController(IViewGame vue){
		this.vue = vue;
	}
	
	@Override
	public void annoncerDefaitJoueur(Player joueur) {
		vue.afficherDefaite(joueur);
//		vue.afficherElimintation(joueur);
	}

	@Override
	public void annoncerVictoireJoueur(Player joueur) {
		vue.afficherVainqueur(joueur);
//		vue.afficherVictoireJoueur(joueur);
	}

	@Override
	public void updateDe() {
		vue.majFaceDe();
	}

	@Override
	public void miseAJourCroyants() {
		vue.majTableCroyant();
	}

	@Override
	public void miseAJourJoueurs() {
		vue.majJoueurs();
		
	}

	@Override
	public void miseAJourNbTour() {
		vue.majNbTours();
	}

	@Override
	public void miseAJourDe() {
		vue.majFaceDe();
	}
	
	
}
