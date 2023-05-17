package plateau;

public class IncorrectNumerPlayersException extends Exception{
	private String track;	// track represents the history of the  thrown Exceptions
	
	public IncorrectNumerPlayersException(String msg) {
		this.track = msg + "\n" ; 
	}
	
	public String toString() {
		return super.toString()+track;
	}
	
	public void addTrack(String msg) {
		this.track += msg + "\n";
	}
}
