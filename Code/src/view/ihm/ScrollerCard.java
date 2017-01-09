package view.ihm;

import java.awt.*;

import java.util.Iterator;

import javax.swing.*;
import java.util.List;

import model.cards.Card;

public class ScrollerCard extends JPanel {

	private static final long serialVersionUID = 1L;

	public ScrollerCard(List<Card> listeCartes) throws HeadlessException {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
//        panel.setPreferredSize(new Dimension(800,150));
        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        final JScrollPane scroll = new JScrollPane(panel);

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
//        setSize(300, 300);
//        setVisible(true);
        
        
        Iterator<Card> it = listeCartes.iterator();
        
        while (it.hasNext()){
        	panel.add(new PanelCarte(it.next()));
        	panel.add(Box.createRigidArea(new Dimension(5,0)));
        }
//        panel.add(initializeExampleCard());
//        panel.add(Box.createRigidArea(new Dimension(5,0)));
//        panel.add(initializeExampleCard());
//        panel.add(Box.createRigidArea(new Dimension(5,0)));
//        panel.add(initializeExampleCard());
//        panel.add(Box.createRigidArea(new Dimension(5,0)));
//        panel.add(initializeExampleCard());
//        panel.add(Box.createRigidArea(new Dimension(5,0)));
//        panel.add(initializeExampleCard());
//        panel.add(Box.createRigidArea(new Dimension(5,0)));
//        panel.add(initializeExampleCard());
//        panel.add(Box.createRigidArea(new Dimension(5,0)));
//        panel.add(initializeExampleCard());
//        panel.add(Box.createRigidArea(new Dimension(5,0)));
//        panel.add(initializeExampleCard());
//        panel.add(Box.createRigidArea(new Dimension(5,0)));
//        panel.add(initializeExampleCard());
//        panel.add(Box.createRigidArea(new Dimension(5,0)));
        
        panel.revalidate();
        scroll.revalidate();
    }
    
//    public JPanel initializeExampleCard(){
//    	
//    	ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();
//		dogmes.add(EnumDogme.CHAOS);
//		dogmes.add(EnumDogme.NATURE);
//		String description = "description de la divinite";
//		EnumOrigineDivinite origine = EnumOrigineDivinite.JOUR;
//		Divinity carte = new Divinity("nom divinite", dogmes, description, origine);
//		
//		PanelCarte panel = new PanelCarte(carte);
//		
//		return panel;
//	}  
}