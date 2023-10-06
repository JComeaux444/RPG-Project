/* Joseph Comeaux
 * CS2012
 * 05 & 06 
 * Description:  class that is in charge of the ending stages, win or lose
 * Other Comments: Some old code probably left in.
 */
package cs2012final;

//import hw08.JavaFX;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOver {
	private static GridPane rootPane;

	
	
	//lose stage
	public static Scene endGame(Stage primaryStage) {
		rootPane = new GridPane();
		rootPane.setPadding(new Insets(50,50,50,50));
		rootPane.setHgap(8);
		rootPane.setVgap(8);
		
		rootPane.add(new Label("Game Over!\nYou died!"), 0 , 0);
		
		
		//rootPane.add(new Label("Play Again?"), 0, 1);
		
		/*
		Button mainMenu = new Button("Go to Main Menu");
		mainMenu.setOnAction(event -> {
			
			rootPane.getChildren().clear();
			Stage newGame = new Stage();
			//Tiles.primaryStageG.setScene(MainMenu.mainMenu(newGame));
			//Main.start();			
		});
		rootPane.add( mainMenu, 0, 2);
		*/
		Scene menu = new Scene(rootPane);
		
		return menu;
	}
	
	//win stage
	public static Scene endGameWin(Stage primaryStage) {
		
		JavaFX.MainScene();
		StackPane forPopper = new StackPane();
		forPopper.getChildren().addAll(JavaFX.MainStackPane());
		Label winnerLabel = new Label("YOU WIN");
		winnerLabel.setFont(new Font("Arial",90));
		forPopper.getChildren().add(winnerLabel);
		
		rootPane = new GridPane();
		rootPane.getChildren().addAll(forPopper);
		rootPane.setPadding(new Insets(20,20,20,20));
		rootPane.setHgap(8);
		rootPane.setVgap(8);
		
		//rootPane.add(new Label("You win!"), 1 , 0);
		
		


		
		
		Scene menu = new Scene(rootPane);
		
		return menu;
	}
	
	
	
	
}
