package view.ihm;

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.game.GameManager;
import model.game.Tour;
import model.player.Player;
/**Panel de scroll pour la representation des joueurs dans la partie*/
public class ScrollerPlayer extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**scroll du panel*/
	private JScrollPane scroll;

	/**liste des panel representant chacun un joueur de la partie*/
	private List<PanelPlayer> listePanel = new ArrayList<PanelPlayer>();

	JPanel panel;
	
	/**Constructeur appele lors de l'initialisation du panel
	 * @param listeJoueurs la liste des joueurs de la partie
	 */
	public ScrollerPlayer(List<Player> listeJoueurs) throws HeadlessException {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(BorderFactory.createLineBorder(Color.red));
		scroll = new JScrollPane(panel);
		setLayout(new BorderLayout());
		add(scroll, BorderLayout.CENTER);

		Iterator<Player> it = listeJoueurs.iterator();
		while(it.hasNext()){
			PanelPlayer p = new PanelPlayer(it.next()); 
			panel.add(p);
			listePanel.add(p);
			panel.add(Box.createRigidArea(new Dimension(5,0)));
		}
		panel.revalidate();
		scroll.revalidate();
	}

	/**Methode permettant de mettre a jour la liste des joueurs encore en lice dans la partie*/
	public void majPlayer(){
		List<Player> joueurs = GameManager.getInstanceUniqueManager().getPlayers();

		listePanel.clear();
		panel.removeAll();
		Iterator<Player> it = joueurs.iterator();
		while (it.hasNext()){
			PanelPlayer p = new PanelPlayer(it.next());
			panel.add(p);
			listePanel.add(p);
			panel.add(Box.createRigidArea(new Dimension(5,0)));
		}
		panel.revalidate();
		scroll.revalidate();
	}

	/**Methode permettant de surligner le joueur actif*/
	public void surlignerJoueurActif(){
		Iterator<PanelPlayer> it = listePanel.iterator();
		while (it.hasNext()){
			PanelPlayer panel = it.next();
			if (panel.getPlayer() == Tour.getJoueurActif())
				panel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.green));
			else
				panel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
		}
	}
}