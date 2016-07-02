package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
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
	
	private final class Keys implements KeyListener
	{
		private boolean w = false;
		private boolean a = false;
		private boolean s = false;
		private boolean d = false;
		private boolean left = false;
		private boolean right = false;
		
		public boolean getW()
		{
			return w;
		}
		
		public boolean getA()
		{
			return a;
		}
		
		public boolean getS()
		{
			return s;
		}
		
		public boolean getD()
		{
			return d;
		}
		
		public boolean getLeft()
		{
			return left;
		}
		
		public boolean getRight()
		{
			return right;
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) 
		{			
			if(arg0.getKeyCode() == KeyEvent.VK_W)
			{
				w = true;
			}
			else if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
			{
				w = true;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_A)
			{
				a = true;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_S)
			{
				s = true;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_D)
			{
				d = true;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				right = true;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
			{
				left = true;
			}
			
			//System.out.println("press "+arg0.getKeyChar());
		}

		@Override
		public void keyReleased(KeyEvent arg0) 
		{
			if(arg0.getKeyCode() == KeyEvent.VK_W)
			{
				w = false;
			}
			else if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
			{
				w = false;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_A)
			{
				a = false;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_S)
			{
				s = false;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_D)
			{
				d = false;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				right = false;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
			{
				left = false;
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
	}
	
	/** The controller body */
	private SimulationBody controller;
	
	Keys k = new Keys();
	Entity body2;
	
	/**
	 * Default constructor.
	 */
	public Game() {
		super("Game", 45.0);

		this.canvas.addKeyListener(k);
	}
	
	/**
	 * Creates game objects and adds them to the world.
	 */
	protected void initializeWorld() {
	    this.world.setGravity(World.EARTH_GRAVITY);
	    
	    body2 = new Entity();
	    BodyFixture f = new BodyFixture(Geometry.createRectangle(0.75,0.75));
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
	protected void update(Graphics2D g, double elapsedTime) {
		body2.setAngularVelocity(0);
		body2.getTransform().setRotation(0);
		System.out.println(body2.getLinearVelocity().x + ", " + body2.getChangeInPosition().x);
		if(k.getW() && body2.getChangeInPosition().y == 0)
		{
			//body2.applyForce(new Vector2(0, 200));
			body2.setLinearVelocity(body2.getLinearVelocity().x, 10);
		}
		if(k.getA() && body2.getChangeInPosition().x > -0.2)
		{
			//System.out.println(body2.getChangeInPosition().y);
			body2.applyForce(new Vector2(-20, 0));
		}
		if(k.getD() && body2.getChangeInPosition().x < 0.2)
		{
			//System.out.println(body2.getChangeInPosition().y);
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
