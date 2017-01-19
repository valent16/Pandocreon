package model.pouvoir.sacrificeCarte;

import java.util.Scanner;

import model.cards.Card;
import model.enumType.EnumCosmogonie;
import model.game.De;
import model.player.Player;
import model.pouvoir.Pouvoir;

/**Sacrifice qui permet au joueur de poser le dé de Cosmogonie sur la face qu'il désire et commence un nouveau tour de jeu*/
public class SacrificePoserDeFace extends Pouvoir{
    
	/**Constructeur*/
    public SacrificePoserDeFace() {
		super("sacrifice");
	}

	@Override
	public void onAction(Card carte, Player joueur) throws Exception {
		System.out.println("Quel face du dé souhaitez-vous? 1:JOUR  2:NUIT  3:NEANT");
        Scanner sc = new Scanner(System.in);
        int choixFace = sc.nextInt();
        while(choixFace>3 || choixFace<1){
            System.out.println("Le nombre non validé.Veuillez choisir entre [1,2,3]");
            choixFace = sc.nextInt();
        }
        switch(choixFace){
            case 1:
                De.getInstanceDe().setFace(EnumCosmogonie.JOUR);
                break;
            case 2:
            	De.getInstanceDe().setFace(EnumCosmogonie.NUIT);
                break;
            case 3:
            	De.getInstanceDe().setFace(EnumCosmogonie.NEANT);
                break;
        }
        sc.close();
	}  
}
