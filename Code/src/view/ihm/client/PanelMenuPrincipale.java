package view.ihm.client;

import java.awt.Dimension;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**Classe qui gere la vue de la partie en mode console*/
public class PanelMenuPrincipale extends PanelType{
	
	/**bouton pour lancer une nouvelle partie*/
	private JButton newGame;

	/**bouton pour le chargement d'une partie*/
	private JButton loadGame;

	/**button pour les regles*/
	private JButton rules;

	private static final long serialVersionUID = 1L;

	/**Constructeur
	 * @param c l'interface client
	 */
	public PanelMenuPrincipale(Client c){
		client = c;
		initialize();
		ajouterListener();
	}

	@Override
	protected void initialize(){
		this.setLayout(new GridBagLayout());
		client.getFenetre().setContentPane(this);
		this.setPreferredSize(new Dimension(250,250));

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
		this.add(newGame, gbc);

		gbc.gridy = 1;
		this.add(loadGame, gbc);

		gbc.gridy = 2;
		this.add(rules, gbc);
	}
	
	@Override
	protected void ajouterListener() {
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.ajouterBots();//on ajoute les bots
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
	
	private void chargerPartie() {
		JOptionPane.showMessageDialog(null, "Le chargement de partie est en cours de developpement", "chargement de partie", JOptionPane.WARNING_MESSAGE, logo);
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
}
