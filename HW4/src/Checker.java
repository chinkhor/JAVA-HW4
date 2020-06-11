import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Checker {
	
	public Checker()
	{
		JFrame frame = new JFrame();
		frame.setTitle("Checker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		
		Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(8,8));
        
        int count = 0;
        for (int i=0; i<8; i++)
        {
        	count++;
  			for (int j=0; j<8; j++)
  			{
  				JPanel panel = new JPanel();
  				count++;
  				if (count%2!=0)
  					panel.setBackground(Color.RED);
  				else 
  					panel.setBackground(Color.BLACK);
  				panel.setPreferredSize(new Dimension(50,50));
  				pane.add(panel);
  			}
        }
		
		frame.setVisible(true);
	}

	
	public static void main(String[] args) {
		new Checker();

	}

}
