package plateau;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Plateau {
	private Box[][] plateau;
	private Pawn[] pawns;
	private boolean isGameOver;
	private int numberBarrier;
	private int numberPlayers;
	
	private boolean canClickBorder = false;;
	
	public Plateau(int numberPlayers) throws Exception{
		/*if(numberPlayers != 2 || numberPlayers != 4)
		{
			throw new IncorrectNumerPlayersException("There must be 2 or 4 players");
		}
		*/		this.plateau = new Box[9][9];
		isGameOver= false;
		numberBarrier = 0;
		this.numberPlayers = numberPlayers;
		initialise();
	}
	
	public Plateau() throws Exception {
		this(2);
	}
	
	public Box[][] getPlateau() {
		return plateau;
	}
	
	
	public void setPlateau(Box[][] plateau) {
		this.plateau = plateau;
	}
	

	public Box getBox(int x,int y) {	
		return plateau[x][y];
	}
	
	
	public boolean isGameOver() {
		return isGameOver;
	}
	
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	public int getNumberBarriere() {
		return numberBarrier;
	}
	
	public void setNumberBarriere(int numberBarrier) {
		this.numberBarrier = numberBarrier;
	}
	
	public int getNumberPlayers() {
		return numberPlayers;
	}
	
	public void setNumberPlayers(int numberPlayers) {
		this.numberPlayers = numberPlayers;
	}
	
	public Pawn[] getPawns() {
		return pawns;
	}
	
	public Pawn getPawn(int index) {
		return pawns[index];
	}
	
	public boolean canClickBorder() {
		return canClickBorder;
	}
	
	public void setCanClickBorder(boolean canClick) {
		this.canClickBorder = canClick;
	}
	
	public void borderClicked(int x, int y, boolean isVertical) {
		if(isVertical) {
			getBox(x,y-1).setRight();
			getBox(x,y).setLeft();
		}else {
			getBox(x,y).setTop();
			getBox(x-1,y).setBot();
		}
	}
	
	
	public List<Box> Neighbours(Box box){
	
		List<Box> neighbours = new ArrayList<Box>();
				
		if(!box.isBarrierTop()){
			neighbours.add(getBox(box.getx()-1, box.gety()));
		}
		if(!box.isBarrierBot()){
			neighbours.add(getBox(box.getx()+1, box.gety()));
		}
		if(!box.isBarrierLeft()){
			neighbours.add(getBox(box.getx(), box.gety()-1));
		}
		if(!box.isBarrierRight()){
			neighbours.add(getBox(box.getx(), box.gety()+1));
		}

		return neighbours;

	}
	
	public boolean existsWay(Pawn pawn) {
		
		//BFS
		List<Integer> marked = new ArrayList<>(); //using a list of integers rather than box for memory efficiency
		Queue<Box> queue = new LinkedList<>();
		queue.add(pawn.getBox());
		
		while(queue.size()>0) {
			Box V = queue.poll(); //take the first item of the queue
			if (!marked.contains(V.getx()*9 + V.gety())) { // check if marked (x*9 + y) is unique and represents the boxes
				
				if(pawn.getTargetRow() == V.getx() || pawn.getTargetColumn() == V.gety()) { //check if arrived to the target row or column
					return true;
				}
				
				marked.add(V.getx()*9 + V.gety()); //mark it
				
				for(Box W : Neighbours(V)) {
					if (!marked.contains(W.getx()*9 + W.gety())) { //check if not marked
						queue.add(W); //add neighbor to queue
					}
				}	
				
			}
		}
		
		return false;
	}
	

	public void initialise() throws Exception {
		
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++) {
				plateau[i][j] = new Box(i,j);
				
				switch(j) {
				case 0:
					plateau[i][j].setLeft();
					break;
				case 8:
					plateau[i][j].setRight();
					break;
				}
				switch(i) {
				case 0:
					plateau[i][j].setTop();
					break;
				case 8:
					plateau[i][j].setBot();
					break;
				}
			}
		}
		
		
		
		
		if(this.getNumberPlayers()==2) {
			pawns = new Pawn[2];
			pawns[0] = new Pawn(0,plateau[0][4]);
			pawns[1] = new Pawn(1,plateau[8][4]);
			
		}else {
			pawns = new Pawn[4];
			pawns[0] = new Pawn(0, plateau[0][4]);
			pawns[1] = new Pawn(1, plateau[8][4]);
			pawns[2] = new Pawn(2, plateau[4][0]);
			pawns[3] = new Pawn(3, plateau[4][8]);
			
			plateau[4][0].setPawn(true);
			plateau[4][8].setPawn(true);
			plateau[4][0].setPawn(pawns[2]);
			plateau[4][8].setPawn(pawns[3]);
		}
		
		plateau[0][4].setPawn(true);
		plateau[8][4].setPawn(true);
		plateau[0][4].setPawn(pawns[0]);
		plateau[8][4].setPawn(pawns[1]);
		
			
	}
	

	
	
}
	

