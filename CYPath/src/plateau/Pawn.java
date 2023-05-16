package plateau;

public class Pawn {
	private int name;	//red pawn is 0, blue pawn is 1, green pawn is 2 and yellow pawn is 3
	private Box box;
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
	
	public boolean move(Box newBox) {
           int newX = newBox.getX();
           int newY = newBox.getY();

           // Check if the new position is unoccupied
           if (!board.isPositionOccupied(newX, newY)) {
                this.box.setX(newX);
                this.box.setY(newY);
           return true; // Move was successful
           }

           return false; // Move was not successful
        }

      

    public void jump(Board board, int newX, int newY) {
        // Get the current position of the box
        int currentX = this.box.getX();
        int currentY = this.box.getY();
	    
	// Get the new position of the box
        int newX = newBox.getX();
        int newY = newBox.getY();


        // Calculate the adjacent player position
        int adjPlayerX = currentX + (newX - currentX - 1);
        int adjPlayerY = currentY + (newY - currentY - 1);

        // Continuously check if the new position is unoccupied
        while (!board.isPositionOccupied(newX, newY) && !board.isBarrierBetween(currentX, currentY, adjPlayerX, adjPlayerY)) {
            // Check if there is a barrier between the adjacent player and the new position
            if (board.isBarrierBetween(adjPlayerX, adjPlayerY, newX, newY)) {
                this.box.setX(adjPlayerX);
                this.box.setY(adjPlayerY);

                // Move the current player to the right or left
                if (currentX == newX) {
                    if (!board.isPositionOccupied(adjPlayerX + 1, adjPlayerY) && !board.isBarrierBetween(adjPlayerX, adjPlayerY, adjPlayerX + 1, adjPlayerY)) {
                        // Move the current player to the right
                        this.box.setX(adjPlayerX + 1);
                    } else if (!board.isPositionOccupied(adjPlayerX - 1, adjPlayerY) && !board.isBarrierBetween(adjPlayerX, adjPlayerY, adjPlayerX - 1, adjPlayerY)) {
                        // Move the current player to the left
                        this.box.setX(adjPlayerX - 1);
                    }
                } else if (currentY == newY) {
                    if (!board.isPositionOccupied(adjPlayerX, adjPlayerY + 1) && !board.isBarrierBetween(adjPlayerX, adjPlayerY, adjPlayerX, adjPlayerY + 1)) {
                        // Move the current player up
                        this.box.setY(adjPlayerY + 1);
                    } else if (!board.isPositionOccupied(adjPlayerX, adjPlayerY - 1) && !board.isBarrierBetween(adjPlayerX, adjPlayerY, adjPlayerX, adjPlayerY - 1)) {
                        // Move the current player down
                        this.box.setY(adjPlayerY - 1);
                    }
                }
            } else {
                // Move the box to the new position
                this.box.setX(newX);
                this.box.setY(newY);
            }
        }
    }
}




	
	
}
