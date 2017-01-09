package view.ihm;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import controller.GameController;

/**Classe qui represente le menu de l'IHM*/
public class Client extends JFrame{

	private static final long serialVersionUID = 1L;

	/**Attribut represennat les composants de l'interface graphique*/
	private JFrame frame = new JFrame();

	/**panel du menu principale*/
	private JPanel menuPrincipal;

	/**bouton pour lancer une nouvelle partie*/
	private JButton newGame;

	/**bouton pour le chargement d'une partie*/
	private JButton loadGame;

	/**button pour les regles*/
	private JButton rules;

	/**liste des strategie*/
	private JComboBox<String> listeStrategie;

	/**label pour le nombre de joueur*/
	private JLabel nombreJoueur, nombreHumain, nombreBot;

	/**logo du jeu*/
	private ImageIcon logo = new ImageIcon("images/logo.png");

	//model
	private final static int NOMBRE_MINIMAL_JOUEUR = 1;
	private final static int NOMBRE_MAXIMAL_JOUEUR = 9;

	/**Attribut representant le controlleur de la partie*/
	private GameController gc;

	/**Attribut representant le nombre total de humain dans la partie*/
	private int nombreTotalHumain = 0;

	/**Attribut representant le nombre total de bot dans la partie*/
	private int nombreTotalBot = 0;

	/**Attribut representant la strategie choisi par le joueur*/
	private String strategie;

	/**Constructeur du client*/
	public Client(){
		gc = new GameController();
		initialize();
		ajoutListener();
		frame.setTitle("Client Pandocreon Divinae");
		frame.setSize(500, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);	
	}

	//methode pour initialiser les composants de la fenetre
	public void initialize(){
		menuPrincipal = new JPanel(new GridBagLayout());
		frame.setContentPane(menuPrincipal);

		menuPrincipal.setPreferredSize(new Dimension(250,250));

		newGame = new JButton("Nouvelle partie");
		newGame.setPreferredSize(new Dimension(200,50));

		loadGame = new JButton("Charger Partie");
		loadGame.setPreferredSize(new Dimension(200, 50));

		rules = new JButton("Afficher les regles");
		rules.setPreferredSize(new Dimension(200,50));

		//ajout des composants
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		menuPrincipal.add(newGame, gbc);

		gbc.gridy = 1;
		menuPrincipal.add(loadGame, gbc);

		gbc.gridy = 2;
		menuPrincipal.add(rules, gbc);
	}

	/**Methode permettant d'ajouter les listener au boutons*/
	private void ajoutListener() {
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nouvellePartie();
			}
		});

		loadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chargerPartie();
			}
		});

		rules.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				afficherRegles();
			}
		});
	}

	/**Methode permettant de lancer une nouvelle partie*/
	public void nouvellePartie(){	
		ajouterBots(); //methode pour ajouter les bots
	}

	//methode qui permet d'ajouter les bots
	private void ajouterBots() {
		JPanel framePanel = new JPanel(new BorderLayout());

		//label nombre de joueur
		nombreBot = new JLabel(""+NOMBRE_MINIMAL_JOUEUR);
		nombreBot.setHorizontalAlignment(JLabel.CENTER); //permet de centrer le contenu du label

		//bordure sur le label nombre de joueur
		Border line = BorderFactory.createLineBorder(Color.GRAY, 2);
		Border panelBorder = BorderFactory.createTitledBorder(line, "");
		nombreBot.setBorder(panelBorder);
		nombreBot.setPreferredSize(new Dimension(40, 40));

		//bouton plus
		JButton plus = new JButton("+");
		plus.setPreferredSize(new Dimension(45, 45));
		plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String d = nombreBot.getText();
				int c = Integer.parseInt(d);
				if(c == NOMBRE_MAXIMAL_JOUEUR)
					c = NOMBRE_MAXIMAL_JOUEUR;
				else
					c++;
				nombreBot.setText(""+c);
				frame.requestFocus();				
			}
		});

		//bouton moins
		JButton moins = new JButton("-");
		moins.setPreferredSize(new Dimension(45, 45));
		moins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String d = nombreBot.getText();
				int c = Integer.parseInt(d);
				if(c == NOMBRE_MINIMAL_JOUEUR)
					c = NOMBRE_MINIMAL_JOUEUR;
				else
					c--;
				nombreBot.setText(""+c);
				frame.requestFocus();				
			}
		});

		JPanel nombreBotPanel = new JPanel(new GridBagLayout()); //Panel pour le nombre de joueur
		JLabel mod = new JLabel("Nombre de bots a ajouter : ("+NOMBRE_MINIMAL_JOUEUR+"-"+NOMBRE_MAXIMAL_JOUEUR+")");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridx = 0;
		gbc.gridy = 0;
		nombreBotPanel.add(mod, gbc);

		gbc.gridx = 1;
		nombreBotPanel.add(moins, gbc);

		gbc.gridx = 2;
		nombreBotPanel.add(nombreBot, gbc);

		gbc.gridx = 3;
		nombreBotPanel.add(plus, gbc);

		//bouton annuler
		JButton annuler = new JButton("Annuler");
		annuler.setPreferredSize(new Dimension(120, 50));
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				retourMenuPrincipale();
			}
		});

		//bouton valider
		JButton valider = new JButton("Valider");
		valider.setPreferredSize(new Dimension(120, 50));
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Vous avez ajouté "+ nombreBot.getText() +" bots. Voulez-vous continuer ?", "bots ajoutés", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logo);
				if(option == JOptionPane.OK_OPTION){
					nombreTotalBot = Integer.parseInt(nombreBot.getText());
					nombreTotalHumain = NOMBRE_MAXIMAL_JOUEUR - nombreTotalBot;
					ajouterDifficulte();//on ajoute les joueurs humain
				}else{
					JOptionPane.showMessageDialog(null, "Veuillez ajouté des bots pour pouvoir lancé une partie", "Probleme ajout de bot", JOptionPane.INFORMATION_MESSAGE, logo);
				}
			}
		});

		//Panel sud
		JPanel southPanel = new JPanel (new FlowLayout());
		southPanel.add(annuler);
		southPanel.add(valider);

		framePanel.add(nombreBotPanel, BorderLayout.CENTER);
		framePanel.add(southPanel, BorderLayout.SOUTH);

		frame.setTitle("Ajout des Bots");
		frame.setContentPane(framePanel);
		frame.repaint();
		frame.validate();
	}

	/**Methode permettant d'ajouter la difficulté des bots*/
	private void ajouterDifficulte(){
		JPanel framePanel = new JPanel(new BorderLayout());

		String[] strats = {"Facile", "Moyen", "Difficile"};//les differentes Strategies
		listeStrategie = new JComboBox<String>(strats);

		JPanel strategieBotPanel = new JPanel(new GridBagLayout()); //Panel pour le nombre de joueur
		JLabel mod = new JLabel("Choississez la strategie des bots (seul la difficulte Moyen fonctionne)");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridx = 0;
		gbc.gridy = 0;
		strategieBotPanel.add(mod, gbc);

		gbc.gridy = 1;
		strategieBotPanel.add(listeStrategie, gbc);

		//bouton annuler
		JButton annuler = new JButton("Annuler");
		annuler.setPreferredSize(new Dimension(120, 50));
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				retourMenuPrincipale();
			}
		});

		//bouton valider
		JButton valider = new JButton("Valider");
		valider.setPreferredSize(new Dimension(120, 50));
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Vous avez ajouté la strategie "+ listeStrategie.getSelectedItem() +" Voulez-vous continuer ?", "strategie des bots", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logo);
				if(option == JOptionPane.OK_OPTION){
					strategie = ""+listeStrategie.getSelectedItem();
					ajouterJoueurHumain();//on ajoute les joueurs humain
				}else
					JOptionPane.showMessageDialog(null, "Veuillez choisir une stratégie pour les bots", "Annulation de la stratégie", JOptionPane.INFORMATION_MESSAGE, logo);
			}
		});

		//Panel sud
		JPanel southPanel = new JPanel (new FlowLayout());
		southPanel.add(annuler);
		southPanel.add(valider);

		framePanel.add(strategieBotPanel, BorderLayout.CENTER);
		framePanel.add(southPanel, BorderLayout.SOUTH);

		frame.setTitle("Ajout de la strategie");
		frame.setContentPane(framePanel);
		frame.repaint();
		frame.validate();
	}

	/**Methode qui permet d'ajouter les joueurs humains en fonction du nombre de joueur saisi*/
	private void ajouterJoueurHumain() {
		JPanel framePanel = new JPanel(new BorderLayout());

		//label nombre de joueur
		nombreHumain = new JLabel(""+1);
		nombreHumain.setHorizontalAlignment(JLabel.CENTER); //permet de centrer le contenu du label

		//bordure sur le label nombre de joueur
		Border line = BorderFactory.createLineBorder(Color.GRAY, 2);
		Border panelBorder = BorderFactory.createTitledBorder(line, "");
		nombreHumain.setBorder(panelBorder);
		nombreHumain.setPreferredSize(new Dimension(40, 40));

		//bouton plus
		JButton plus = new JButton("+");
		plus.setPreferredSize(new Dimension(45, 45));
		plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String d = nombreHumain.getText();
				int c = Integer.parseInt(d);
				if(c == nombreTotalHumain)
					c = nombreTotalHumain;
				else
					c++;
				nombreHumain.setText(""+c);
				frame.requestFocus();				
			}
		});

		//bouton moins
		JButton moins = new JButton("-");
		moins.setPreferredSize(new Dimension(45, 45));
		moins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String d = nombreHumain.getText();
				int c = Integer.parseInt(d);
				if(c == NOMBRE_MINIMAL_JOUEUR)
					c = NOMBRE_MINIMAL_JOUEUR;
				else
					c--;
				nombreHumain.setText(""+c);
				frame.requestFocus();				
			}
		});

		JPanel nombreHumainPanel = new JPanel(new GridBagLayout()); //Panel pour le nombre de joueur
		JLabel mod = new JLabel("Nombre d'humains a ajouter (1 seul) : ("+ 1 + "-" + nombreTotalHumain +")");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridx = 0;
		gbc.gridy = 0;
		nombreHumainPanel.add(mod, gbc);

		gbc.gridx = 1;
		nombreHumainPanel.add(moins, gbc);

		gbc.gridx = 2;
		nombreHumainPanel.add(nombreHumain, gbc);

		gbc.gridx = 3;
		nombreHumainPanel.add(plus, gbc);

		//bouton annuler
		JButton annuler = new JButton("Annuler");
		annuler.setPreferredSize(new Dimension(120, 50));
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				retourMenuPrincipale();
			}
		});

		//bouton valider
		JButton valider = new JButton("Valider");
		valider.setPreferredSize(new Dimension(120, 50));
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Vous avez ajouté "+ nombreHumain.getText() +" de joueurs humains. Voulez-vous continuer ?", "humain ajoutés", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logo);
				if(option == JOptionPane.OK_OPTION){
					nombreTotalHumain = Integer.parseInt(nombreHumain.getText());
					System.out.println(nombreTotalHumain);
					ajouterInfoJoueurHumain();//on ajoute les noms et age des joueurs
				}else
					JOptionPane.showMessageDialog(null, "Veuillez ajouté des joueurs pour pouvoir lancé une partie", "Probleme Ajout de joueur", JOptionPane.INFORMATION_MESSAGE, logo);
			}
		});

		//Panel sud
		JPanel southPanel = new JPanel (new FlowLayout());
		southPanel.add(annuler);
		southPanel.add(valider);

		framePanel.add(nombreHumainPanel, BorderLayout.CENTER);
		framePanel.add(southPanel, BorderLayout.SOUTH);

		frame.setTitle("Nombres d'humains");
		frame.setContentPane(framePanel);
		frame.repaint();
		frame.validate();
	}

	private void ajouterInfoJoueurHumain() {
		lancerPartie();
		/*Faire le panel avec le nom et le prenom des joueurs*/
		//System.out.println("/////////:test1");
		//lancerPartie();//on peut lancer la partie
		//ensuite lancerPartie(); //permet d'instancier les bots

	}

	/**Methode pour lancer la partie*/
	private void lancerPartie() {
		System.out.println("on lance la partie");

		//TODO Dans la classe Player ajouter une instance observateur Player
		//TODO Quand on pose une carte on notifie le joueur et la carte
		//TODO faire un ObservateurPlayer dans view qui permet
		//TODO Creer les bots puis les mettre games dans la nouvelle partie
		//TODO il balance une carte il faut recuperer la carte et le nom du bot

		JFrame test = new JFrame("Test");
		JLabel salut = new JLabel("salut");
		test.getContentPane().add(salut);
		
		test.pack();
		test.setVisible(true);
		


		///TODO A FAIRE AU COMPLET
		//il faut changer la vue car la methode MenuPrinciaple de la classe VUegame est fait pour la consolle il faut appeler une nouvelle methode
		//La methode de game est bonn mais 

		//TODO PROBLEME CA NE LANCE PAS LA FENETRE
		GameController gameController = new GameController();
		gameController.startGame();

		gameController.CreationJoueur("valentin", 22);////////////////////////
		gameController.CreationJoueur("David", 20);//////////////////////////

		gameController.lancerPartie();


		////////////////////
		/*GameController gameController = new GameController();
		gameController.startGame();

		Human joueur1 = new Human("valentin", 18);
		joueur1.attacher(new JoueurController(joueur1));
		Human joueur2 = new Human("David", 20);
		joueur2.attacher(new JoueurController(joueur2));

		gameController.getGame().ajouterJoueurReel(joueur1);
		gameController.getGame().ajouterJoueurReel(joueur2);

		gameController.lancerPartie();*/
	}

	/**Methode pour revenir au panel MenuPrincipale*/
	private void retourMenuPrincipale() {
		int option = JOptionPane.showConfirmDialog(null, "Voulez vous revenir au menu principal ?", "Revenir au Menu Principal", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logo);
		if(option == JOptionPane.OK_OPTION){
			frame.setTitle("Client Pandocreon Divinae");
			frame.setContentPane(menuPrincipal);
			frame.repaint();
			frame.validate();
		}
	}

	private void chargerPartie() {
		JOptionPane.showMessageDialog(null, "Fonctionnalité pas encore developpé", "chargement de partie", JOptionPane.WARNING_MESSAGE, logo);
	}

	/**Methode permettant d'afficher les regles du jeu*/
	private void afficherRegles(){
		String regles = "<html><h1>Pandocreon Divinae</h1>"
				+ "<h2>Livret de Regles</h2>"

		+ "<h3>Mythologie</h3>"
		+ "Dans le monde de Pandocréon, les hommes prient le Haut Dieu, chacun à leur<br/>"
		+ "manière; certains vénèrent la lumière du soleil, d'autres les voies du surnaturel,<br/>"
		+ "d'autres encore sont athées et ne croient qu'en l'espèce humaine. Toutes ces<br/>"
		+ "prières et ces croyances remontent au Haut Dieu et lui donnent sa puissance.<br/>"
		+ "Néanmoins, dans l'ombre de ce Tout-Puissant, de nombreuses Divinités<br/>"
		+ "mineures essayent de récupérer à leur avantage les prières des hommes pour<br/>"
		+ "prendre le pas les unes sur les autres, et surtout pour prendre la place du Haut<br/>"
		+ "Dieu. Pour cela, elles envoient des émissaires, des Guides Spirituels qui vont<br/>"
		+ "habilement canaliser les prières de leurs adeptes pour en récolter la puissance.<br/>"
		+ "Les Divinités sont issues des forces originelles du Jour et de la Nuit. Certaines<br/>"
		+ "ont vu leur apparition se placer sous l'influence du Néant qui, combiné à l'action<br/>"
		+ "du Jour ou de la Nuit, les a fait émerger de l'Aube ou du Crépuscule.<br/>"

		+ "<h3>Présentation du jeu</h3>"
		+ "Vous incarnez des Divinités, qui sont caractérisées par leur Origine (Jour, Nuit,<br/>"
		+ "Aube ou Crépuscule) qui exprime leur filiation, et leurs Dogmes (3 parmi :<br/>"
		+ "Nature, Humain, Symboles, Mystique, Chaos) qui définissent leurs croyances.<br/><br/>"
		+ "Chaque Divinité possèdent une capacité spéciale, un pouvoir utilisable une<br/>"
		+ "unique fois pendant la partie.<br/><br/>"
		+ "Le but du jeu est d’éliminer les autres Divinités et de prendre la place du Haut<br/>"
		+ "Dieu en récupérant les prières d’un maximum de Croyants.<br/>"
		+ "Pour cela, les joueurs vont créer des Croyants, qui seront mis en commun au<br/>"
		+ "milieu de la table. Par la suite, les joueurs pourront créer des Guides Spirituels,<br/>"
		+ "dont le rôle est d’amener à une Divinité un nombre variable de cartes de<br/>"
		+ "Croyants. A cela s’ajoute des cartes Deus Ex qui modifient les rapports de force<br/>"
		+ "en cours de partie.<br/>"
		+ "Lorsqu’une carte Apocalypse est posée, un joueur est éliminé (4 joueurs ou<br/>"
		+ "plus) ou un joueur gagne la partie (2 ou 3 joueurs) en fonction des points de Prière<br/>"
		+ "apportés par les Croyants de chaque Divinité<br/>"

		+ "<h3>Debut de partie</h3>"
		+ "Chaque joueur pioche une Divinité au hasard et la place devant lui.<br/>"
		+ "7 cartes d’Action sont distribuées à tous les joueurs.<br/>"

		+ "<h3>Deroulement d'un tour de jeu</h3>"
		+ "Chaque tour de jeu commence par le lancement du dé de Cosmogonie pour<br/>"
		+ "connaître la manière dont va être influencé ce tour. Le résultat du jet de dé va<br/>"
		+ "donner aux joueurs des points d’Action :<br/>"
		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- face Jour :donne 2 points Jour aux Divinités d’Origine Jour<br/>"
		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;donne 1 point Jour aux Divinités d’Origine Aube<br/>"
		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- face Nuit :donne 2 points Nuit aux Divinités d’Origine Nuit<br/>"
		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;donne 1 point Nuit aux Divinités d’Origine Crépuscule<br/>"
		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- face Néant : donne 1 point Néant aux Divinités Aube et Crépuscule<br/><br/>"
		+ "Les points d’Action ont une Origine (Jour, Nuit ou Néant), qui va décider des<br/>"
		+ "cartes d’Action que la Divinité pourra jouer.<br/><br/>"
		+ "Chaque joueur peut ensuite, à son tour de jeu :<br/>"
		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. se défausser de tout ou partie de ses cartes<br/>"
		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. compléter sa main à 7 cartes<br/>"
		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3. jouer les deux phases suivantes tant que ses cartes le lui permettent<br/>"
		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- utiliser les cartes d’Action qu’il a en main<br/>"
		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- sacrifier une carte Croyants ou Guide Spirituel<br/><br/>"
		+ "Le joueur qui a lancé le dé de Cosmogonie est celui qui aura commencé à jouer<br/>"
		+ "sur ce tour. Au tour suivant, c’est le deuxième joueur qui lance le dé et qui<br/>"
		+ "commence à jouer ; le premier joueur sera donc celui qui jouera en dernier ce<br/>"
		+ "tour-ci, et ainsi de suite.<br/>"

		+ "<h3>Cartes d'action</h3>"
		+ "On distingue 4 types de cartes d’Action différents : les cartes de Croyants, les<br/>"
		+ "Guides Spirituels, les Deus Ex et les Apocalypses.<br/>"
		+ "Pour chaque carte d’Action posée, il en coûte 1 point d’Action de même Origine<br/>"
		+ "(Jour, Nuit ou Néant) ; seule exception, les cartes d’Action d’Origine Néant qui<br/>"
		+ "peuventêtreposées enéchangede2pointsd’Actiond’OrigineJourouNuit.<br/>"
		+ "Il existe des cartes d’Action sans Origine, qui se jouent sans coûter de point<br/>"
		+ "d’Action, et peuvent même être jouées pendant la phase de jeu d’un autre joueur<br/>."

		+ "<h3>Les Croyants</h3>"
		+ "Les cartes de Croyants sont posées par les joueurs au centre de la table. Elles sont<br/>"
		+ "communes à tous<br/>"
		+ "Ce sont les Croyants qui apportent leur puissance aux Divinité. Les cartes<br/>"
		+ "représentent un nombre de Croyants différent (donc toutes les cartes de Croyants<br/>"
		+ "n’auront pas la même importance lors du calcul final des points de Prière).<br/>"
		+ "Les Croyants possèdent des capacités spéciales qui permettent de faire certaines<br/>"
		+ "actions en contrepartie d’un sacrifice (la carte est alors défaussée). Une carte de<br/>"
		+ "Croyants ne peut pas être sacrifiée lorsqu’elle est encore au centre de la table.<br/>"

		+ "<h3>Les Guides Spirituels</h3>"
		+ "Lorsque les Croyants sont au milieu de la table, ils ne rapportent aucun point de<br/>"
		+ "Prière aux Divinités. Pour que celles-ci récupèrent les prières des Croyants, il<br/>"
		+ "faut qu'elles utilisent des Guides Spirituels.<br/>"
		+ "Chaque Guide Spirituel peut ramener avec lui un groupe de plusieurs cartes de<br/>"
		+ "Croyants (le nombre est inscrit sur la carte) auprès d'une Divinité. Elle récupère<br/>"
		+ "alors les prières des Croyants en question.<br/>"
		+ "Un Guide Spirituel peut très bien guider des Croyants ayant une Cosmogonie<br/>"
		+ "(Jour/Nuit/Néant) différente de la sienne. Mais il ne peut guider que des Croyants<br/>"
		+ "ayant au moins un Dogme (Nature, Humain, Symboles, Chaos, Mystique) en<br/>"
		+ "commun avec lui. Un joueur ne peut pas utiliser un Guide Spirituel pour<br/>"
		+ "récupérer un carte de Croyants qu'il vient de créer ; c'est à partir de la phase de jeu<br/>"
		+ "du joueur suivant que la carte de Croyants sera utilisable.<br/>"
		+ "Les Guides Spirituels possèdent des capacités spéciales en échange de sacrifices<br/>"
		+ " (il est alors défaussé, et les Croyants qui y étaient attachés reviennent au centre de la table).<br/>"
		+ "Un Guide Spirituel n'ayant plus de Croyants rattachés à lui (à la suite de sacrifices<br/>"
		+ "par exemple) est défaussé. Sa capacité spéciale de sacrifice n'est pas jouée.<br/>"

		+ "<h3>Les cartes DeusEx</h3>"
		+ "Ces cartes ont des capacités spéciales, agissant sur les Croyants ou les Guides<br/>"
		+ "Spirituels en jeu, voire directement sur les Divinités. Leur effet est immédiat.<br/>"
		+ "Les Deus Ex sans Origine peuvent être utilisées à n’importe quel moment, sans<br/>"
		+ "coûter de point d’Action.<br/>"

		+ "<h3>Les cartes Apocalypse</h3>"
		+ "Lorsqu’un joueur pose une carte Apocalypse, un bouleversement divin a lieu, qui<br/>"
		+ "interrompt la partie. Tous les joueurs additionnent alors le nombre de Croyants<br/>"
		+ "qu’ils possèdent (une carte de Croyants pouvant représenter plusieurs Croyants).<br/>"
		+ "Cela correspond aux points de Prière, la puissance reçue par la Divinité. Deux cas<br/>"
		+ "de figure se présentent alors :<br/>"
		+ "<b>- 4 joueurs ou plus </b>: le joueur ayant la plus faible puissance de Prière est éliminé<br/>"
		+ "de la partie. Si plusieurs joueurs sont les derniers à égalité, la carte Apocalypse<br/>"
		+ "est défaussée sans effet.<br/>"
		+ "<b>- 2 ou 3 joueurs </b>: le joueur ayant la plus forte puissance de Prière gagne la partie.<br/>"
		+ "Si plusieurs joueurs sont premiers à égalité, la carte Apocalypse est défaussée<br/>"
		+ "sans effet.<br/><br/>"
		+ "Il est interdit de jouer une carte Apocalypse durant le premier tour de la partie,<br/>"
		+ "ainsi que durant le tour qui suit une Apocalypse.<br/>"
		+ "Après une Apocalypse, et si la partie n’est pas terminée, le joueur qui a posé la<br/>"
		+ "carte commence un nouveau tour de jeu. Les cartes présentes sur la table ne sont<br/>"
		+ "pas défaussées, à l’exception des Guides Spirituels du joueur qui s’est fait<br/>"
		+ "éliminer (les Croyants qui y étaient attachés reviennent au centre de la table).<br/><br/>"
		+ "De la même manière que pour les cartes Deus Ex, il existe des Apocalypses avec<br/>"
		+ "Origine (Jour, Nuit ou Néant) qui coûtent des points d’Action pour être posées,<br/>"
		+ "ainsi que des Apocalypses sans Origine, qui peuvent être posées à tout moment<br/>"
		+ "sans rien coûter.<br/>"

		+ "<html><p><b>Auteurs: </b><i>Gilbert Valentin - Noga Lucas</i></p>"
		+ "<p>Projet Pandocreon Divinae - Université de Technologie de Troyes</p>";

		JLabel labelRegles = new JLabel(regles);
		JScrollPane scrollPanel = new JScrollPane(labelRegles);
		scrollPanel.getVerticalScrollBar().setUnitIncrement(10);//augmente la vitesse de scroling
		scrollPanel.setPreferredSize( new Dimension(600, 700) );
		JOptionPane.showMessageDialog(null, scrollPanel, "Regles du jeu", JOptionPane.INFORMATION_MESSAGE, logo);
	}

	/**Getter Jframe
	 * @return la fenetre graphique
	 */
	public JFrame getFenetre(){
		return frame;
	}

	/**Getter nombre de joueur
	 * @return le nombre de joueur choisi pour demarrer la partie
	 */
	public int getJoueurs(){
		return Integer.parseInt(nombreJoueur.getText());
	}

	/**Getter pour le controller de la partie
	 * @return le GameControler
	 */
	public GameController getGc() {
		return gc;
	}
}
