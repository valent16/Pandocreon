package view.ihm;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import model.game.De;
import model.game.GameManager;
import model.player.Human;
import view.ViewGame;


public class TableJeu extends ViewGame{
	
	JFrame frame = new JFrame();
	
	PanelJoueurReel panelJoueur;
	
	PanelTableJeu panelTableJeu;
	
	public TableJeu(Human joueur){
		//super(controller, j);
		initialize(joueur);
		frame.setVisible(true);
	}
	
	public void initialize(Human joueur){
//		frame.setTitle("Pandocreon Divinae");
		frame.setPreferredSize(new Dimension(1000,1000));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel panelGlobal = new JPanel();
		panelGlobal.setLayout(new BoxLayout(panelGlobal, BoxLayout.Y_AXIS));
		
		panelTableJeu = new PanelTableJeu(GameManager.getInstanceUniqueManager(), De.getInstanceDe());
		panelGlobal.add(panelTableJeu);
		
//		ArrayList<Player> joueurs = GameManager.getInstanceUniqueManager().getPlayers();
//		Iterator<Player> it = joueurs.iterator();
//		
//		Human joueur=null;
//		while (it.hasNext()){
//			Player p = it.next();
//			if (p instanceof Human){
//				joueur = (Human) p;
//			}
//		}
		
		if (joueur != null ){
			panelJoueur = new PanelJoueurReel(joueur);
			panelGlobal.add(panelJoueur);
		}
		

		frame.getContentPane().add(panelGlobal, BorderLayout.CENTER);
		frame.pack();
	}
	
	public PanelJoueurReel getPanelJoueur(){
		return this.panelJoueur;
	}
	
	public PanelTableJeu getPanelTableJeu(){
		return this.panelTableJeu;
	}

}
