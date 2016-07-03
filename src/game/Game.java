package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.List;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.dynamics.contact.ContactListener;
import org.dyn4j.dynamics.contact.ContactPoint;
import org.dyn4j.dynamics.contact.PersistedContactPoint;
import org.dyn4j.dynamics.contact.SolvedContactPoint;
import org.dyn4j.dynamics.joint.MotorJoint;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;

public class Game extends SimulationFrame {
	/** The serial version id */
	private static final long serialVersionUID = -805418642620588619L;
	
	public static TextureLoader tl;
	
	/** The controller body */
	private SimulationBody controller;
	Listeners l = new Listeners();
	Listeners.Keys k = l.new Keys();
	Entity body2;
	
	/**
	 * Default constructor.
	 */
	public Game()
	{
		super("Game", 45.0);

		this.canvas.addKeyListener(k);
	}
	
	/**
	 * Creates game objects and adds them to the world.
	 */
	protected void initializeWorld()
	{
	    this.world.setGravity(World.EARTH_GRAVITY);
	    
	    body2 = new Entity();
	    //BodyFixture f = new BodyFixture(Geometry.createRectangle(2,2));
	    BodyFixture f = new BodyFixture(Geometry.createCircle(0.5));
	    f.setFriction(0);
	    f.setRestitution(0);
	    body2.setTextureName("player");
	    body2.addFixture(f);
	    body2.setLinearVelocity(new Vector2(0.0, 0.0));
	    body2.setAngularVelocity(0.0);
	    body2.setMass(MassType.NORMAL);
	    body2.setAutoSleepingEnabled(false);
	    world.addBody(body2);
	    
		SimulationBody wallb = new SimulationBody();
		wallb.addFixture(Geometry.createRectangle(20, 0.2));
		wallb.translate(0, -6.5);
		wallb.setMass(MassType.INFINITE);
		world.addBody(wallb);
		
		SimulationBody wallr = new SimulationBody();
		wallr.addFixture(Geometry.createRectangle(0.2, 10));
		wallr.translate(5, 0);
		wallr.setMass(MassType.INFINITE);
		world.addBody(wallr);
		
		SimulationBody plat = new SimulationBody();
		plat.addFixture(Geometry.createRectangle(5, 0.2));
		plat.translate(-3, -3);
		plat.setMass(MassType.INFINITE);
		world.addBody(plat);
		
		SimulationBody plat2 = new SimulationBody();
		plat2.addFixture(Geometry.createRectangle(2, 0.2));
		plat2.translate(4, 2);
		plat2.setMass(MassType.INFINITE);
		world.addBody(plat2);
	}
	
	@Override
	protected void update(Graphics2D g, double elapsedTime)
	{
		body2.setAngularVelocity(0);
		body2.getTransform().setRotation(0);
		//System.out.println(body2.getContacts(false));
		List<ContactPoint> l = body2.getContacts(false);
		boolean grounded = false;
		for(ContactPoint b : l)
		{
			if(b.getPoint().y - body2.getTransform().getTranslationY() < 0)
			{
				grounded = true;
			}
		}
		if(grounded && (k.getW() && (body2.getChangeInPosition().y <= 0.0001 && body2.getChangeInPosition().y >= -0.0001)) || (k.getSpace() && (body2.getChangeInPosition().y <= 0.0001 && body2.getChangeInPosition().y >= -0.0001)))
		{
			body2.setLinearVelocity(body2.getLinearVelocity().x, 10);
		}
		if(k.getA() && body2.getChangeInPosition().x > -0.2)
		{
			body2.applyForce(new Vector2(-20, 0));
		}
		if(k.getD() && body2.getChangeInPosition().x < 0.2)
		{
			body2.applyForce(new Vector2(20, 0));
		}
		if(!k.getD() && !k.getA())
		{
			if(Math.abs(body2.getLinearVelocity().x) > 0.005)
			{
				body2.setLinearVelocity(body2.getLinearVelocity().x/1.1,body2.getLinearVelocity().y);
			}
			else
			{
				body2.setLinearVelocity(0,body2.getLinearVelocity().y);
			}
		}
		super.update(g, elapsedTime);
	}

	double camera = 0;

	@Override
	protected void render(Graphics2D g, double elapsedTime)
	{
		camera = body2.getTransform().getTranslationX() * 45;
		g.translate(-camera, 0);
		super.render(g, elapsedTime);
	}
	
	/**
	 * Converts from screen space to world space.
	 * @param point the screen space point
	 * @return {@link Vector2}
	 */
	private Vector2 toWorldCoordinates(Point point) {
		double x =  (point.getX() - this.canvas.getWidth() / 2.0) / this.scale;
		double y = -(point.getY() - this.canvas.getHeight() / 2.0) / this.scale;
		return new Vector2(x, y);
	}
	
	public static void addTextures()
	{
		tl.addMap("/Textures/spaceman.gif", "player");
		tl.loadTextures();
	}
	
	/**
	 * Entry point for the example application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) 
	{
		tl = new TextureLoader();
		addTextures();
		Game simulation = new Game();
		simulation.run();
	}
}
