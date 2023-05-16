package plateau;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
	private Box[][] plateau;
	private boolean isGameOver;
	private int numberBarrier;
	private int numberPlayers;
	
	public Plateau(int numberPlayers) throws Exception{
		/*if(numberPlayers != 2 || numberPlayers != 4)
		{
			throw new IncorrectNumerPlayersException("There must be 2 or 4 players");
		}
		*/
		this.plateau = new Box[9][9];
		isGameOver= false;
		numberBarrier = 0;
		this.numberPlayers = numberPlayers;
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
	
	public List<Box> Neighbours(Box box){
	
		List<Box> neighbours = new ArrayList<Box>();
		if(!box.isBarrierTop()){
			neighbours.add(plateau.getBox(box.getx(), box.gety()-1))
		}
		if(!box.isBarrierBot()){
			neighbours.add(plateau.getBox(box.getx(), box.gety()+1))
		}
		if(!box.isBarrierLeft()){
			neighbours.add(plateau.getBox(box.getx()-1, box.gety()))
		}
		if(!box.isBarrierRight()){
			neighbours.add(plateau.getBox(box.getx()+1, box.gety()))
		}

		return neighbours;

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
		plateau[0][4].setPawn(true);
		plateau[8][4].setPawn(true);
		plateau[0][4].setPawn(new Pawn(0));
		plateau[8][4].setPawn(new Pawn(1));
		if(this.getNumberPlayers()==4) {
			plateau[4][0].setPawn(true);
			plateau[4][8].setPawn(true);
			plateau[4][0].setPawn(new Pawn(2));
			plateau[4][8].setPawn(new Pawn(3));
		}
			
	}
	
	public void afficher() {
		int i,j;
		for(i=0;i<9;i++)
		{
			System.out.print(" ");
			for(j=0;j<9;j++)
			{
				if(this.getBox(i, j).isBarrierTop()==true) 
					System.out.print("_ ");
			}
			System.out.println();
			for(j=0;j<9;j++) {
				if(this.getBox(i, j).isBarrierLeft()==true) 
					System.out.print("|");
				
				if(this.getBox(i,j).isPawn())
					System.out.print(0+" ");
				else
					System.out.print(1+" ");
				
				if(this.getBox(i, j).isBarrierRight()==true) 
					System.out.print("|");
			}
		}
		System.out.println();
		System.out.print(" ");
		for(j=0;j<9;j++)
		{
			if(this.getBox(8, j).isBarrierBot()==true) 

				System.out.print(" ‾");
		}	
		
	}
	
	
}
	

