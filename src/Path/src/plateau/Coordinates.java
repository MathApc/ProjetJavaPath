package plateau;

public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates(int x, int y) {
		if(x>=-1||y>=-1) {
			this.x=x;
			this.y=y;
		}
	}
	public Coordinates() {
		this(-1,-1);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}
