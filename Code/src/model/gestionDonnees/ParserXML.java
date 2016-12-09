package model.gestionDonnees;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.EnumType.EnumDogme;
import model.EnumType.Cosmogonie;
import model.EnumType.EnumOrigineDivinite;
import model.cards.ActionCard;
import model.cards.Divinity;
import model.cards.OriginCards.ApocalypseWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.DeusExWithOrigin;
import model.cards.OriginCards.SpiritGuide;
import model.cards.withoutOriginCards.Apocalypse;
import model.cards.withoutOriginCards.DeusEx;
import model.pouvoir.Pouvoir;
import model.pouvoir.pouvoirCarte.ConversionCroyant;
import model.pouvoir.pouvoirCarte.DepotCroyant;
import model.pouvoir.pouvoirCarte.PouvoirApocalypse;

public class ParserXML implements IDataLoad {

	private LinkedList<ActionCard> cartesAction = new LinkedList<ActionCard>();
	
	private LinkedList<Divinity> divinites = new LinkedList<Divinity>();
	
	public ParserXML() {
		parserFichier();
	}
	
	
	@Override
	public LinkedList<ActionCard> chargerCartes() {
//		System.out.println("coucou");
		return this.cartesAction;
	}

	@Override
	public LinkedList<Divinity> chargerDivinites() {
		return this.divinites;
	}

	@Override
	public void chargerPartie() {
		// TODO Auto-generated method stub
		
	}
	
	private String recupTypeCarte(String type){
		Pattern p=Pattern.compile("carte-([a-zA-Z]*)((,.*)||$)");
		Matcher m=p.matcher(type);
		while(m.find()){
			return m.group(1);
		}	
		return null;
	}
	
	private Cosmogonie recupOrigineCarte(String template){
		Pattern p=Pattern.compile(".*origine-([a-zA-Z]*).*");
		Matcher m=p.matcher(template);
		while(m.find()){
			//System.out.println(m.group(1));
			return convertOrigineCAFromString(m.group(1));
		}	
		return null;
	}
	private EnumOrigineDivinite recupOrigineCarteDiv(String template){
		Pattern p=Pattern.compile(".*origine-divinites-([a-zA-Z]*).*");
		Matcher m=p.matcher(template);
		while(m.find()){
			return convertOrigineDiviniteFromString(m.group(1));
//			return m.group(1);
		}	
		return null;
	}
	
	private ArrayList<EnumDogme> getDogmeDiv(String template){
		ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();
		
		Pattern p=Pattern.compile(".*dogmes-divinites-(.*)");
		Matcher m=p.matcher(template);
		while(m.find()){
			String[] rsltt = m.group(1).split("_");
			for(int i=0; i<rsltt.length;i++){
				dogmes.add(convertDogmeFromString(rsltt[i]));
			}
			return dogmes;
		}	
		return null;
	}
	
	//Permet d'obtenir les dogmes d'une carte Action
	private ArrayList<EnumDogme> getDogmeCA(String template){
		ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();	
		Pattern p=Pattern.compile(".*dogmes-(.*)");
		Matcher m=p.matcher(template);
		while(m.find()){
			String[] rsltt = m.group(1).split("_");
			for (int i= 0; i<rsltt.length; i++){
				dogmes.add(convertDogmeFromString(rsltt[i]));
			}
			return dogmes;
		}
		return null;
	}
	
	//Permet de d'obtenir à partir d'un dogme en chaine de caract�re sa correspondance en 
	//EnumDogme
	private EnumDogme convertDogmeFromString(String dogme){
		switch(dogme){
		case "nature":
			return EnumDogme.NATURE;
			
		case "mystique":
			return EnumDogme.MYSTIQUE;
			
		case "chaos":
			return EnumDogme.CHAOS;
			
		case "symboles":
			return EnumDogme.SYMBOLE;
			
		case "humain":
			return EnumDogme.HUMAIN;
			
		default:
			return EnumDogme.NOTREFERENCED;
		}
	}
	
	//Permet de d'obtenir � partir d'une origine en chaine de caract�re sa correspondance en 
	//EnumOrigneDivinite
	private EnumOrigineDivinite convertOrigineDiviniteFromString(String origine){
		switch(origine){
		case "jour":
			return EnumOrigineDivinite.JOUR;
		case "aube":
			return EnumOrigineDivinite.AUBE;
		case "crepuscule":
			return EnumOrigineDivinite.CREPUSCULE;
		case "nuit":
			return EnumOrigineDivinite.NUIT;
		default:
			return EnumOrigineDivinite.NOTREFERENCED;
		
		}
	}
	
	//Permet de d'obtenir � partir d'une origine en chaine de caract�re sa correspondance en 
	//EnumOrigineCA
	private Cosmogonie convertOrigineCAFromString(String origine){
		switch(origine){
		case "jour":
			return Cosmogonie.JOUR;
		case "nuit":
			return Cosmogonie.NUIT;
		case "neant":
			return Cosmogonie.NEANT;
		default:
			return Cosmogonie.NOTREFERENCED;
		}
	}
	
	
	
	public void parserFichier(){
//		System.out.println("debut du parse de fichier");
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		HashMap<String,Integer > mapNbCroyants = new HashMap<String,Integer >();
		mapNbCroyants.put("nbr_un", 1);
		mapNbCroyants.put("nbr_deux", 2);
		mapNbCroyants.put("nbr_trois", 3);
		mapNbCroyants.put("nbr_quatre", 4);
		
		try{
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document=(Document) builder.parse(new File("cartes.xml"));
			final Element racine = document.getDocumentElement();
			final NodeList racineNoeuds = racine.getChildNodes();
			final int nbRacineNoeuds = racineNoeuds.getLength();
			
			for (int i = 0; i<nbRacineNoeuds; i++) {
				if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
				    final Element deck =  (Element) racineNoeuds.item(i);
				    final NodeList cartes = deck.getElementsByTagName("card");
				    final int nbCartes = cartes.getLength();
				    
				    
				    Pouvoir pvCroyant = new DepotCroyant();
				    Pouvoir pvGuide = new ConversionCroyant();
				    Pouvoir pvApocalypse = new PouvoirApocalypse();
				    
				    
				    // cas ou la balise carte est trouv�e
				    if (nbCartes != 0){
				    	for(int j = 0; j<nbCartes; j++) {
			                final Element carte = (Element) cartes.item(j);
			                String[] lstr =  carte.getTextContent().trim().split("\n");
			                try{
			                	String type = recupTypeCarte(carte.getAttribute("template"));
			                	ArrayList<EnumDogme> dogmesCarte;
			                	if (type.equals("divinites")){
			                		EnumOrigineDivinite orDIv = recupOrigineCarteDiv(carte.getAttribute("template"));
			                		dogmesCarte = getDogmeDiv(carte.getAttribute("template".trim()));
			                		Divinity divinite = new Divinity(lstr[0].trim(), dogmesCarte, lstr[3].trim(), orDIv);
			                		divinites.push(divinite);
//			                		System.out.println(divinite.toString());
			                	}else if(type.equals("croyants") || type.equals("guides")){
			                		dogmesCarte = getDogmeCA(carte.getAttribute("template".trim()));
			                		Cosmogonie orCA = recupOrigineCarte(carte.getAttribute("template"));
			                		NodeList image = carte.getElementsByTagName("image");
			                		int nbImage = image.getLength();
			                		int valeurCroyant =0;
			                		if (nbImage == 1){
			                			Element croyants = (Element) image.item(0);
			                			valeurCroyant = mapNbCroyants.get(croyants.getAttribute("id"));
			                		}
			                		if (type.equals("croyants")){
			                			
			                			Believer croyant = new Believer(lstr[0].trim(), orCA, dogmesCarte, valeurCroyant, lstr[3].trim());
			                			croyant.ajouterPouvoir("deposer Croyant", pvCroyant);
			                			cartesAction.push(croyant);
//			                			System.out.println(croyant.toString());
			                		}else{
			                			
			                			SpiritGuide guide = new SpiritGuide(lstr[0].trim(), orCA, dogmesCarte, valeurCroyant, lstr[3].trim());
			                			guide.ajouterPouvoir("convertir Croyant", pvGuide);
//			                			System.out.println(guide.toString());
			                			cartesAction.push(guide);
			                		}
			                		
			                	}else if (type.equals("deusex")){
			                		Cosmogonie orCA = recupOrigineCarte(carte.getAttribute("template"));
			                		if (orCA == null){
			                			DeusEx deusEx = new DeusEx(lstr[0].trim());
			                			cartesAction.push(deusEx);
//			                			System.out.println(deusEx.toString());
			                		}else{
			                			DeusExWithOrigin deusExOrigine = new DeusExWithOrigin(lstr[0].trim(), orCA);
			                			cartesAction.push(deusExOrigine);
//			                			System.out.println(deusExOrigine.toString());
			                		}
			                	}else if (type.equals("apocalypses")){
			                		Cosmogonie orCA = recupOrigineCarte(carte.getAttribute("template"));
			                		if (orCA == null){
			                			Apocalypse apocalypse = new Apocalypse();
			                			apocalypse.ajouterPouvoir("declencher apocalypse", pvApocalypse);
//			                			cartesAction.push(apocalypse);
//			                			System.out.println(apocalypse.toString());
			                		}else{
			                			ApocalypseWithOrigin apocalypse = new ApocalypseWithOrigin(orCA);
			                			cartesAction.push(apocalypse);
			                		}
			                	}
			                }catch(Exception e){
			                	e.printStackTrace();
			                }
				    	}
				    }
				}
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
//		System.out.println("fin du parser de fichier");
	}
	

}
