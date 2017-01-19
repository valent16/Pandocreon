package controller;

import model.player.Player;

import view.IViewGame;

/**Classe qui agit comme un controller du gestionnaire de partie en gerant la vue de la partie et le gestionnaire de partie*/
public class GameManagerController implements IObserverGameManager, IObserverDe{

	/**Attribut correspondant a la vue du gestionnaire de partie*/	
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
	}

	@Override
	public void annoncerVictoireJoueur(Player joueur) {
		vue.afficherVainqueur(joueur);
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

	@Override
	public void miseAJourJoueurActif() {
		vue.majJoueurActif();
	}
}
