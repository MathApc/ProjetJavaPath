package plateau;

public class Pawn {
	private int name;	//red pawn is 0, blue pawn is 1, green pawn is 2 and yellow pawn is 3
	
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}
	
	public Pawn(int name){
		if(0<=name && name <=3)
			this.name = name;
	}
	
	
}
