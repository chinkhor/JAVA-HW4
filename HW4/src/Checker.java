import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Checker implements ActionListener{
	
	private  CheckerTile[][] tile = new CheckerTile[8][8];
	
	public Checker()
	{
		JFrame frame = new JFrame();
		frame.setTitle("Checker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		
		Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(8,8));
        
        int count = 0;
        for (int i=0; i<8; i++)
        {
        	count++;
  			for (int j=0; j<8; j++)
  			{
  				count++;
  				tile[i][j] = new CheckerTile(i,j);
  				if (count%2!=0)
  					tile[i][j].setBackground(Color.RED);
  				else
  				{
  					tile[i][j].setBackground(Color.BLACK);
  					tile[i][j].setValid(true);
  				}
  				tile[i][j].setPreferredSize(new Dimension(100,100));
  				tile[i][j].setLayout(new BorderLayout());
  				
  				pane.add(tile[i][j]);
  			}
        }
        
        CheckerPlayer playerW = new CheckerPlayer();
        for (int i=0; i<3; i++)
        {
        	int j = i % 2;
        	while (j < 8)
  			{
        		CheckerComponent piece = new CheckerComponent(Color.WHITE,i,j);
        		piece.addActionListener(this);
        		tile[i][j].add(piece, BorderLayout.CENTER);
        		
        		playerW.add(piece);
        		tile[i][j].setOccupied(true, Color.WHITE);
  				j+=2;
  			}
        }
		
        CheckerPlayer playerY = new CheckerPlayer();
        for (int i=5; i<8; i++)
        {
        	int j = i % 2;
        	while (j < 8)
  			{
        		CheckerComponent piece = new CheckerComponent(Color.ORANGE,i,j);
        		piece.addActionListener(this);
        		tile[i][j].add(piece, BorderLayout.CENTER);
        		playerY.add(piece);
        		tile[i][j].setOccupied(true, Color.ORANGE);
  				j+=2;
  			}
        }
        
		frame.setVisible(true);
	}

		
	public void actionPerformed (ActionEvent e)
	{
		CheckerComponent button = (CheckerComponent) e.getSource();
		tile[button.getRow()][button.getCol()].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		System.out.println("button [" + button.getCol() + ", " + button.getRow() + "], player " + button.getPlayer());
	}
	
	
	public static void main(String[] args) {
		new Checker();

	}

}
