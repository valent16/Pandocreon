package view.ihm;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;
	
	private int width, height;
	
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
	
	public BufferedImage getBufferedImage(){
		return this.image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, width, height, this);
	}
}
