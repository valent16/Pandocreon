package view.ihm;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**Permet la creation d'une image encapsulee dans un label*/
public class ImageLabel extends JLabel{
  
	private static final long serialVersionUID = 1L;
	
	/**L'image a mettre dans le label*/
	private Image image;

	/**Constructeur
	 * @param text le text associe a l'image
	 */
    public ImageLabel(String text){
        super(text);
    }

    /**Setter de l'icon
     * @param icon l'image du label
     */
    public void setIcon(Icon icon) {
        super.setIcon(icon);
        if (icon instanceof ImageIcon)
            image = ((ImageIcon) icon).getImage();
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}