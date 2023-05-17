package plateau;

public class BorderList {
	
	private Border[][] list;
	private int rows, col;
	
	public BorderList(int rows, int col, char rotation, Plateau pl) {
		
		this.rows = rows;
		this.col = col;
		
		list = new Border[rows][col];
		
		
		for(int i = 0; i < rows; i++) {
			for (int j=0; j<col; j++) {
				Border border = new Border(i, j, rotation, pl, this);
				
				if(rotation == 'V' && (j == 0|| j == col-1)) {
					border.setBarrier();
				}
				if(rotation != 'V' && (i == 0|| i == rows-1)) {
					border.setBarrier();
				}
				
				list[i][j] = border;
				
			}
		}
		
	}
	
	public Border getBorder(int x, int y) {
		return list[x][y];
	}
	
	public Border[][] getList(){
		return list;
	}
	
	public int getrows() {
		return rows;
	}
	
	public int getcol() {
		return col;
	}

}
