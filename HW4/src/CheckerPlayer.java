import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;

public class CheckerPlayer 
{
	private Color color;
	private ArrayList<CheckerComponent> pieces = new ArrayList<CheckerComponent>();
	
	public CheckerPlayer(Color color)
	{
		this.color = color;
		
		// white color player
		if (color == Color.WHITE)
		{
			for (int row=0; row<3; row++)
		    {
				//create and add piece to board and player array list
				addPiece (color, row);
		    }	
		}
		// orange color player
		else
		{
			for (int row=5; row<CheckerBoard.MAX_TILES; row++)
		    {
				//create and add piece to board and player array list
				addPiece (color, row);
		    }	
		}
	}
	
	public void addPiece (Color color, int row)
	{
		int col = row % 2;
    	while (col < CheckerBoard.MAX_TILES)
		{
    		// create new piece
    		CheckerComponent piece = new CheckerComponent(color,row,col);
    		
    		// add piece to the checker board
    		CheckerBoard board = Checker.getBoard();
    		board.addPiece(piece, color, row, col);
		
    		// add piece to player array list
    		pieces.add(piece);
    		col+=2;
		}
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
				break;
			}
		}
	}
}
