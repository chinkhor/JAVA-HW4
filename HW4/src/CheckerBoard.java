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
	
	// add piece on board
	public void addPiece(CheckerComponent piece, Color player, int row, int col)
	{
		// place piece on tile
		tile[row][col].add(piece, BorderLayout.CENTER);
		tile[row][col].setOccupied(true, player);
		
		piece.addActionListener(this);
	}
	
	// remove piece from board
	public void removePiece(CheckerComponent piece, int row, int col)
	{
		// remove piece from tile
		tile[row][col].remove(piece);
		tile[row][col].setOccupied(false, null);
		
		if(piece.getActionListeners().length > 0) 
		{
	        for(ActionListener g : piece.getActionListeners()) {
	            piece.removeActionListener(g);
	        }
	    }
		
		deSelectTile (row, col);
	}

	public void selectTile(int row, int col)
	{
		tile[row][col].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
	}
	
	public void deSelectTile(int row, int col)
	{
		tile[row][col].setBorder(BorderFactory.createEmptyBorder());
	}
	
	public void actionPerformed (ActionEvent e)
	{
		CheckerComponent piece = (CheckerComponent) e.getSource();
		
		if (piece.getPlayer() == Checker.getCurrentPlayer())
		{
			int row = piece.getRow();
			int col = piece.getCol();
			
			// get current player
			CheckerPlayer player = Checker.getPlayer(piece.getPlayer());
			
			// get last selected piece (if any) by current player and de-select the component
			CheckerComponent lastSelectedPiece = player.getLastSelectedPiece();
			deSelectTile(lastSelectedPiece.getRow(), lastSelectedPiece.getCol());
			
			// select current piece clicked by player 
			player.selectPiece(row, col);
			selectTile(row, col);
			
		}
		
	}


}

