import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class CheckerTile extends JPanel implements MouseListener {
	
	private boolean valid, occupied;
	private Color player = null;
	private int row,col;
	
	public CheckerTile(int row, int col)
	{
		super();
		this.valid = false;
		this.occupied = false;
		this.player = null;
		this.row = row;
		this.col = col;	
		addMouseListener(this);
	}
	
	public void setValid (boolean v)
	{
		this.valid = v;
	}
	
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
	
	public boolean getValid()
	{
		return this.valid;
	}
	
	public Color getPlayer()
	{
		return this.player;
	}
	
	public boolean getOccupied()
	{
		return this.occupied;
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getCol()
	{
		return this.col;
	}
	
	public void mouseClicked(MouseEvent e) 
	{ 
		CheckerTile tile = (CheckerTile) e.getSource();
		System.out.println("tile [" + tile.getCol() + ", " + tile.getRow() + "], occupied " + tile.getOccupied() + ", by " + tile.getPlayer() + ", valid " + tile.getValid());

    }

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
