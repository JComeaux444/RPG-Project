/* Joseph Comeaux
 * CS2012
 * 05 & 06 
 * Description: In charge of player info mainly like ammo 
 * as well as the shooting Stage the player uses
 * Other Comments: Some old code probably left in.
 */
package cs2012final;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerInfo {

	private static GridPane rootPane;
	public static String nearNotifications ="";
	public static int playerAmmo = 3;
	public static Stage userConStage = new Stage();
	
	
	//main scene / window for player to use to shoot boss and see ammo
	public static Scene playerControl(Stage primaryStage) {
		rootPane = new GridPane();
		rootPane.setPadding(new Insets(20,20,20,20));
		rootPane.setHgap(8);
		rootPane.setVgap(8);
		
		rootPane.add(new Label("Bombs Left: "), 0 , 0);
		rootPane.add(new Text(printAmmo()), 2 , 0);
		//This area is for attack buttons for the most part
		rootPane.add(new Label("Attack/Shoot into tile over: "), 1, 1);
		Button atkUP = new Button("Shoot Up");
		atkUP.setOnAction(event -> {
			playerAmmo--;
			Tiles.checkBoss(Tiles.getCurPosX(), Tiles.getCurPosY()-1);
			getRootPane().getChildren().clear();
			userConStage.setScene(playerControl(userConStage));
		});
		rootPane.add(atkUP, 1, 2);
		
		Button atkDOWN = new Button("Shoot Down");
		atkDOWN.setOnAction(event -> {
			playerAmmo--;
			Tiles.checkBoss(Tiles.getCurPosX(), Tiles.getCurPosY()+1);
			getRootPane().getChildren().clear();
			userConStage.setScene(playerControl(userConStage));
		});
		rootPane.add(atkDOWN, 1, 3);
		
		Button atkRight = new Button("Shoot Right");
		atkRight.setOnAction(event -> {
			playerAmmo--;
			Tiles.checkBoss(Tiles.getCurPosX()+1, Tiles.getCurPosY());
			getRootPane().getChildren().clear();
			userConStage.setScene(playerControl(userConStage));
		});
		rootPane.add(atkRight, 2, 3);
		
		Button atkLeft = new Button("Shoot Left");
		atkLeft.setOnAction(event -> {
			playerAmmo--;
			Tiles.checkBoss(Tiles.getCurPosX()-1, Tiles.getCurPosY());
			getRootPane().getChildren().clear();
			userConStage.setScene(playerControl(userConStage));
		});
		rootPane.add(atkLeft, 0, 3);
		
		
		rootPane.add(new Label("Notifications: "), 1, 4);
		
		rootPane.add(new Label(Tiles.getDetect()), 1, 5);
		
		Scene menu = new Scene(rootPane);
		
		return menu;
	}
	
	//main method in charge of starting method above, allows to refresh it as well
	public static void playerControlMenu(Stage primaryStage) {
		Stage playerControl = new Stage();
		playerControl.setScene(PlayerInfo.playerControl(playerControl));
		playerControl.show();
		userConStage = playerControl;
	}
	

	
	public static Pane getRootPane() {
		return rootPane;
	}
	
	//used to change ammo based on how many the user has
	public static String printAmmo() {
		String ammoAmount = Integer.toString(playerAmmo);
		return ammoAmount;
	}
	
	
	public void setAmmo(int x) {
		this.playerAmmo += x;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
