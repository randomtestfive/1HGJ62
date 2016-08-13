package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;


public class TextureLoader extends Loader
{
	public final int Player = 0;
	
	/*private ArrayList<String> textureSrc;
	private ArrayList<String> textureName;*/
	//@Override
	protected Map<String, Image> textureMap;

	public Image[] textures;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TextureLoader()
	{
		super();
		textureMap = new HashMap();
	}
	
	/*public void addTexture(String src, String name)
	{
		Src.add(src);
		Name.add(name);
	}*/
	
	public Image textureFromName(String src)
	{
		return textureMap.get(src);
	}
	
	public void loadTextures()
	{
		textures = new Image[Src.size()];
		int loadingTexture = 0;
		for (String src : Src)
		{
			textures[loadingTexture] = new ImageIcon(this.getClass().getResource(src)).getImage();
			textureMap.put(Name.get(loadingTexture), textures[loadingTexture]);
			new ImageIcon(textures[loadingTexture]);
			loadingTexture++;
		}
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
}
