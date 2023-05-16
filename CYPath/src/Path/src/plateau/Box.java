package plateau;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Box extends Button{
	private boolean isPawn = false;
	private Pawn pawn = null;
	private int x,y;
	private boolean isBarrierLeft = false; // defines if the neighbors of the box are accessible or not, if true , that means that there is a barrier							
	private boolean isBarrierRight = false;
	private boolean isBarrierTop = false;
	private boolean isBarrierBot = false;
	
	public Box(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.setOnAction(new BtnHandler());
		this.setStyle("-fx-background-color: gray;");
		this.setMinSize(50, 50);
	}
	
	class BtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			System.out.println(String.valueOf(x)+","+String.valueOf(y));
			
		}
		
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

	public void setLeft() {
		this.isBarrierLeft = true;
	}

	public boolean isBarrierRight() {
		return isBarrierRight;
	}

	public void setRight() {
		this.isBarrierRight = true;
	}

	public boolean isBarrierTop() {
		return isBarrierTop;
	}

	public void setTop() {
		this.isBarrierTop = true;
	}

	public boolean isBarrierBot() {
		return isBarrierBot;
	}

	public void setBot() {
		this.isBarrierBot = true;
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
