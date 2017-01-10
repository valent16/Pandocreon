package view.ihm;

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.game.GameManager;
import model.player.Player;

public class ScrollerPlayer extends JPanel {

	private JScrollPane scroll;
	
	private List<PanelPlayer> listePanel = new ArrayList<PanelPlayer>();
	
	JPanel panel;
	//cr�ation de cet objet � chaque fois qu'un joueur est �limin�
    public ScrollerPlayer(List<Player> listeJoueurs) throws HeadlessException {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

//        panel.setPreferredSize(new Dimension(800,150));
        panel.setBorder(BorderFactory.createLineBorder(Color.red));

        scroll = new JScrollPane(panel);
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        setSize(300, 300);
//        setVisible(true);
        
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
    
    /**Methode permettant de surligner le joueur actif
     * 
     */
    public void surlignerJoueurActif(){
    	Iterator<PanelPlayer> it = listePanel.iterator();
    	while (it.hasNext()){
    		PanelPlayer panel = it.next();
    		if (panel.getPlayer() == GameManager.getInstanceUniqueManager().getJoueurActif()){
    			panel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.green));
    		}else{
    			panel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
    		}
    	}
    }
}