import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
@SuppressWarnings("serial")

public class CheckerTile extends JPanel implements MouseListener
{
	private boolean valid, occupied;
	private Color player = null;
	private int row,col;
	
	// constructor
	public CheckerTile (int row, int col)
	{
		super();
		this.valid = false;
		this.occupied = false;
		this.player = null;
		this.row = row;
		this.col = col;	
		addMouseListener(this);
	}
	
	// set the tile is validity for game
	public void setValid (boolean v)
	{
		this.valid = v;
	}
	
	// set the occupancy of the tile, and by which player, represented by color code (white or orange)
	public void setOccupied (boolean occupied, Color player)
	{		
		this.occupied = occupied;
		if (this.occupied)
		{
			this.player = player;
		}
		else
		{
			this.player = null;
		}
	}
	
	// check if the tile is valid for game
	public boolean getValid()
	{
		return this.valid;
	}
	
	// get the player who owns the tile
	public Color getPlayer()
	{
		return this.player;
	}
	
	// check if the tile is occupied
	public boolean getOccupied()
	{
		return this.occupied;
	}
	
	// get the tile row position
	public int getRow()
	{
		return this.row;
	}
	
	// get the tile column position
	public int getCol()
	{
		return this.col;
	}
	
	// mouse click event on the unoccupied title
	// note: mouse click on occupied tile will generate button click event, see CheckerBoard
	public void mouseClicked(MouseEvent e) 
	{ 
		CheckerTile tile = (CheckerTile) e.getSource();
		
		/* ignore if the selected tile is not valid or not movable to */
		if (!tile.getValid())
			return;
		
		CheckerPlayer player = Checker.getPlayer(Checker.getCurrentPlayer());
		player.actionNotify(tile.getRow(), tile.getCol());
		
    }

	// the following events are dummy, not use.
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 

}

