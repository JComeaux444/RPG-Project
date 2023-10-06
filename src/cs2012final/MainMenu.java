/* Joseph Comeaux
 * CS2012
 * 05 & 06 
 * Description: Main menu class allows user to pick map size
 * Other Comments: Some old code probably left in.
 */
package cs2012final;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainMenu{

	private static GridPane rootPane;
	
	
	/*
	@Override
	public void start(Stage primaryStage) {
		//primaryStage.setScene(Tiles.MainScene(5,5));
		primaryStage.setScene(MainMenu.mainMenu(primaryStage));
		primaryStage.setTitle("MainMenu");
		primaryStage.show();
	}
	*/
	//sets up the main menu
	public static Scene mainMenu(Stage primaryStage) {
		rootPane = new GridPane();
		rootPane.setPadding(new Insets(20,20,20,20));
		rootPane.setHgap(20);
		rootPane.setVgap(20);
		
		rootPane.add(new Label("Start map with 5x5 grid: "), 0 , 0);
		Button bt5x5 = new Button("5x5");
		bt5x5.setOnAction(event -> {
			//System.out.println("5x5 was pressed");
			primaryStage.setScene(Tiles.mainScene(5, 5,primaryStage));
			userCon(primaryStage);
			//System.out.println(getRootPane());
			//return Tiles.MainScene(5, 5)
		});
		rootPane.add(bt5x5, 1, 0);
		
		rootPane.add(new Label("Start map with 7x7 grid: "), 0 , 1);
		Button bt7x7 = new Button("7x7");
		bt7x7.setOnAction(event -> {
			//System.out.println("7x7 was pressed");
			primaryStage.setScene(Tiles.mainScene(7, 7,primaryStage));
			userCon(primaryStage);
		});
		rootPane.add(bt7x7, 1, 1);
		
		rootPane.add(new Label("Start map with 10x7 grid: "), 0, 2);
		Button bt10x7 = new Button("10x7");
		bt10x7.setOnAction(event -> {
			//System.out.println("10x7 was pressed");
			primaryStage.setScene(Tiles.mainScene(10, 7,primaryStage));
			userCon(primaryStage);
		});
		rootPane.add(bt10x7, 1, 2);
		
		rootPane.add(new Label("\nStart map with Custom size Below\n"
				+ "Both axis must be greater than or equal to 5! \n"
				+ "More tiles = stranger maps\n"), 0, 3);
		TextField width = new TextField();
		
		TextField height = new TextField();
		
		rootPane.add(new Label("Custom X(width): "), 0, 4);
		rootPane.add(width, 1, 4);
		
		rootPane.add(new Label("Custom Y(Height): "), 0, 5);
		rootPane.add(height, 1, 5);
		
		rootPane.add(new Label("Use custom values: "), 0, 6);
		Button useCustoms = new Button("Use Custom Values");
		useCustoms.setOnAction(event -> {
			//System.out.println("useCustoms was pressed");
			//System.out.println(width.getText());
			//System.out.println(height.getText());
			try {
				if (Integer.valueOf(width.getText()) >= 5 &&
					Integer.valueOf(height.getText()) >= 5) {
					//System.out.println("Custom X was " + width.getText());
					//System.out.println("Custom Y was " + height.getText());
					primaryStage.setScene(Tiles.mainScene(
							Integer.valueOf(width.getText()),
							Integer.valueOf(height.getText()),
							primaryStage));
					userCon(primaryStage);
				}
				
			} catch (Exception e) {

			}
		});
		
		rootPane.add(useCustoms, 1, 6);
		
		Scene menu = new Scene(rootPane);
		return menu;
	}
	
	public static Pane getRootPane() {
		return rootPane;
	}
	
	public static void userCon(Stage primaryStage) {
		PlayerInfo.playerControlMenu(primaryStage);
	}
	
	
	/*
	public static void main(String[] args) {
		Application.launch(args);
	}
	*/

	
}
