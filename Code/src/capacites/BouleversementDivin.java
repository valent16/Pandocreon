package capacites;

import java.util.ArrayList;
import java.util.Iterator;

import model.game.GameManager;
import model.player.Player;

public class BouleversementDivin extends CapaciteSpeciale {

    // Equivalent à la pose d'une carte Apocalypse
    
    public BouleversementDivin() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        if (gameManager.getPlayers().size()>=4) {
            ArrayList<Player> joueurPlusFaiblePuissance = new ArrayList<Player>();
            int minPriere = gameManager.getPlayers().get(0).getScore();
            Iterator<Player> it = gameManager.getPlayers().iterator();
            while (it.hasNext()) {
                Player p = it.next();
                if (player.getScore() < minPriere) {
                    minPriere = player.getScore();
                    joueurPlusFaiblePuissance.add(player);
                }
            }
            if (joueurPlusFaiblePuissance.size()<2) {
                System.out.println("Le joueur "+joueurPlusFaiblePuissance.get(0).getNom()+" est éliminé de la partie!");
            }
        }else{
            ArrayList<Player> joueurPlusFortePuissance = new ArrayList<Player>();
            int maxPriere = gameManager.getPlayers().get(0).getScore();
            for (Player j : gameManager.getPlayers()) {
                if (player.getScore()>maxPriere) {
                    maxPriere = player.getScore();
                    joueurPlusFortePuissance.add(player);
                }
            }
            if (joueurPlusFortePuissance.size()<2) {
                System.out.println("Le joueur "+joueurPlusFortePuissance.get(0).getNom()+" gagne la partie!");
                gameManager.setEnCours(false);/////////////////////POUVOIR BLOQUER LA PARTIE
            }
        }
    }
    
}
