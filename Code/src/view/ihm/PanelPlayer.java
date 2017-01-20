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
/**Panel representant les joueurs dans la liste de joueurs en haut du panel principal*/
public class PanelPlayer extends JPanel{

	private static final long serialVersionUID = 1L;
	
	/**Attribut representant le joueur*/
	Player joueur;

	/**Constructeur
	 * @param p le joueur du panel
	 */
	public PanelPlayer(Player p){
		this.joueur = p;
		StringBuffer sb = new StringBuffer();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(110, 110));

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
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
	}

	/**Getter du joueur
	 * @return le joueur 
	 */
	public Player getPlayer(){
		return joueur;
	}
}
