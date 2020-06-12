import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class Checker 
{
	private static CheckerPlayer playerW, playerY;
	private static CheckerBoard board;
	private static Color currentPlayer = Color.ORANGE;
	
	// constructor
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
	
	// change turn of the play
	public static void turnOver()
	{
		if (currentPlayer == Color.WHITE)
			currentPlayer = Color.ORANGE;
		else
			currentPlayer = Color.WHITE;
		
	}
	
	// get the player's instance
	public static CheckerPlayer getPlayer (Color color)
	{
		if (color == Color.WHITE)
			return playerW;
		else
			return playerY;
	}
	
	// get current player in turn
	public static Color getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	// get opponent player in turn
	public static Color getOpponentPlayer()
	{
		if (currentPlayer == Color.WHITE)
			return Color.ORANGE;
		else
			return Color.WHITE;
	}
	
	// set current player
	public static void setCurrentPlayer(Color color)
	{
		currentPlayer = color;
	}
	
	// get board's instance
	public static CheckerBoard getBoard()
	{
		return board;
	}
	
	// main, entry point
	public static void main(String[] args) {
		new Checker();

	}

}
