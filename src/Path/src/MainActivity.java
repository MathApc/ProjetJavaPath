
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import plateau.Board;
import plateau.Box;
import plateau.Coordinates;

public class MainActivity extends Application{
	
	int turn = 0;
	int launched,gameOver = 0;
	boolean move = false;
	boolean barrier = false;
	Label player_turn_label ;
	Button barrier_btn, move_btn, new_game_btn;
	public Board pl;
	
	public static void main(String[] args) {		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("hello");
		
		pl = new Board();
		
		//root container with two columns
		HBox root = new HBox(2);
		
		// grid for playground
		GridPane grid = createGridPane(pl);
		HBox.setMargin(grid, new Insets(100,100,100,100));
		
		
		//container for buttons and text with 4 rows
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
		
		
		//adding containers in the root container
		root.getChildren().addAll(container, grid);

		root.setStyle("-fx-background-color: #46315c;");
		
		Scene scene = new Scene(root, 1080, 700);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private static GridPane createGridPane(Board pl) throws FileNotFoundException {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        for (int row = 0; row < pl.getBoard().length; row++) {
            for (int col = 0; col < pl.getBoard().length; col++) {
            	if(pl.getBox(row,col).isPawn()) {    
            		
            		if(pl.getBox(row, col).getPawn().getName()==0) {
            			Image image = new Image("File:H:\\Ing1\\Semestre 2\\Java et IHM\\CYPath\\src\\Path\\src\\images\\red.png");
            			ImageView pic = new ImageView();
                		pic.setFitWidth(50);
                		pic.setFitHeight(50);
                		pic.setImage(image);
                		gridPane.add(pic, col, row);
            		}
            		else if(pl.getBox(row, col).getPawn().getName()==1) {
            			Image image = new Image("File:H:\\Ing1\\Semestre 2\\Java et IHM\\CYPath\\src\\Path\\src\\images\\blue.png");
            			ImageView pic = new ImageView();
                		pic.setFitWidth(50);
                		pic.setFitHeight(50);
                		pic.setImage(image);
                		gridPane.add(pic, col, row);
            		}
            		else if(pl.getBox(row, col).getPawn().getName()==2) {
            			Image image = new Image("File:H:\\Ing1\\Semestre 2\\Java et IHM\\CYPath\\src\\Path\\src\\images\\green.png");
            			ImageView pic = new ImageView();
                		pic.setFitWidth(50);
                		pic.setFitHeight(50);
                		pic.setImage(image);
                		gridPane.add(pic, col, row);
            		}else {
            			Image image = new Image("File:H:\\Ing1\\Semestre 2\\Java et IHM\\CYPath\\src\\Path\\src\\images\\yellow.png");
            			ImageView pic = new ImageView();
                		pic.setFitWidth(50);
                		pic.setFitHeight(50);
                		pic.setImage(image);
                		gridPane.add(pic, col, row);
            		}
            		
            	}
            	else
            		gridPane.add(pl.getBox(row, col), col, row);
            }
        }
        
        
        return gridPane;
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
			barrier = true;
			move = false;
			
			set_background(barrier_btn, "barrier", true);
			set_background(move_btn, "move", false);
			
		}
		
	}
	
	class MoveBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			barrier = false;
			move = true;
			
			set_background(barrier_btn, "barrier", false);
			set_background(move_btn, "move", true);
			/*Coordinates c = new Coordinates();
			List<Box> list = new ArrayList<>();
			list = getNeighboringBoxes(pl.getBox(c.getX(), c.getY()));
			*/
			
		}
		
	}
	
	class GameBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			/*launched = 1;
			while(launched == 1 || gameOver ==0) {	//launched defines if the game is launched, paused or not.
				if(turn == 0) {
					Coordinates c = new Coordinates();
					c = pl.locate(turn);
					if(c != new Coordinates()) {
						List<Box> list = neighbors(pl.getBox(c.getX(),c.getY()));	// we locate the player's pawn to allow him to move in a neighbor box
						
					}
				}
			}
			*/
			
		}
		
	}
	
	
}
