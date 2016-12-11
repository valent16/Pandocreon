package sacrifice;

import java.util.Iterator;
import java.util.Scanner;
import model.cards.ActionCard;
import model.cards.OriginCards.Believer;
import model.game.GameManager;
import model.player.Player;

public class SacrificeBeneficierCapaciteCroyantGuide extends Sacrifice{

	// Permet de beneficier de la capacite speciale de l'un des croyants ou guides spirituels présent sur la table

	@Override
	public void effectuerSacrifice(Player player, GameManager gameManager) {
		Iterator<ActionCard> it = gameManager.getCroyants().iterator();
		System.out.println("Quelle cartes souhaitez-vous bénéficier la capacité");
		while(it.hasNext()){
			ActionCard card = it.next();
			System.out.println("Carte "+ gameManager.getCroyants().indexOf(card) + card);	//affichage des cartes sur la table
		}
		
		Scanner sc = new Scanner(System.in);
		int choixCarte = sc.nextInt();
		
		while(choixCarte > gameManager.getCroyants().size() || choixCarte < 1){
			System.out.println("!!!!!Le nombre non validé.Veuillez choisir un autre.");
			choixCarte = sc.nextInt();
		}
		ActionCard card = gameManager.getCroyants().get(choixCarte);
		if (card instanceof Believer) {
			//((Believer) card). //TODO Appeler le sacrifice de la carte croyant card
		}else{
			//((SpiritGuide) card ).getCapaciteSpeciale().effectuerCapaciteSpeciale(player, gameManager); //TODO Appeler le sacrifice de la carte spirit guide card
		}
	}
}
