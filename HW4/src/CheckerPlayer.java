import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;

public class CheckerPlayer 
{
	private boolean myturn;
	private Color color;
	private ArrayList<CheckerComponent> pieces = new ArrayList<CheckerComponent>();
	
	public CheckerPlayer(Color color)
	{
		myturn = false;
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
	
	
	
}
