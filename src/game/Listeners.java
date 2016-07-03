package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listeners 
{
	public final class Keys implements KeyListener
	{
		private boolean w = false;
		private boolean a = false;
		private boolean s = false;
		private boolean d = false;
		private boolean left = false;
		private boolean right = false;
		private boolean space = false;
		
		public boolean getW()
		{
			return w;
		}
		
		public boolean getSpace()
		{
			return space;
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
			
			if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
			{
				space = true;
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
			
			if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
			{
				space = false;
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
}