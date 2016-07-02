package game;

import java.awt.Graphics2D;

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
		g2d.drawImage(Game.tl.textureFromName(this.texturename), 0, 0, null, null);
	}
	
	public void setTextureName(String t)
	{
		texturename = t;
	}
}
