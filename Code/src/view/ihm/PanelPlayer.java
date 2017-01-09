package view.ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.player.Bot;
import model.player.Player;

public class PanelPlayer extends JPanel{
		public PanelPlayer(Player p){
			StringBuffer sb = new StringBuffer();
		
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setPreferredSize(new Dimension(110,110));
			
			JLabel nomPersonne = new JLabel();
			if (p instanceof Bot){
				sb.append("bot: ");
				sb.append(p.getNom());
				nomPersonne.setText(sb.toString());
			}else {
				nomPersonne.setText(p.getNom());
			}

			nomPersonne.setAlignmentX(Component.CENTER_ALIGNMENT);
			nomPersonne.setVerticalAlignment(SwingConstants.CENTER);

			this.add(nomPersonne);
			
			this.add(new ImagePanel("./images/imageJoueur/joueur.png", 225/2, 225/2));
			
			this.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
		}
}