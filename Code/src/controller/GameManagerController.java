package controller;

import model.player.Player;
import view.IObservateurGameManager;
import view.console.VueGameManager;

/**Classe qui agit comme un controller du gestionnaur de partie en gerant la vue de la partie et le gestionnaire de partie*/
public class GameManagerController implements IObservateurGameManager{

	/**Attribut correspondant a la vue du gestionnaire de partie*/
	VueGameManager vue;
	
	/**Constructeur
	 * @param vue la vue du gestionnaire de partie
	 */
	public GameManagerController(VueGameManager vue){
		this.vue = vue;
	}
	
	@Override
	public void annoncerDefaitJoueur(Player joueur) {
		vue.afficherElimintation(joueur);
	}

	@Override
	public void annoncerVictoireJoueur(Player joueur) {
		vue.afficherVictoireJoueur(joueur);
	}


}
