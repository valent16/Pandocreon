package model.gestionDonnees;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.cards.ActionCard;
import model.cards.Divinity;
import model.game.Game;

public class ParserXML implements IDataLoad {

	@Override
	public Collection<ActionCard> chargerCartes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Divinity> chargerDivinites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void chargerPartie() {
		// TODO Auto-generated method stub
		
	}
	
	private String recupTypeCarte(String type){
		Pattern p=Pattern.compile("carte-([a-zA-Z]*),.*");
		Matcher m=p.matcher(type);
		while(m.find()){
			return m.group(1);
		}	
		return null;
	}
	
	private String recupOrigineCarte(String template){
		Pattern p=Pattern.compile(".*origine-([a-zA-Z]*).*");
		Matcher m=p.matcher(template);
		while(m.find()){
			//System.out.println(m.group(1));
			return m.group(1);
		}	
		return null;
	}
	private String recupOrigineCarteDiv(String template){
		Pattern p=Pattern.compile(".*origine-divinites-([a-zA-Z]*).*");
		Matcher m=p.matcher(template);
		while(m.find()){
			return m.group(1);
		}	
		return null;
	}
	
	private String[] getDogmeDiv(String template){
		Pattern p=Pattern.compile(".*dogmes-divinites-(.*)");
		Matcher m=p.matcher(template);
		while(m.find()){
			String[] rsltt = m.group(1).split("_");
			return rsltt;
		}	
		return null;
	}
	private String[] getDogmeCA(String template){
		Pattern p=Pattern.compile(".*dogmes-(.*)");
		Matcher m=p.matcher(template);
		while(m.find()){
			String[] rsltt = m.group(1).split("_");
			return rsltt;
		}	
		return null;
	}
	
	
	public void parserFichier(){
		System.out.println("d�but du parse de fichier");
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
				    if (nbCartes != 0){
				    	for(int j = 0; j<nbCartes; j++) {
			                final Element carte = (Element) cartes.item(j);
			                String[] lstr =  carte.getTextContent().trim().split("\n");
			                try{
			                	String type = recupTypeCarte(carte.getAttribute("template"));
			                	String texteCarte =type +" ";
			                	String[] dCarte;
			                	if (type.equals("divinites")){
			                		String orDIv = recupOrigineCarteDiv(carte.getAttribute("template"));
			                		dCarte = getDogmeDiv(carte.getAttribute("template"));
			                		texteCarte = orDIv + " "+ texteCarte;
			                		
			                	}else{
			                		String orCA = recupOrigineCarte(carte.getAttribute("template"));
			                		dCarte = getDogmeCA(carte.getAttribute("template"));
			                		texteCarte = orCA + " "+ texteCarte;
			                		
			                		NodeList image = carte.getElementsByTagName("image");
			                		int nbImage = image.getLength();
			                		for (int m = 0; m<nbImage; m++){
			                			Element croyants = (Element) image.item(m);
			                			String valeurCroyant = croyants.getAttribute("id");
			                			texteCarte = texteCarte + mapNbCroyants.get(valeurCroyant) +" ";
			                		}
			                	}
			                	if (dCarte != null){
		                			String org="";
		                			for (int y = 0; y<dCarte.length; y++){
		                				texteCarte = dCarte[y]+" "+texteCarte;
		                			}
		                		}
			                	System.out.println(texteCarte);		                
				                System.out.println(lstr[0]);
				                System.out.println(lstr[3].trim());
			                }catch(Exception e){
			                	
			                }
				    	}
				    }
				}
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}

			//Affiche l'encodage
		
			//System.out.println(document.getXmlEncoding());  


			//Affiche s'il s'agit d'un document standalone      

			//System.out.println(document.getXmlStandalone());
			
			
			//FileInputStream xmlFile = new FileInputStream("cartes.xml");
		
//		try {
//			final Element racine = document.getDocumentElement();

			//org.jdom2.Document document =  builder.build(xmlFile);
			//Element rootNode = document.getRootElement();
			
//			System.out.println();
//			
//			List list = rootNode.getChildren("deck");
//			
//			//System.out.println(list.size());
//			for (int i = 0; i < list.size(); i++) {
//			   Element node1 = (Element) list.get(i);
//			   List liste = node1.getChildren("card");
//			   System.out.println(liste.get(0));
//			   
//			   
//			   System.out.println(liste);
//			   //List list2 = node1.getChildren();
//			   
//			   
//			   
			   /*
			   for (int j = 0 ; j<list2.size(); j++){
				   Element node2 = (Element) list.get(j);
//				   Element node2 = (Element) 
//				   List liste2 = node.getChildren();
				   System.out.println(node2.getContent());
			   }
			   */
			   
			   
//			   System.out.println("Text carte : " + node.getChildText("card"));
//			   System.out.println("Last Name : " + node.getChildText("lastname"));
//			   System.out.println("Nick Name : " + node.getChildText("nickname"));
//			   System.out.println("Salary : " + node.getChildText("salary"));
//			   System.out.println("" + node.getChildText("deck"));
//			}
//
//		  } catch (IOException io) {
//			System.out.println(io.getMessage());
//		  } catch (JDOMException jdomex) {
//			System.out.println(jdomex.getMessage());
//		  }
//		
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//		}
		
		
		System.out.println("fin du parser de fichier");
	}
	
	//permet de tester la fonction
	public static void main(String[] args) {	
		ParserXML p = new ParserXML();
		p.parserFichier();
	}
}
