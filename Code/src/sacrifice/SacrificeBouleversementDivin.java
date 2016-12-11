package sacrifice;

import java.util.ArrayList;
import java.util.Iterator;

import model.game.GameManager;
import model.player.Player;

/**Sacrifice equivalent a la capacite d'une apocalypse*/
public class SacrificeBouleversementDivin extends Sacrifice {

    // Equivalent à la pose d'une carte Apocalypse
    
    @Override
    public void effectuerSacrifice(Player player, GameManager gameManager) {
        if (gameManager.getPlayers().size()>=4) {
            ArrayList<Player> joueurPlusFaiblePuissance = new ArrayList<Player>();
            int minPriere = gameManager.getPlayers().get(0).getScore();
            Iterator<Player> it = gameManager.getPlayers().iterator();
            while (it.hasNext()) {
                Player p = it.next();
                if (p.getScore() < minPriere) {
                    minPriere = p.getScore();
                    joueurPlusFaiblePuissance.add(p);
                }
            }
            if (joueurPlusFaiblePuissance.size()<2) {
                System.out.println("Le joueur "+joueurPlusFaiblePuissance.get(0).getNom()+" est éliminé de la partie!");
            }
        }else{
            ArrayList<Player> joueurPlusFortePuissance = new ArrayList<Player>();
            int maxPriere = gameManager.getPlayers().get(0).getScore();
            for (Player p : gameManager.getPlayers()) {
                if (p.getScore()>maxPriere) {
                    maxPriere = p.getScore();
                    joueurPlusFortePuissance.add(p);
                }
            }
            if (joueurPlusFortePuissance.size()<2) {
                System.out.println("Le joueur "+joueurPlusFortePuissance.get(0).getNom()+" gagne la partie!");
                gameManager.setEnCours(false); //TODO: appeler la methode qui arrete la parte
            }
        }
    }
    
}
