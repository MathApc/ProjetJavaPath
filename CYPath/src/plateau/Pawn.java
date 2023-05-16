package plateau;

public class Pawn {
	private int name;	//red pawn is 0, blue pawn is 1, green pawn is 2 and yellow pawn is 3
	private int x;
	private int y;
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}
	
	public Pawn(int name) throws Exception{
		if(0<=name && name <=3)
			this.name = name;
		else
			throw new UnknownPawnException("The pawn id must be bewteen 0 and 3");
	}
	public int getX(){
		return x;
	}
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}
	public boolean move(Position newPosition) {
	    int newX = newPosition.getX();
	    int newY = newPosition.getY();

	    // Check if the new position is unoccupied
	    if (!board.isPositionOccupied(newX, newY)) {
	        setX(newX);
	        setY(newY);
	        return true;  // Move was successful
	    }

	    return false;  // Move was not successful
	}
        public void jump(Board board, int newX, int newY) {
         // Get the current position of the pawn
          int currentX = getX();
          int currentY = getY();

         // Calculate the adjacent player position
          int adjPlayerX = currentX + (newX - currentX-1);
          int adjPlayerY = currentY + (newY - currentY-1);

         // Continuously check if the new position is unoccupied and that there is no barrier 
         while (!board.isPositionOccupied(newX, newY) && !board.isBarrierBetween(currentX, currentY, adjPlayerX, adjPlayerY)) {
         // Check if there is a barrier between the adjacent player and the new position
         if (board.isBarrierBetween(adjPlayerX, adjPlayerY, newX, newY)) {
            setX(adjPlayerX);
            setY(adjPlayerY);
            // Check if the barrier is vertical
             if (currentX == newX) {
                if (!board.isPositionOccupied(adjPlayerX + 1, adjPlayerY) && !board.isBarrierBetween(adjPlayerX, adjPlayerY, adjPlayerX + 1, adjPlayerY)) {
                // Move the current player to the right
                setX(adjPlayerX + 1);
                }
                else if (!board.isPositionOccupied(adjPlayerX - 1, adjPlayerY) && !board.isBarrierBetween(adjPlayerX, adjPlayerY, adjPlayerX - 1, adjPlayerY)) {
                //Move the current player to the left
                setX(adjPlayerX - 1);
            } 
             // Check if the barrier is horizontal
            elif (currentY == newY) {
                if (!board.isPositionOccupied(adjPlayerX, adjPlayerY + 1) && !board.isBarrierBetween(adjPlayerX, adjPlayerY, adjPlayerX, adjPlayerY + 1)){
                // Move the current player up
                setY(adjPlayerY + 1);  
                }
                else if (!board.isPositionOccupied(adjPlayerX, adjPlayerY - 1) && !board.isBarrierBetween(adjPlayerX, adjPlayerY, adjPlayerX, adjPlayerY - 1)){
                //Move the current player down
                setY(adjPlayerY - 1);
                }
               
            }
        } else {
            // Move the pawn to the new position
            setX(newX);
            setY(newY);
        }
    }
}




	
	
}
