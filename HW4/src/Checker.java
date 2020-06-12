import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class Checker 
{
	private static CheckerPlayer playerW, playerY;
	private static CheckerBoard board;
	private static Color currentPlayer = Color.ORANGE;
	
	public Checker()
	{
		int tiles = CheckerBoard.MAX_TILES;
		int width =  tiles * CheckerBoard.TILE_SIZE;
		int length = tiles * CheckerBoard.TILE_SIZE;
		
		// create frame
		JFrame frame = new JFrame();
		frame.setTitle("Checker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(length, width);
		
		Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(tiles,tiles));
        
        // create and draw Checker Board
        board = new CheckerBoard(pane);
        
        // create and add players
        playerW = new CheckerPlayer(Color.WHITE);
        playerY = new CheckerPlayer(Color.ORANGE);
        
		frame.setVisible(true);
	}
	
	public static CheckerPlayer getPlayer (Color color)
	{
		if (color == Color.WHITE)
			return playerW;
		else
			return playerY;
	}
	
	public static Color getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	public static void setCurrentPlayer(Color color)
	{
		currentPlayer = color;
	}
	
	public static CheckerBoard getBoard()
	{
		return board;
	}
	
	public static void main(String[] args) {
		new Checker();

	}

}
