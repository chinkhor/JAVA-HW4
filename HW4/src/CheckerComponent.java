import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class CheckerComponent extends JButton
{  
   private int x = 25, y = 25;
   private int size = 50;
   private Color player;
   private int row, col;
   
   public CheckerComponent(Color player, int row, int col)
   {
	   super();
	   setBorderPainted(false);
	   this.player = player;
	   this.row = row;
	   this.col = col;
   }
   
   public void paintComponent(Graphics g)
   {  
      g.setColor(player);
      g.drawOval(x, y, size, size);
      g.fillOval(x, y, size, size);
      super.paintComponent(g);
   }
   
    public Color getPlayer()
	{
		return this.player;
	}

	public int getRow()
	{
		return this.row;
	}
	
	public int getCol()
	{
		return this.col;
	}
}
