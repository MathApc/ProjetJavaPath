package plateau;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import main.MainActivity;

public class Plateau {
	private Box[][] plateau;
	private Pawn[] pawns;
	private boolean isGameOver;
	private int numberBarrier;
	private int numberPlayers;
	private int turn = 0;
	private Pawn winner;
	
	private MainActivity activity;
	
	private boolean canClickBorder = false;
	private boolean canClickBox = false;
	
	public Plateau(int numberPlayers, MainActivity activity) throws Exception{
		/*if(numberPlayers != 2 || numberPlayers != 4)
		{
			throw new IncorrectNumerPlayersException("There must be 2 or 4 players");
		}
		*/		this.plateau = new Box[9][9];
		isGameOver= false;
		numberBarrier = 0;
		this.numberPlayers = numberPlayers;
		this.activity = activity;
		initialise();
	}
	
	public Plateau(MainActivity activity) throws Exception {
		this(2, activity);
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
	
	public void setWinner(Pawn winner) {
		this.winner = winner;
	}
	
	public Pawn getWinner() {
		return winner;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
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
	
	public boolean canClickBox() {
		return canClickBox;
	}
	
	public void setCanClickBox(boolean canClick) {
		this.canClickBox = canClick;
	}
	
	
	public void borderClicked(int x, int y, boolean isVertical,boolean clicked) {
		if(isVertical) {
			getBox(x,y-1).setRight(clicked);
			getBox(x,y).setLeft(clicked);
		}else {
			getBox(x,y).setTop(clicked);
			getBox(x-1,y).setBot(clicked);
		}
	}
	
	
	public List<Box> Neighbours(Box box, int comesFrom){
		
		//comes from is an integer that matters only when recursive (when neighborOfNeighbor) 
		//to not count the initial box or pawn as a neighbor again
		
	
		List<Box> neighbours = new ArrayList<Box>();
				
		if(!box.isBarrierTop() && comesFrom != 0){
			Box neighbor = getBox(box.getx()-1, box.gety());
			if (neighbor.isPawn()) {
				if(neighbor.isBarrierTop() && comesFrom == -1) {
					for(Box neighborOfNeighbor: Neighbours(neighbor,1)) {
						neighbours.add(neighborOfNeighbor);
					}
				}else if(getBox(neighbor.getx()-1, neighbor.gety()).isPawn() && comesFrom == -1) {
					for(Box neighborOfNeighbor: Neighbours(neighbor,1)) {
						neighbours.add(neighborOfNeighbor);
					}
				}else if(!getBox(neighbor.getx()-1, neighbor.gety()).isPawn() && comesFrom == -1) {
					neighbours.add(getBox(neighbor.getx()-1, neighbor.gety()));
				}
				
			}else {
				neighbours.add(neighbor);
			}
		}
		if(!box.isBarrierBot() && comesFrom != 1){
			Box neighbor = getBox(box.getx()+1, box.gety());
			if (neighbor.isPawn()) {
				if(neighbor.isBarrierBot() && comesFrom == -1) {
					for(Box neighborOfNeighbor: Neighbours(neighbor,0)) {
						neighbours.add(neighborOfNeighbor);
					}
				}else if(getBox(neighbor.getx()+1, neighbor.gety()).isPawn() && comesFrom == -1) {
					for(Box neighborOfNeighbor: Neighbours(neighbor,0)) {
						neighbours.add(neighborOfNeighbor);
					}
				}else if(!getBox(neighbor.getx()+1, neighbor.gety()).isPawn() && comesFrom == -1) {
					neighbours.add(getBox(neighbor.getx()+1, neighbor.gety()));
				}
				
			}else {
				neighbours.add(neighbor);
			}
		}
		if(!box.isBarrierLeft() && comesFrom != 2){
			Box neighbor = getBox(box.getx(), box.gety()-1);
			if (neighbor.isPawn()) {
				if(neighbor.isBarrierLeft() && comesFrom == -1) {
					for(Box neighborOfNeighbor: Neighbours(neighbor,3)) {
						neighbours.add(neighborOfNeighbor);
					}
				}else if(getBox(neighbor.getx(), neighbor.gety()-1).isPawn() && comesFrom == -1) {
					for(Box neighborOfNeighbor: Neighbours(neighbor,3)) {
						neighbours.add(neighborOfNeighbor);
					}
				}else if(!getBox(neighbor.getx(), neighbor.gety()-1).isPawn() && comesFrom == -1) {
					neighbours.add(getBox(neighbor.getx(), neighbor.gety()-1));
				}
				
			}else {
				neighbours.add(neighbor);
			}
		}
		if(!box.isBarrierRight() && comesFrom != 3){
			Box neighbor = getBox(box.getx(), box.gety()+1);
			if (neighbor.isPawn()) {
				if(neighbor.isBarrierRight() && comesFrom == -1) {
					for(Box neighborOfNeighbor: Neighbours(neighbor,2)) {
						neighbours.add(neighborOfNeighbor);
					}
				}else if(getBox(neighbor.getx(), neighbor.gety()+1).isPawn() && comesFrom == -1) {
					for(Box neighborOfNeighbor: Neighbours(neighbor,2)) {
						neighbours.add(neighborOfNeighbor);
					}
				}else if(!getBox(neighbor.getx(), neighbor.gety()+1).isPawn() && comesFrom == -1) {
					neighbours.add(getBox(neighbor.getx(), neighbor.gety()+1));
				}
				
			}else {
				neighbours.add(neighbor);
			}
		}
		
		return neighbours;

	}
	
	public List<Box> Neighbours(Box box){
		return Neighbours(box,-1);
	}
	
	public void setClickableBoxes(int turn) {
		List<Box> neighbours = Neighbours(getPawn(turn).getBox());
		
		for(Box neighbor: neighbours) {
			neighbor.setCanClick(true);
		}
	}
	
	public void reset() {

		canClickBorder = false;
		canClickBox = false;
		
		setTurn((getTurn()+1)%getNumberPlayers());
		
		activity.player_turn_label.setText("Player " + String.valueOf(getTurn()+1));
		activity.set_background(activity.barrier_btn, "barrier", false);
		activity.set_background(activity.move_btn, "move", false);
		
		removeClickableBoxes();
		activity.drawPawns();
		
		if(isGameOver()) {
			activity.player_turn_label.setText("Player " + String.valueOf(getWinner().getName()+1) + " Won!");
		}
		
	}
	
	public void removeClickableBoxes() {
		for(Box[] boxList: getPlateau()) {
			for(Box box: boxList) {
				box.setCanClick(false);
			}
			
		}
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
				plateau[i][j] = new Box(i,j, this);
				
				switch(j) {
				case 0:
					plateau[i][j].setLeft(true);
					break;
				case 8:
					plateau[i][j].setRight(true);
					break;
				}
				switch(i) {
				case 0:
					plateau[i][j].setTop(true);
					break;
				case 8:
					plateau[i][j].setBot(true);
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
	

