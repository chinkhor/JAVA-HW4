import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;

public class CheckerPlayer 
{
	private Color player;
	private boolean pieceSelected = false;
	private ArrayList<CheckerPiece> pieces = new ArrayList<CheckerPiece>();
	private int kingRow;
	private boolean captureInProgress = false;
	
	// constructor
	public CheckerPlayer(Color player)
	{
		this.player = player;
		
		// white color player
		if (player == Color.WHITE)
		{
			for (int row=0; row<CheckerBoard.PLAYING_ROWS; row++)
		    {
				//create and add piece to board and player array list
				addPiece (player, row);
		    }	
			kingRow = CheckerBoard.MAX_TILES - 1;
		}
		// orange color player
		else
		{
			for (int row=CheckerBoard.MAX_TILES-CheckerBoard.PLAYING_ROWS; row<CheckerBoard.MAX_TILES; row++)
		    {
				//create and add piece to board and player array list
				addPiece (player, row);
		    }	
			kingRow = 0;
		}
	}
	
	// add pieces to board
	public void addPiece (Color player, int row)
	{
		int col = row % 2;
    	while (col < CheckerBoard.MAX_TILES)
		{
    		// create new piece
    		CheckerPiece piece = new CheckerPiece(player,row,col, false);
    		
    		// add piece to the checker board
    		CheckerBoard board = Checker.getBoard();
    		board.addPiece(piece, player, row, col);
		
    		// add piece to player array list
    		pieces.add(piece);
    		col+=2;
		}
	}
	
	// check if player already select his piece for next action
	public boolean isPieceSelected()
	{
		return pieceSelected;
	}
	
	// player to make a move
	public void move(CheckerPiece piece, int row, int col)
	{
		Color player = piece.getPlayer();
		boolean crown = false;
		
		// remove piece
		CheckerBoard board = Checker.getBoard();
		board.removePiece(piece, piece.getRow(), piece.getCol());
		pieces.remove(piece);
		
		if ((row == kingRow) || piece.getCrown())
			crown = true;
			
		// create new piece
		CheckerPiece newpiece = new CheckerPiece(player,row,col, crown);
		
		// add piece to the checker board
		board.addPiece(newpiece, player, row, col);
		pieces.add(0, newpiece);
	}
	
	// player to capture opponent piece
	public void capture (int row, int col)
	{
		CheckerPlayer player = Checker.getPlayer(Checker.getOpponentPlayer());
		CheckerPiece piece = player.getPiece(row,  col);
		
		if (piece != null)
		{
			// remove piece
			CheckerBoard board = Checker.getBoard();
			board.removePiece(piece, piece.getRow(), piece.getCol());
			pieces.remove(piece);
		}
	}
	
	// player to check if a move is valid
	public boolean checkForValidMove (int srcRow, int srcCol, int dstRow, int dstCol)
	{
		// valid move
		// orange player at bottom, valid moves are up (or -1 in row and +/- 1 in col)
		if (this.player == Color.ORANGE)
		{
			return (((dstRow - srcRow) == -1) && (Math.abs(dstCol - srcCol) == 1));
		}
		// white player at top, valid moves are down (or 1 in row and +/- 1 in col)
		else
		{
			return (((dstRow - srcRow) == 1) && (Math.abs(dstCol - srcCol) == 1));
		}
	}
				
	// player to check if can fly and capture
	// return -1: cannot fly
	// return 0: can fly, but no capture
	// return 1: can fly and capture
	public int checkForValidFlyCapture (int srcRow, int srcCol, int dstRow, int dstCol)
	{
		CheckerBoard board = Checker.getBoard();
		int opponentRow = 0;
		int opponentCol = 0;
		int opponentCount = 0;
		
		// fly diagonally
		if (Math.abs(dstRow - srcRow) == Math.abs(dstCol - srcCol))
		{
			// check any obstacle piece along the fly
			int incrementRow = (dstRow - srcRow)/Math.abs(dstRow - srcRow);
			int incrementCol = (dstCol - srcCol)/Math.abs(dstCol - srcCol);
			int row = srcRow;
			int col = srcCol;
			System.out.println("increment " + incrementRow + " and " + incrementCol);
			
			// since this is diagonal fly, just check for row (col will increment accordingly too)
			while (row != dstRow)
			{
				row += incrementRow;
				col += incrementCol;
				Color player = Color.black; // dummy initialization
				int status = board.getTileOwner(row, col);
				if (status == 1)
					player = Color.white;
				else if (status == 2)
					player = Color.orange;
				
				if (player == Checker.getCurrentPlayer()) // blocked by own's piece
					return -1;
				else if (player == Checker.getOpponentPlayer()) // potential capture 
				{
					if (opponentCount > 0) // two opponent pieces along the fly
						return -1;
					
					opponentCount++;
					opponentRow = row;
					opponentCol = col;
				}	
			}
			
			// if get here, either no obstacle or can capture
			if (opponentCount == 1)
			{
				capture (opponentRow, opponentCol);
				return 1;
			}
			else
			{	
				// if captureInProgress, prohibit subsequent fly without capture
				if (captureInProgress)
					return -1;
				else
					return 0;
			}
		}
		
		return -1;
	}
		
	// player to check can make a jump to capture, this check will follow by a capture test
	public boolean checkForValidJump(int srcRow, int srcCol, int dstRow, int dstCol)
	{
		// orange player at bottom, valid jumps are up (or -2 in row and +/- 2 in col)
		if (this.player == Color.ORANGE)
		{
			// valid jump
			return (((dstRow - srcRow) == -2) && (Math.abs(dstCol - srcCol) == 2));
		}
		// white player at top, valid jumps are down (or 2 in row and +/- 2 in col)
		else
		{
			// valid move
			return (((dstRow - srcRow) == 2) && (Math.abs(dstCol - srcCol) == 2));
		}
	}

	public boolean continuousCaptureCheck (int srcRow, int srcCol)
	{
		CheckerBoard board = Checker.getBoard();
		int status1 = -1; 
		int status2 = -1;
		
		if (this.player == Color.ORANGE)
		{
			status1 = board.getTileOwner(srcRow-1, srcCol-1);
			status2 = board.getTileOwner(srcRow-2, srcCol-2);
			if ((status1 == 1) && (status2 == 0)) // status1: 1 = WHITE (opponent), status2 = 0 (un-occupied)
				return true;
					
			status1 = board.getTileOwner(srcRow-1, srcCol+1);
			status2 = board.getTileOwner(srcRow-2, srcCol+2);
			if ((status1 == 1) && (status2 == 0)) // status1: 1 = WHITE (opponent), status2 = 0 (un-occupied)
				return true;
		}
		else // this player is WHITE
		{
			status1 = board.getTileOwner(srcRow+1, srcCol-1);
			status2 = board.getTileOwner(srcRow+2, srcCol-2);
			if ((status1 == 2) && (status2 == 0)) // status1: 2 = ORANGE (opponent), status2 = 0 (un-occupied)
				return true;
					
			status1 = board.getTileOwner(srcRow+1, srcCol+1);
			status2 = board.getTileOwner(srcRow+2, srcCol+2);
			if ((status1 == 2) && (status2 == 0)) // status1: 2 = ORANGE (opponent), status2 = 0 (un-occupied)
				return true;
		}
		// no possible capture
		return false;
	}
	
	public boolean continuousFlyCaptureCheck (int srcRow, int srcCol, int incrementRow, int incrementCol)
	{
		CheckerBoard board = Checker.getBoard();
		int row = srcRow + incrementRow;
		int col = srcCol + incrementCol;
		int opponentCount = 0;
		boolean occupied = false;
		boolean outBoardBoundaryHit = false;
		Color player = Color.BLACK;
		
		while (true)
		{
			int status = board.getTileOwner(row, col);
			
			switch (status)
			{
				case 0:
				{
					occupied = false;
					player = Color.black;
					break;
				}
				case 1:
				{
					occupied = true;
					player = Color.white;
					break;
				}
				case 2:
				{
					occupied = true;
					player = Color.orange;
					break;
				}
				default: // out of board boundary, status = -1
					outBoardBoundaryHit = true;
					player = Color.black;
					break;
			}
			
			System.out.println("flycheck (" + incrementRow + "," + incrementCol + ") : row " + row + " col " + col + " status " + status);
			
			// potential capture, move on to check next tile
			if (player == Checker.getOpponentPlayer())
			{
				++opponentCount;
			}
			// out of board boundary, or two consecutive opponent pieces, or block by own's piece
			if (outBoardBoundaryHit || (opponentCount > 1) || (player == Checker.getCurrentPlayer()))
				return false;
			// potential capture scenario
			else if ((opponentCount ==1) && (occupied == false))
				return true;
			
			row += incrementRow;
			col += incrementCol;
		}

	}
	
	// notify player after mouse click with a piece is selected for next action
	public void actionNotify(int dstRow, int dstCol)
	{
		CheckerBoard board = Checker.getBoard();
		
		if (pieceSelected)
		{
			CheckerPiece selectedPiece = getLastSelectedPiece();
				
			int srcRow = selectedPiece.getRow();
			int srcCol = selectedPiece.getCol();
			System.out.println("Selected piece " + srcRow + ", " + srcCol + " to " + dstRow + ", " + dstCol);
			
			if (selectedPiece.getCrown())
			{
				int status = checkForValidFlyCapture(srcRow, srcCol, dstRow, dstCol);
		
				if ( status >= 0)
				{
					// valid fly
					move(selectedPiece, dstRow, dstCol);
					
					// already capture, check for continuous action for fly and capture
					if ((status > 0) &&
						(continuousFlyCaptureCheck(dstRow, dstCol,1,1)  ||
						 continuousFlyCaptureCheck(dstRow, dstCol,1,-1) ||
						 continuousFlyCaptureCheck(dstRow, dstCol,-1,1) ||
						 continuousFlyCaptureCheck(dstRow, dstCol,-1,-1)))
					{
						selectPiece(dstRow, dstCol);
						board.selectTile(dstRow, dstCol);
						captureInProgress = true;
					}
					else
					{
						captureInProgress = false;
						pieceSelected = false;
						Checker.turnOver();
					}
				}
			}
			else
			{
				if (!captureInProgress && checkForValidMove(srcRow, srcCol, dstRow, dstCol))
				{
					// valid move
					move(selectedPiece, dstRow, dstCol);
					pieceSelected = false;
					Checker.turnOver();
					return;
				}
			
				boolean validJump = checkForValidJump(srcRow, srcCol, dstRow, dstCol);
				if (validJump)
				{
					int midRow = (dstRow - srcRow)/2 + srcRow;
					int midCol = (dstCol - srcCol)/2 + srcCol;
					
				
					// check for valid capture
					int status = board.getTileOwner(midRow, midCol);
					Color player = Color.black; // dummy initialization
					if (status == 1)
						player = Color.white;
					else if (status == 2)
						player = Color.orange;
						
					if (player == Checker.getOpponentPlayer())
					{
						move(selectedPiece, dstRow, dstCol);
						capture(midRow, midCol);
						captureInProgress = true;
					
						// check for continuous action for capture
						if (continuousCaptureCheck(dstRow, dstCol))
						{
							selectPiece(dstRow, dstCol);
							board.selectTile(dstRow, dstCol);
						}
						else
						{
							captureInProgress = false;
							pieceSelected = false;
							Checker.turnOver();
						}
						return;
					}
				}
			}
		}
		else
			System.out.println("No piece is selected yet");
	}
	
	// return player last selected piece for action
	public CheckerPiece getLastSelectedPiece()
	{
		// get the first piece in the arraylist
		// note: the last selected piece will be always moved to index 0
		return pieces.get(0);
	}
	
	// player action in selecting a piece, can switch piece if not yet making an action
	public void selectPiece(int row, int col)
	{
		String label = CheckerPiece.constructLabel(row,  col);
		for (CheckerPiece piece : pieces)
		{
			if (label.equals(piece.getLabel()))
			{
				int index = pieces.indexOf(piece);
				CheckerPiece extract = pieces.remove(index);
				pieces.add(0,  extract);
				pieceSelected = true;
				break;
			}
		}
	}
	
	// player to get a piece, return with its piece component
	public CheckerPiece getPiece(int row, int col)
	{
		String label = CheckerPiece.constructLabel(row,  col);
		for (CheckerPiece piece : pieces)
		{
			if (label.equals(piece.getLabel()))
			{
				return piece;
			}
		}
		return null;
	}
	
	public boolean getCaptureInProgress()
	{
		return this.captureInProgress;
	}
}
