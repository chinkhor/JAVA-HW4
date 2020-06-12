import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;

public class CheckerBoard implements ActionListener 
{
	public static final int MAX_TILES = 8;
	public static final int TILE_SIZE = 100;
	private CheckerTile[][] tile = new CheckerTile[MAX_TILES][MAX_TILES];
	
	public CheckerBoard (Container pane)
	{
		int count = 0;
        for (int row=0; row<MAX_TILES; row++)
        {
        	count++;
  			for (int col=0; col<MAX_TILES; col++)
  			{
  				count++;
  				tile[row][col] = new CheckerTile(row, col);
  				if (count%2!=0)
  					tile[row][col].setBackground(Color.RED);
  				else
  				{
  					tile[row][col].setBackground(Color.BLACK);
  					tile[row][col].setValid(true);
  				}
  				tile[row][col].setPreferredSize(new Dimension(TILE_SIZE,TILE_SIZE));
  				tile[row][col].setLayout(new BorderLayout());
  				
  				pane.add(tile[row][col]);
  			}
        }
	}
	
	public void addPiece(CheckerComponent piece, Color color, int row, int col)
	{
		tile[row][col].add(piece, BorderLayout.CENTER);
		tile[row][col].setOccupied(true, color);
		
		piece.addActionListener(this);
	}

	public void selectTile(int row, int col)
	{
		tile[row][col].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
	}
	
	public void actionPerformed (ActionEvent e)
	{
		CheckerComponent button = (CheckerComponent) e.getSource();
		tile[button.getRow()][button.getCol()].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		//System.out.println("button [" + button.getCol() + ", " + button.getRow() + "], player " + button.getPlayer());
	}


}

