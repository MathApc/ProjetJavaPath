package plateau;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Border extends Button{
	
	private int x, y;
	private boolean isBarrier, isVertical, isHorizontal;
	private Plateau pl;
	private BorderList list;
	private int whichNeighbour = 0;
	
	public Border(int x, int y, char rotation, Plateau pl, BorderList list) {
		this.x = x;
		this.y = y;
		this.pl =pl;
		this.list = list;
		isBarrier = false;
		
		this.setStyle("-fx-background-color: #6d88b3;");
		if(rotation == 'V') {
			this.setMinSize(5, 50);
			this.setMaxSize(5, 50);
			isVertical = true;
			isHorizontal = false;
		}else {
			this.setMinSize(50, 5);
			this.setMaxSize(50, 5);
			
			isVertical = false;
			isHorizontal = true;
		}
		
		
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {
		      @Override public void handle(MouseEvent event) {
		        
		    	  if(pl.canClickBorder()&&!isBarrier()) {
			    	  double percentage;
			    	  if(isVertical()) {
			    		  percentage = event.getY()/50;
			    	  }else {
			    		  percentage = event.getX()/50; 
			    	  }
			    	  
			    	  List<Border> neighbours = Neighbours();
			    	  
			    	  if(percentage>0.5 && neighbours.size()>1) {
			    		  whichNeighbour = 1;
			    		  neighbours.get(0).originalColor();
			    	  }
			    	  
			    	  if(percentage<=0.5 && neighbours.size()>0) {
			    		  whichNeighbour = 0;
			    		  if(neighbours.size()>1) {
			    			  neighbours.get(1).originalColor();
			    		  } 
			    	  }
			    	  
			    	  if(neighbours.size()>0) {
			    		  changeColor();
			    		  neighbours.get(whichNeighbour).changeColor();
			    	  }
			    	  
		    	  }
		        
		      }
		    });
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				if(pl.canClickBorder()&&!isBarrier()) {
					System.out.println("barrier : " + String.valueOf(x)+","+String.valueOf(y));
					
					List<Border> neighbours = Neighbours();
					if (neighbours.size() > 0) {
						Border neighbour = neighbours.get(whichNeighbour);
						
						neighbour.setBarrier();
						setBarrier();
						
						pl.borderClicked(getX(), getY(), isVertical());
						pl.borderClicked(neighbour.getX(), neighbour.getY(), neighbour.isVertical());
						
						System.out.println(pl.existsWay(pl.getPawn(0)));
						
					}
		    	  }
		      }	
		});
		
	    this.setOnMouseExited(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent event) {
	    	  if(pl.canClickBorder()&&!isBarrier()) {
	    		  if(Neighbours().size()>0) {
	    			  originalColor();
	  		        Neighbours().get(whichNeighbour).originalColor();
	    		  }
		        
	    	  }
	      }
	    });
			
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isVertical() {
		return isVertical;
	}
	
	public boolean isHorizontal() {
		return isHorizontal;
	}
	
	public boolean isBarrier() {
		return isBarrier;
	}
	
	public void setBarrier() {
		isBarrier = true;
		changeColor();
	}
	
	public void changeColor() {
		this.setStyle("-fx-background-color: black;");
	}
	
	public void originalColor() {
		this.setStyle("-fx-background-color: #6d88b3;");
	}
	
	public List<Border> Neighbours(){
		List<Border> neighbours = new ArrayList<Border>();
		
			if(isVertical()) {
				if(x>0) {
					if(!list.getBorder(x-1, y).isBarrier()) {
						neighbours.add(list.getBorder(x-1, y));
					}
				}
				
				if(x<list.getrows()-1) {
					if(!list.getBorder(x+1, y).isBarrier()) {
						neighbours.add(list.getBorder(x+1, y));
					}
				}
				
			}else {
				if(y>0) {
					if(!list.getBorder(x, y-1).isBarrier()) {
						neighbours.add(list.getBorder(x, y-1));
					}
				}
				
				if(y<list.getcol()-1) {
					if(!list.getBorder(x, y+1).isBarrier()) {
						neighbours.add(list.getBorder(x, y+1));
					}
				}
				
			}
		
		return neighbours;
	}

	
	

}
