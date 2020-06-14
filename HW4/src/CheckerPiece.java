import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class CheckerPiece extends JButton
{  
   private int x = CheckerBoard.TILE_SIZE/4, y = CheckerBoard.TILE_SIZE/4;
   private int size = CheckerBoard.TILE_SIZE/2;
   private Color player;
   private int row, col;
   private String label;
   private boolean crowned;
   private boolean preSelect;
   private Image img;
   
   // constructor
   public CheckerPiece(Color player, int row, int col, boolean crown)
   {
	   super();
	   setBorderPainted(false);
	   this.player = player;
	   this.row = row;
	   this.col = col;
	   this.crowned = crown;
	   this.label = CheckerPiece.constructLabel(row,  col);
	   this.preSelect = false;
	   try 
	   {
		   this.img = ImageIO.read(new File("src/crown.png"));
	   } catch (IOException e)
	   {
		   System.out.println("Couldn't load/find crown.png");
		   System.exit(0);
	   }
   }
   
   public boolean getPreSelect()
   {
	   return preSelect;
   }
   
   public void setPreSelect(boolean select)
   {
	   preSelect = select;
   }
   
   // construct label based on row and col position
   public static String constructLabel (int row, int col)
   {
	   return (Integer.toString(row) + "," + Integer.toString(col));
   }
   
   // paint the button/piece
   public void paintComponent(Graphics g)
   {  
	  Graphics2D g2 = (Graphics2D) g;
	  
	  super.paintComponent(g2);
	  g2.setColor(player);
      g2.drawOval(x, y, size, size);
      g2.fillOval(x, y, size, size);
      
      if (crowned)
      {
    	  g2.drawImage(img, x, y, size,  size,  null);
      }  
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
   
   public void setCrown()
   {
	   crowned = true;
   }
   
   public boolean getCrown()
   {
	   return crowned;
   }
   
}

   