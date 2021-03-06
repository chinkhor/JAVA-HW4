import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;

public class CheckerBoard implements ActionListener 
{
	public static final int MAX_TILES = 8;
	public static final int TILE_SIZE = 100;
	public static final int PLAYING_ROWS = 3;
	private CheckerTile[][] tile = new CheckerTile[MAX_TILES][MAX_TILES];
	
	// constructor
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
	public void addPiece(CheckerPiece piece, Color player, int row, int col)
	{
		// place piece on tile
		tile[row][col].add(piece, BorderLayout.CENTER);
		tile[row][col].repaint();
		piece.repaint();
		tile[row][col].setOccupied(true, player);
		
		piece.addActionListener(this);
	}
	
	// remove piece from board
	public void removePiece(CheckerPiece piece, int row, int col)
	{
		// remove piece from tile
		tile[row][col].remove(piece);
		tile[row][col].repaint();
		tile[row][col].setOccupied(false, null);
		
		if(piece.getActionListeners().length > 0) 
		{
	        for(ActionListener g : piece.getActionListeners()) {
	            piece.removeActionListener(g);
	        }
	    }
		
		deSelectTile (row, col);
	}
	
    // check the owner/player of a given tile (with a piece on it or not, i.e. occupied) 
	public int getTileOwner (int row, int col)
	{		
		// check out of bound
		if (row < 0 || row > MAX_TILES-1 || col < 0 || col > MAX_TILES-1)
			return -1;
		
		CheckerTile t = tile[row][col];
		
		if (t.getOccupied()==false)
			return 0; 
		else if (t.getPlayer() == Color.WHITE)
			return 1;
		else
			return 2; // player is ORANGE
		
	}
	
	// set tile border to highlight selection
	public void selectTile(int row, int col)
	{
		tile[row][col].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		tile[row][col].repaint();
	}
	
	// set tile border to highlight selection
	public void selectTileTEST(int row, int col)
	{
		tile[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN));
		tile[row][col].repaint();
	}
		
	// clear tile border to highlight de-selection
	public void deSelectTile(int row, int col)
	{
		tile[row][col].setBorder(BorderFactory.createEmptyBorder());
		tile[row][col].repaint();
	}
	
	// button click action, i.e. a piece is clicked or selected
	public void actionPerformed (ActionEvent e)
	{
		CheckerPiece piece = (CheckerPiece) e.getSource();
		
		//test code
		String str = "WHITE";
		if (piece.getPlayer() == Color.ORANGE)
			str = "ORANGE";
		//test code
		
		System.out.print ("piece " + str + " (" + piece.getRow() + ", " + piece.getCol() + ") is selected");
		if (piece.getPlayer() == Checker.getCurrentPlayer())
		{
			int row = piece.getRow();
			int col = piece.getCol();

			// get current player
			CheckerPlayer player = Checker.getPlayer(piece.getPlayer());
			boolean limitSelect = player.getLimitSelection();
			
			System.out.println(", limitSelect " + limitSelect + "  piece pre-select " + piece.getPreSelect() + " captureinprogress " + player.getCaptureInProgress());
			// test code
			ArrayList<CheckerPiece> preSelectList = player.getPlayerPreSelectArrayList();
			System.out.print ("Pre-select list: ");
			for (CheckerPiece piece1 : preSelectList)
			{
				System.out.print(piece1.getLabel() + ", ");
			}
			System.out.println("");
			// test code
			
			// don't allow piece switching when capture is in progress
			// and, if this piece is pre-selected for next move/action 
			if (!player.getCaptureInProgress() && 
				(!limitSelect || (limitSelect && piece.getPreSelect())))
			{
				// get last selected piece (if any) by current player and de-select the component
				CheckerPiece lastSelectedPiece = player.getLastSelectedPiece();
				deSelectTile(lastSelectedPiece.getRow(), lastSelectedPiece.getCol());
			
				// select current piece clicked by player 
				player.selectPiece(row, col);
				selectTile(row, col);
			}
			
		}
		System.out.println(" ");
		
	}


}

