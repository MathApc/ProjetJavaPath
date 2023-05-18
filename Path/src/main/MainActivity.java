package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import plateau.BorderList;
import plateau.Border;
import plateau.Box;
import plateau.Pawn;
import plateau.Plateau;


public class MainActivity extends Application{
	
	int turn ;
	boolean move = false;
	boolean barrier = false;
	public Label player_turn_label ;
	public Button barrier_btn, move_btn, new_game_btn;
	GridPane grid;
	Plateau pl;
	
	public static void main(String[] args) {		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Path");
		
		pl = new Plateau(4,this);
		this.turn = pl.getTurn();
		BorderList vertical_borders = new BorderList(9, 10, 'V', pl);
		BorderList horizontal_borders = new BorderList(10, 9, 'H', pl);
		
		//root container with two columns
		HBox root = new HBox(2);
		
		// grid for playground
		grid = createGridPane(pl,vertical_borders,horizontal_borders);
		HBox.setMargin(grid, new Insets(100,100,100,100));
		
		drawPawns();
		
		//container for buttons and text with 4 rows
		VBox container = createContainer();
		
		//adding containers in the root container
		root.getChildren().addAll(container, grid);
		root.setStyle("-fx-background-color: #46315c;");
		
		Scene scene = new Scene(root, 1080, 700);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	public void drawPawns() {
		for(Pawn p : pl.getPawns()){
			if(grid.getChildren().contains(p)) {
				grid.getChildren().remove(p);
			}
			grid.add(p, p.getBox().gety()*2+1, p.getBox().getx()*2+1);
			GridPane.setHalignment(p, HPos.CENTER);
			
		}
	}
	
	private GridPane createGridPane(Plateau pl, BorderList vl, BorderList hl) {
        GridPane gridPane = new GridPane();
  

        int size = pl.getPlateau().length;
        
        for (int row = 0; row < size; row++) {
        	
            for (int col = 0; col < size; col++) {
            	
            	Box cell = pl.getBox(row, col);
            	Border vertical_border = vl.getBorder(row, col);           
                Border horizontal_border = hl.getBorder(row, col);

                gridPane.add(cell, col*2+1, row*2+1);
                gridPane.add(horizontal_border, col*2+1, row*2);
                gridPane.add(vertical_border, col*2, row*2+1);
                
            }
            
            Border outter_vertical_border = vl.getBorder(row, size);
            Border outter_horizontal_border = hl.getBorder(size, row);
            
            gridPane.add(outter_horizontal_border, row*2+1, size*2);
            gridPane.add(outter_vertical_border, size*2, row*2+1);
            
        }
        
        return gridPane;
    }
	
	private VBox createContainer() {
		
		VBox container = new VBox(4);
		
		// label for indicating player turn
		player_turn_label = new Label();
		player_turn_label.setText("Player " + String.valueOf(turn+1));
		player_turn_label.setFont(new Font("Arial", 30));
		player_turn_label.setTextFill(Color.web("#0076a3"));
		player_turn_label.setStyle("-fx-font-weight: bold;");
		VBox.setMargin(player_turn_label, new Insets(5,0,100,50));
		
		//button for adding barrier
		barrier_btn = new Button();
		set_background(barrier_btn, "barrier", false);
		barrier_btn.setOnAction(new BarrierBtnHandler());
		
		//button for moving pawn
		move_btn = new Button();
		set_background(move_btn, "move", false);
		move_btn.setOnAction(new MoveBtnHandler());
		
		//button for a new game
		new_game_btn = new Button();
		set_background(new_game_btn, "new_game", false);
		VBox.setMargin(new_game_btn, new Insets(100,0,0,50));
		new_game_btn.setOnAction(new GameBtnHandler());
		
		//adding items in container	
		container.getChildren().addAll(player_turn_label, barrier_btn, move_btn, new_game_btn);
		
		return container;
	}
	
	public void set_background(Button btn, String image, Boolean selected) {
		// set the background of a button with an image
		if (selected) { // if the button is selected change the size and margin and add S to the image name
			VBox.setMargin(btn, new Insets(3,3,3,33));
			btn.setMinSize(239, 104);
			image = image + "S";
		}else {
			VBox.setMargin(btn, new Insets(20,20,20,50));
			btn.setMinSize(205, 70);
		}
		BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("/images/"+image+".png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		btn.setBackground(new Background(backgroundImage));
	}
	
	
	class BarrierBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			if(!pl.isGameOver() && pl.getNumberBarriere() <= 20) {
			
				barrier = true;
				move = false;
				
				pl.setCanClickBorder(barrier);
				pl.setCanClickBox(move);
				
				pl.removeClickableBoxes();
				
				set_background(barrier_btn, "barrier", true);
				set_background(move_btn, "move", false);
			}
			
		}
		
	}
	
	class MoveBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			if(!pl.isGameOver()) {
				
				barrier = false;
				move = true;
				
				pl.setCanClickBorder(barrier);
				pl.setCanClickBox(move);
				
				pl.setClickableBoxes(pl.getTurn());
				
				set_background(barrier_btn, "barrier", false);
				set_background(move_btn, "move", true);
				
			}
			
					
		}
		
	}
	
	class GameBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			
		}
		
	}
}
