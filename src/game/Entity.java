package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

public class Entity extends SimulationBody
{
	public String texturename;
	
	public Entity()
	{
		super();
	}
	
	@Override
	public void render(Graphics2D g, double scale, Color color) {
		// point radius
		final int pr = 4;
		
		// save the original transform
		AffineTransform ot = g.getTransform();
		
		// transform the coordinate system from world coordinates to local coordinates
		AffineTransform lt = new AffineTransform();
		lt.translate(this.transform.getTranslationX() * scale, this.transform.getTranslationY() * scale);
		lt.rotate(this.transform.getRotation());
		
		// apply the transform
		g.transform(lt);
		
		// loop over all the body fixtures for this body
		for (BodyFixture fixture : this.fixtures) {
			this.renderFixture(g, scale, fixture, color);
		}
		
		// set the original transform
		g.setTransform(ot);
	}
	
	@Override
	protected void renderFixture(Graphics2D g, double scale, BodyFixture fixture, Color color) {
		// do we need to render an image?
		AffineTransform oTransform = g.getTransform();
		// translate and rotate
		g.rotate(3.1415);
		if (Game.tl.textureFromName(texturename) != null) {
			// get the shape on the fixture
			Convex convex = fixture.getShape();
			// check the shape type
			if (convex instanceof Rectangle) {
				Rectangle r = (Rectangle)convex;
				Vector2 c = r.getCenter();
				double w = r.getWidth();
				double h = r.getHeight();
				g.drawImage(Game.tl.textureFromName(texturename), 
						(int)Math.ceil((c.x - w / 2.0) * scale),
						(int)Math.ceil((c.y - h / 2.0) * scale),
						(int)Math.ceil(w * scale),
						(int)Math.ceil(h * scale),
						null);
		} else {
			// default rendering
			//super.renderFixture(g, scale, fixture, color);
		}
		g.setTransform(oTransform);
	}
}
	
	public void setTextureName(String t)
	{
		texturename = t;
	}
}
