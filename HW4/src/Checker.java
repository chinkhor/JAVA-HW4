import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Checker {
	
	public Checker()
	{
		JFrame frame = new JFrame();
		frame.setTitle("Checker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		
		Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(8,8));
        
        JPanel[][] panel = new JPanel[8][8];
        int count = 0;
        for (int i=0; i<8; i++)
        {
        	count++;
  			for (int j=0; j<8; j++)
  			{
  				count++;
  				panel[i][j] = new JPanel();
  				if (count%2!=0)
  					panel[i][j].setBackground(Color.RED);
  				else 
  					panel[i][j].setBackground(Color.BLACK);
  				panel[i][j].setPreferredSize(new Dimension(100,100));
  				panel[i][j].setLayout(new BorderLayout());
  				pane.add(panel[i][j]);
  			}
        }
        
        ArrayList<CheckerComponent> playerW = new ArrayList<CheckerComponent>();
        for (int i=0; i<3; i++)
        {
        	int j = i % 2;
        	while (j < 8)
  			{
        		CheckerComponent piece = new CheckerComponent(Color.WHITE,i,j);
        		panel[i][j].add(piece, BorderLayout.CENTER);
        		playerW.add(piece);
  				j+=2;
  			}
        }
		
        ArrayList<CheckerComponent> playerY = new ArrayList<CheckerComponent>();
        for (int i=5; i<8; i++)
        {
        	int j = i % 2;
        	while (j < 8)
  			{
        		CheckerComponent piece = new CheckerComponent(Color.ORANGE,i,j);
        		panel[i][j].add(piece, BorderLayout.CENTER);
        		playerY.add(piece);
  				j+=2;
  			}
        }
        
		frame.setVisible(true);
	}

	
	public static void main(String[] args) {
		new Checker();

	}

}
