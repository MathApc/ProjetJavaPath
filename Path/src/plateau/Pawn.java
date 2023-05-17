package plateau;

public class Pawn {
	private int name;	//red pawn is 0 (top), blue pawn is 1(bottom), green pawn is 2(left) and yellow pawn is 3 (right)
	private Box box;
	private int targetRow, targetColumn; //the row or column the pawn should arrive to. Set the other variable to -1
	
	
	public Pawn(int name,Box box) throws Exception{
		this.box = box;
		switch(name) {
			case 0:
			 this.name = name;
			 targetRow = 8;
			 targetColumn = -1;
			 break;
			case 1:
				 this.name = name;
				 targetRow = 0;
				 targetColumn = -1;
				 break;
			case 2:
				 this.name = name;
				 targetRow = -1;
				 targetColumn = 8;
				 break;
			case 3:
				 this.name = name;
				 targetRow = -1;
				 targetColumn = 0;
				 break;
			default:
				throw new UnknownPawnException("The pawn id must be bewteen 0 and 3");
		}
	}
	
	public int getName() {
		return name;
	}
	
	public int getTargetRow() {
		return targetRow;
	}
	
	public int getTargetColumn() {
		return targetColumn;
	}
	
	public void setBox(Box newBox) {
		this.box = newBox;
	}
	
	public Box getBox() {
		return box;
	}
	
	
	
	
}
