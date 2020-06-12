import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;

public class CheckerPlayer 
{
	private Color player;
	private boolean select = false;
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
		return select;
	}
	
	public void move(CheckerComponent piece, int row, int col)
	{
		CheckerBoard board = Checker.getBoard();
		Color player = piece.getPlayer();
		board.removePiece(piece, piece.getRow(), piece.getCol());
		pieces.remove(piece);
		
		// create new piece
		CheckerComponent newpiece = new CheckerComponent(player,row,col);
		
		// add piece to the checker board
		board.addPiece(newpiece, player, row, col);
		pieces.add(0, newpiece);
	}
	
	public boolean checkForValidMove(int curRow, int curCol, int nextRow, int nextCol)
	{
		// orange player at bottom, valid moves are up (or -1 in row and +/- in col)
		if (this.player == Color.ORANGE)
		{
			System.out.println("nextrow - currow : " + (nextRow - curRow) + " Math.abs(nextCol - curCol): " + Math.abs(nextCol - curCol));
			// valid move
			if (((nextRow - curRow) == -1) && (Math.abs(nextCol - curCol) == 1))
			{
				return true;
			}
			else
				return false;
		}
		// white player at top, valid moves are down (or 1 in row and +/- in col)
		else
		{
			// valid move
			if (((nextRow - curRow) == 1) && (Math.abs(nextCol - curCol) == 1))
			{
				return true;
			}
			else
				return false;
		}
	}
	
	
	public void tileSelectionNotify(int row, int col)
	{
		if (select)
		{
			CheckerComponent selectedPiece = getLastSelectedPiece();
			System.out.println("Selected piece " + selectedPiece.getRow() + ", " + selectedPiece.getCol() + " to " + row + ", " + col);
			boolean validMove = checkForValidMove(selectedPiece.getRow(), selectedPiece.getCol(), row, col);
			System.out.println("valid move: " + validMove);
			if (validMove)
			{
				move(selectedPiece, row, col);
				select = false;
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
				select = true;
				break;
			}
		}
	}
}
