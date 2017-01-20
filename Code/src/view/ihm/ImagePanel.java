package view.ihm;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**Panel permettant d'afficher une image de taille dynamique*/
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**Image a afficher*/
	private BufferedImage image;
	
	/**Proprietes de l'image*/
	private int width, height;
	
	/**Constructeur
	 * @param path le chemin de l'image de la carte
	 * @param width la largeur de la carte
	 * @param height la hauteur de la carte
	 */
	public ImagePanel(String path, int width, int height){
		this.width = width;
		this.height = height;
		try {                
	          image = ImageIO.read(new File(path));
	       } catch (IOException e) {
	            e.printStackTrace();
	       }
		this.setPreferredSize(new Dimension(width, height));
		
	}
	
	/**Getter de l'image
	 * @return l'image de la carte
	 */
	public BufferedImage getBufferedImage(){
		return this.image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, width, height, this);
	}
}
