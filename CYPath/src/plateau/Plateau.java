package plateau;

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
	
	public void initialise() throws Exception {
		Box[][] tmp = new Box[9][9];
		tmp = this.getPlateau();
		for(int i=0;i<9;i++)
		{
			
			for(int j=0;j<9;j++) {
				tmp[i][j] = new Box();
				tmp[i][j].setLeft(false);
				tmp[i][j].setRight(false);
				tmp[i][j].setTop(false);
				tmp[i][j].setBot(false);
				switch(j) {
				case 0:
					tmp[i][j].setLeft(true);
					break;
				case 8:
					tmp[i][j].setRight(true);
				}
				switch(i) {
				case 0:
					tmp[i][j].setTop(true);
					break;
				case 8:
					tmp[i][j].setBot(true);
				}
				tmp[i][j].setPawn(false);
				tmp[i][j].setPawn(null);
			}
		}
		tmp[0][4].setPawn(true);
		tmp[8][4].setPawn(true);
		tmp[0][4].setPawn(new Pawn(0));
		tmp[8][4].setPawn(new Pawn(1));
		if(this.getNumberPlayers()==4) {
			tmp[4][0].setPawn(true);
			tmp[4][8].setPawn(true);
			tmp[4][0].setPawn(new Pawn(2));
			tmp[4][8].setPawn(new Pawn(3));
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
	
	public static void main(String[] args) throws Exception {
		Plateau p = new Plateau();
		p.initialise();
	}
}
	

