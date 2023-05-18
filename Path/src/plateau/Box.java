package plateau;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Box extends Button{
	
	static String[] Colors = {"red", "blue", "green", "yellow"};
	
	private boolean isPawn = false;
	private Plateau pl;
	private Pawn pawn = null;
	private int x,y;
	private boolean isBarrierLeft = false; // defines if the neighbors of the box are accessible or not, if true , that means that there is a barrier							
	private boolean isBarrierRight = false;
	private boolean isBarrierTop = false;
	private boolean isBarrierBot = false;
	
	private boolean canClick = false;
	
	public Box(int x, int y, Plateau pl) {
		this.x = x;
		this.y = y;
		this.pl = pl;
		
		this.setOnAction(new BtnHandler());
		this.setStyle("-fx-background-color: gray;");
		this.setMinSize(50, 50);
		this.setMaxSize(50, 50);
	}
	
	class BtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			if(getCanClick() && pl.canClickBox()) {
				System.out.println(String.valueOf(x)+","+String.valueOf(y));
				Pawn pawn = pl.getPawn(pl.getTurn());
				setPawn(true);
				setPawn(pawn);
				pawn.getBox().setPawn(false);
				pawn.getBox().setPawn(null);
				pawn.setBox(getThisBox());
				
				if(pawn.getTargetRow() == getx() || pawn.getTargetColumn() == gety()) { //check if arrived to the target row or column
					pl.setGameOver(true);
					pl.setWinner(pawn);
				}
				
				pl.reset();
			}
			
		}
		
	}
	
	public Box getThisBox() {
		return this;
	}
	
	public int getx(){
		return x;
	}
	
	public int gety(){
		return y;
	}

	public boolean isBarrierLeft() {
		return isBarrierLeft;
	}

	public void setLeft(Boolean isBarrier) {
		this.isBarrierLeft = isBarrier;
	}

	public boolean isBarrierRight() {
		return isBarrierRight;
	}

	public void setRight(Boolean isBarrier) {
		this.isBarrierRight = isBarrier;
	}

	public boolean isBarrierTop() {
		return isBarrierTop;
	}

	public void setTop(Boolean isBarrier) {
		this.isBarrierTop = isBarrier;
	}

	public boolean isBarrierBot() {
		return isBarrierBot;
	}

	public void setBot(Boolean isBarrier) {
		this.isBarrierBot = isBarrier;
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
	
	public boolean getCanClick() {
		return canClick;
	}
	
	public void setCanClick(boolean canClick) {
		this.canClick = canClick;
		if(canClick) {
			this.setStyle("-fx-background-color: "+Colors[pl.getTurn()]+";");
		}else {
			this.setStyle("-fx-background-color: gray;");
		}
		
	}

	
	
}
