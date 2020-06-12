import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class CheckerComponent extends JButton
{  
   private int x = CheckerBoard.TILE_SIZE/4, y = CheckerBoard.TILE_SIZE/4;
   private int size = CheckerBoard.TILE_SIZE/2;
   private Color player;
   private int row, col;
   private String label;
   
   // constructor
   public CheckerComponent(Color player, int row, int col)
   {
	   super();
	   setBorderPainted(false);
	   this.player = player;
	   this.row = row;
	   this.col = col;
	   this.label = CheckerComponent.constructLabel(row,  col);
	   //System.out.println("Piece label: " + this.label);
   }
   
   // construct label based on row and col position
   public static String constructLabel (int row, int col)
   {
	   return (Integer.toString(row) + "," + Integer.toString(col));
   }
   
   // paint the button/piece
   public void paintComponent(Graphics g)
   {  
      g.setColor(player);
      g.drawOval(x, y, size, size);
      g.fillOval(x, y, size, size);
      super.paintComponent(g);
   }
   
   // get player owning the piece, his/her color code
   public Color getPlayer()
   {
	   return this.player;
   }

   // get piece row position
   public int getRow()
   {
	   return this.row;
   }
	
   // get piece column position
   public int getCol()
   {
	   return this.col;
   }
	
   // get piece label, constructed with its row and column position or coordinate
   public String getLabel()
   {
	   return this.label;
   }
}
