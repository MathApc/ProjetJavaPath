package plateau;

public class Box {
	private boolean isPawn;
	private Pawn pawn;
	private boolean isBarrierLeft; // defines if the neighbors of the box are accessible or not, if true , that means that there is a barrier							
	private boolean isBarrierRight;
	private boolean isBarrierTop;
	private boolean isBarrierBot;

	public boolean isBarrierLeft() {
		return isBarrierLeft;
	}

	public void setLeft(boolean left) {
		this.isBarrierLeft = left;
	}

	public boolean isBarrierRight() {
		return isBarrierRight;
	}

	public void setRight(boolean right) {
		this.isBarrierRight = right;
	}

	public boolean isBarrierTop() {
		return isBarrierTop;
	}

	public void setTop(boolean top) {
		this.isBarrierTop = top;
	}

	public boolean isBarrierBot() {
		return isBarrierBot;
	}

	public void setBot(boolean bot) {
		this.isBarrierBot = bot;
	}

	public Box(boolean isPawn, Pawn pawn) {
			this.isPawn = false;
			this.pawn = null;

	}

	public Box() {
		this(false,null);
	}
	
	public boolean isPawn() {
		return isPawn;
	}

	public void setPawn(boolean isPawn) {
		this.isPawn = isPawn;
	}

	public Pawn getPawn() {
		return pawn;
	}

	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}

	
	
}
