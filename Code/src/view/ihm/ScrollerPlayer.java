package view.ihm;

import java.awt.*;

import javax.swing.*;

public class ScrollerPlayer extends JPanel {

    public ScrollerPlayer() throws HeadlessException {
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
        
        
        panel.add(initializeExamplePlayer());
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(initializeExamplePlayer());
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(initializeExamplePlayer());
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(initializeExamplePlayer());
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(initializeExamplePlayer());
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(initializeExamplePlayer());
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(initializeExamplePlayer());
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(initializeExamplePlayer());
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(initializeExamplePlayer());
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(initializeExamplePlayer());
        panel.revalidate();
        scroll.revalidate();
    }
    
    public JPanel initializeExamplePlayer(){
//		frame.setTitle("Pandocreon Divinae");
		//frame.setPreferredSize(new Dimension(800, 600));
//		frame.setBounds(100, 100, 450, 300);
//		frame.setPreferredSize(new Dimension(200, 300));
//		frame.setSize(159, 180);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel panelBox = new JPanel();
		panelBox.setLayout(new BoxLayout(panelBox, BoxLayout.Y_AXIS));
		
		panelBox.setPreferredSize(new Dimension(110,110));
////		panelBox.setMaximumSize(new Dimension(150,200));
//		JPanel panelTop = new JPanel();
//		panelTop.setLayout(new BorderLayout());
//		
//		JPanel panelBot = new JPanel();
//		panelBot.setLayout(new BoxLayout(panelBot, BoxLayout.X_AXIS));
//		
//		panelTop.add(new ImagePanel("./images/OrigineCarte/jour.jpg",800/20,800/20), BorderLayout.WEST);
//		
//		panelTop.add(new ImagePanel("./images/nbCroyantsCarte/nbr1.jpg", 800/40, 1600/40), BorderLayout.EAST);
//		
//		panelBot.add(new ImagePanel("./images/dogmesCarte/chaos.jpg", 800/20, 800/20));
//		panelBot.add(new ImagePanel("./images/dogmesCarte/chaos.jpg", 800/20, 800/20));
//		panelBot.add(new ImagePanel("./images/dogmesCarte/chaos.jpg", 800/20, 800/20));
//		
//		panelBox.add(panelTop);
//		
//		
//		JLabel nomCarte = new JLabel();
//		nomCarte.setText("nom de la carte\n");
//		nomCarte.setAlignmentX(Component.CENTER_ALIGNMENT);
//		nomCarte.setVerticalAlignment(SwingConstants.CENTER);
//		
//		JTextArea labelArea = new JTextArea("mon texte\nmon texte\nmon texte");
//		labelArea.setEditable(false);
//		labelArea.setLineWrap(true);
//		
		
		JLabel nomPersonne = new JLabel();
		nomPersonne.setText("Jean-Yves");
		nomPersonne.setAlignmentX(Component.CENTER_ALIGNMENT);
		nomPersonne.setVerticalAlignment(SwingConstants.CENTER);
		
		panelBox.add(nomPersonne);
		panelBox.add(new ImagePanel("./images/imageJoueur/joueur.png", 225/2, 225/2));
		
		panelBox.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
		
		return panelBox;
//		BufferedImage img = null;
//		try {
//		    img = ImageIO.read(new File("./images/fondCarte/fond_apocalypses.jpg"));
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
		
//		BufferedImage dimg = img.getScaledInstance(label.width, label.height,
//		        Image.SCALE_SMOOTH);
		
//		try{
//			Image image = ImageIO.read(new File("./images/fondCarte/fond_apocalypses.jpg"));
//			Image scaledImage = image.getScaledInstance(frame.getContentPane().getWidth(),frame.getContentPane().getHeight(),Image.SCALE_SMOOTH);
//			
//			
//			panel.add(new ImageLabel(image));
//			
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		JLabel l = new JLabel();
//		JPanel panel = new JPanel();
//		frame.getContentPane().add(panel, BorderLayout.NORTH);
//		
//		panel.add(l);
//		
//		BufferedImage img = null;
//		try {
//		    img = ImageIO.read(new File("./images/fondCarte/fond_apocalypses.jpg"));
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
		
		//BufferedImage dimg = (BufferedImage) img.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		
//		ImageIcon imageIcon = new ImageIcon(img);
//		l.setIcon(imageIcon);
//		ImageIcon image = new ImageIcon("./images/fondCarte/fond_apocalypses.jpg");
		
//		panel.add(new JLabel(image));
//		frame.getContentPane().add(new JLabel(image));
//		JLabel l = new JLabel(image);
//		l.
		
//		panel.add();
		
//		 BufferedImage image;

	
//		 try {                
//			 image = ImageIO.read(new File("./images/fondCarte/fond_apocalypses.jpg"));
//		 } catch (IOException ex) {
//			 // handle exception...
//		 }
		
//		buttonImage = new JButton(image);
//		buttonImage.addActionListener(new ActionListener() {
	}

//    public static void main(final String[] args) throws Exception {
//    	
//    	final JFrame frame = new JFrame();
//    	frame.setPreferredSize(new Dimension(800,300));
//    	
//    	final JPanel p = new JPanel();
//    	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
//    	p.add(new JLabel("coucou les amis"));
//    	
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
////                new Scroller().setVisible(true);
//            	p.add( new Scroller());
//            	
//            	frame.pack();
//            }
//        });   
//        
//        
//        frame.getContentPane().add(p);
//        frame.setVisible(true);
//        frame.pack();
//    }
    
}