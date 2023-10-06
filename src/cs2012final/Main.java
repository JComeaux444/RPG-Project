/* Joseph Comeaux
 * CS2012
 * 05 & 06 
 * Description: Main class that starts up the whole program
 * Other Comments: Some old code left in.
 * How to use: Use WASD to move and click on the buttons on the second window to shoot at that tile.
 * Hint: On the second window you will be given text describing what is around you if you are next to a tile with something on it.
 */


package cs2012final;


import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	
	@Override
	public void start(Stage primaryStage) {
		//primaryStage.setScene(Tiles.MainScene(5,5));
		primaryStage.setScene(MainMenu.mainMenu(primaryStage));
		primaryStage.setTitle("MainMenu");
		primaryStage.show();
		
		//Stage playerControl = new Stage();
		//playerControl.setScene(PlayerInfo.playerControl(primaryStage));
		//playerControl.show();
	}
	
	public static void sceneOverride() {
		
	}
	
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
