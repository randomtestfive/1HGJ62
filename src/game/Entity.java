package game;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Entity extends SimulationBody
{
	public String texturename;
	
	public Entity()
	{
		super();
	}
	
	@Override
	public void render(Graphics2D g2d, double scale)
	{
		// save the original transform
		AffineTransform ot = g2d.getTransform();
		
		// transform the coordinate system from world coordinates to local coordinates
		AffineTransform lt = new AffineTransform();
		lt.translate(this.transform.getTranslationX() * scale, this.transform.getTranslationY() * scale);
		lt.rotate(this.transform.getRotation());
		
		// apply the transform
		g2d.transform(lt);
		g2d.drawImage(Game.tl.textureFromName(this.texturename), 0, 0, null, null);
		g2d.setTransform(ot);
	}
	
	public void setTextureName(String t)
	{
		texturename = t;
	}
}
