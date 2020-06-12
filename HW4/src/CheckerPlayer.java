import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;

public class CheckerPlayer 
{
	private Color player;
	private boolean pieceSelected = false;
	private ArrayList<CheckerComponent> pieces = new ArrayList<CheckerComponent>();
	
	public CheckerPlayer(Color player)
	{
		this.player = player;
		
		// white color player
		if (player == Color.WHITE)
		{
			for (int row=0; row<3; row++)
		    {
				//create and add piece to board and player array list
				addPiece (player, row);
		    }	
		}
		// orange color player
		else
		{
			for (int row=5; row<CheckerBoard.MAX_TILES; row++)
		    {
				//create and add piece to board and player array list
				addPiece (player, row);
		    }	
		}
	}
	
	public void addPiece (Color player, int row)
	{
		int col = row % 2;
    	while (col < CheckerBoard.MAX_TILES)
		{
    		// create new piece
    		CheckerComponent piece = new CheckerComponent(player,row,col);
    		
    		// add piece to the checker board
    		CheckerBoard board = Checker.getBoard();
    		board.addPiece(piece, player, row, col);
		
    		// add piece to player array list
    		pieces.add(piece);
    		col+=2;
		}
	}
	
	
	public boolean isPieceSelected()
	{
		return pieceSelected;
	}
	
	public void move(CheckerComponent piece, int row, int col)
	{
		Color player = piece.getPlayer();
		
		// remove piece
		CheckerBoard board = Checker.getBoard();
		board.removePiece(piece, piece.getRow(), piece.getCol());
		pieces.remove(piece);
		
		// create new piece
		CheckerComponent newpiece = new CheckerComponent(player,row,col);
		
		// add piece to the checker board
		board.addPiece(newpiece, player, row, col);
		pieces.add(0, newpiece);
	}
	
	public void capture (int row, int col)
	{
		System.out.println("capture : " + row + ", " + col);
		CheckerPlayer player = Checker.getPlayer(Checker.getOpponentPlayer());
		CheckerComponent piece = player.getPiece(row,  col);
		
		if (piece != null)
		{
			System.out.println("capture found piece : ");
			// remove piece
			CheckerBoard board = Checker.getBoard();
			board.removePiece(piece, piece.getRow(), piece.getCol());
			pieces.remove(piece);
		}
	}
	
	public boolean checkForValidMove(int srcRow, int srcCol, int dstRow, int dstCol)
	{
		// orange player at bottom, valid moves are up (or -1 in row and +/- 1 in col)
		if (this.player == Color.ORANGE)
		{
			// valid move
			if (((dstRow - srcRow) == -1) && (Math.abs(dstCol - srcCol) == 1))
			{
				return true;
			}
			else
				return false;
		}
		// white player at top, valid moves are down (or 1 in row and +/- 1 in col)
		else
		{
			// valid move
			if (((dstRow - srcRow) == 1) && (Math.abs(dstCol - srcCol) == 1))
			{
				return true;
			}
			else
				return false;
		}
	}
	
	public boolean checkForValidJump(int srcRow, int srcCol, int dstRow, int dstCol)
	{
		// orange player at bottom, valid jumps are up (or -2 in row and +/- 2 in col)
		if (this.player == Color.ORANGE)
		{
			// valid jump
			if (((dstRow - srcRow) == -2) && (Math.abs(dstCol - srcCol) == 2))
			{
				return true;
			}
			else
				return false;
		}
		// white player at top, valid jumps are down (or 2 in row and +/- 2 in col)
		else
		{
			// valid move
			if (((dstRow - srcRow) == 2) && (Math.abs(dstCol - srcCol) == 2))
			{
				return true;
			}
			else
				return false;
		}
	}
	
	public void actionNotify(int dstRow, int dstCol)
	{
		if (pieceSelected)
		{
			CheckerComponent selectedPiece = getLastSelectedPiece();
				
			int srcRow = selectedPiece.getRow();
			int srcCol = selectedPiece.getCol();
			System.out.println("Selected piece " + srcRow + ", " + srcCol + " to " + dstRow + ", " + dstCol);
			
			boolean validMove = checkForValidMove(srcRow, srcCol, dstRow, dstCol);
			if (validMove)
			{
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
				CheckerBoard board = Checker.getBoard();
				
				// check for valid capture
				if (board.getTileOwner(midRow, midCol) == Checker.getOpponentPlayer())
				{
					move(selectedPiece, dstRow, dstCol);
					capture(midRow, midCol);
					pieceSelected = false;
					Checker.turnOver();
					return;
				}
			}
			
		}
		else
			System.out.println("No piece is selected yet");
	}
	
	public CheckerComponent getLastSelectedPiece()
	{
		// get the first piece in the arraylist
		// note: the last selected piece will be always moved to index 0
		return pieces.get(0);
	}
	
	public void selectPiece(int row, int col)
	{
		String label = CheckerComponent.constructLabel(row,  col);
		for (CheckerComponent piece : pieces)
		{
			if (label.equals(piece.getLabel()))
			{
				int index = pieces.indexOf(piece);
				CheckerComponent extract = pieces.remove(index);
				pieces.add(0,  extract);
				pieceSelected = true;
				break;
			}
		}
	}
	
	public CheckerComponent getPiece(int row, int col)
	{
		String label = CheckerComponent.constructLabel(row,  col);
		for (CheckerComponent piece : pieces)
		{
			if (label.equals(piece.getLabel()))
			{
				return piece;
			}
		}
		return null;
	}
}
