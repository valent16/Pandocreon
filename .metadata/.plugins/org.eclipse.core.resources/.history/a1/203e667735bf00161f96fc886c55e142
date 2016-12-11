package capacites;

import java.util.Scanner;

import model.EnumType.Cosmogonie;
import model.game.De;
import model.game.GameManager;
import model.player.Player;

public class PoserDeFace extends CapaciteSpeciale{

    // Le joueur pose le dé de Cosmogonie sur la face qu'il désire et commence un nouveau tour de jeu
    
    public PoserDeFace() {}
    
    @Override
    public void effectuerCapaciteSpeciale(Player player, GameManager gameManager) {
        System.out.println("Quel face du dé souhaitez-vous? 1:JOUR  2:NUIT  3:NEANT");
        Scanner input = new Scanner(System.in);
        int choixFace = input.nextInt();
        while(choixFace>3 || choixFace<1){
            System.out.println("Le nombre non validé.Veuillez choisir entre [1,2,3]");
            choixFace = input.nextInt();
        }
        switch(choixFace){
            case 1:
                De.getInstanceDe().setFace(Cosmogonie.JOUR);
                break;
            case 2:
            	De.getInstanceDe().setFace(Cosmogonie.NUIT);
                break;
            case 3:
            	De.getInstanceDe().setFace(Cosmogonie.NEANT);
                break;
        }
    }  
}
