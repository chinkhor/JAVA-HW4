import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Checker 
{
	private static CheckerPlayer playerW, playerY;
	private static CheckerBoard board;
	private static Color currentPlayer = Color.ORANGE;
	private static JLabel label;
	
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
		
		label = new JLabel("Current Player: ORANGE  ", SwingConstants.RIGHT);
		frame.add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(tiles,tiles));
		
		//Container pane = frame.getContentPane();
        //pane.setLayout(new GridLayout(tiles,tiles));
        
        // create and draw Checker Board
        board = new CheckerBoard(panel);
        
        // create and add players
        playerW = new CheckerPlayer(Color.WHITE);
        playerY = new CheckerPlayer(Color.ORANGE);
        
		frame.setVisible(true);
	}
	
	// change turn of the play
	public static void turnOver()
	{
		if (currentPlayer == Color.WHITE)
		{
			currentPlayer = Color.ORANGE;
			label.setText("Current Player: ORANGE  ");
			System.out.println("Turn over to Player ORANGE now");
		}
		else
		{
			currentPlayer = Color.WHITE;
			label.setText("Current Player: WHITE  ");
			System.out.println("Turn over to Player WHITE now");
		}
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
