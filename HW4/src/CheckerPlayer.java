import java.util.ArrayList;

public class CheckerPlayer {
	
	private boolean myturn;
	private ArrayList<CheckerComponent> pieces;
	
	public CheckerPlayer()
	{
		myturn = false;
		pieces = new ArrayList<CheckerComponent>();
	}
	
	public void add (CheckerComponent piece)
	{
		pieces.add(piece);
	}
	

}
