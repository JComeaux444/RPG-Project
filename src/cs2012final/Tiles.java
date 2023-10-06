/* Joseph Comeaux
 * CS2012
 * 05 & 06 
 * Description: One of the main classes for this program is in charge of
 * making the map stage and keeping track of variables to use 
 * Other Comments: Some old code left in for reference.
 */
package cs2012final;


import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class Tiles {

	private static String curPos = ""+"-1"+","+"-1";
	private static int curPosX = 0;
	private static int curPosY = 0;
	
	private static int prevPosX = 0;
	private static int prevPosY = 0;
	
	
	private static int mapMaxX = 4;
	private static int mapMaxY = 4;
	private static GridPane worldMap = new GridPane();
	private static StackPane rootPane;
	//private static StackPane finalMap;
	
	private static String detect = "";
	private static boolean isAlive = true;
	
	static Stage primaryStageG = new Stage();
	
	
	public static Scene mainScene(int x, int y, Stage primaryStage) {
		Group group1 = new Group();
		//runs first, gives anything needed to the overall "map"
		mapTiles2(x,y,primaryStage);
		mapMaxX = x ;
		mapMaxY = y ;
		primaryStageG = primaryStage;
		primaryStageG.setTitle("Map");
		PlayerInfo.userConStage.setTitle("User Controls");
		//Background color of scene, can set root background color! Not exactly a scene
		Color bg = Color.rgb(90, 90, 90);
		BackgroundFill backFill = new BackgroundFill(bg, CornerRadii.EMPTY, Insets.EMPTY);
		Background back1 = new Background(backFill);
		
		
		//adds the 'map' to the group
		group1.getChildren().addAll(worldMap);
		//System.out.print(javafx.scene.text.Font.getFamilies());

		rootPane = new StackPane();
		//rootPane.getChildren().add(group1);
		
		AnchorPane debugPane = new AnchorPane();
		Button debug = new Button("Debug Toggle");
		//debug.setIndeterminate(false);
		debug.setOnMouseClicked( e ->{
			if(TileObj.visable == false) {
				TileObj.visable = true;
			}
			else if(TileObj.visable == true) {
				TileObj.visable = false;
			}
			refreshMap();
		});
		debugPane.getChildren().add(debug);
		rootPane.getChildren().addAll(group1,debugPane);

		
		Scene scene = new Scene(rootPane, 500, 575);
		
		//sets background of scene, can set root background color
		rootPane.setBackground(back1);
		
		//tells scene what to do on a key press, sends info to another method
		scene.setOnKeyPressed(e ->{
			detect = "";
			//refreshUserPanel();
			moveTest(e);
			//System.out.println(TileObj.myX);
			//System.out.println(TileObj.myY);
			
			
			
			nearCheck(curPosX -1,curPosY);
			nearCheck(curPosX +1,curPosY);
			nearCheck(curPosX ,curPosY -1);
			nearCheck(curPosX ,curPosY +1);
			//System.out.println("this is detection \n"+detect);
			if(isAlive) {
				refreshUserPanel();
			}
			//System.out.println("now at "+curPosX+","+curPosY+" tile");
			//System.out.println((Rectangle)worldMap.lookup("#"+curPosX+","+curPosY));
			
			//System.out.println(" ");
			
			
		});
		
		return scene;
	}
	
	public static Pane setRootPane(int w, int h,Stage primaryStage) {
		mainScene(w,h,primaryStage);
		return rootPane;
	}
	/*
	public static GridPane mapTiles(int rows, int columns) {
		
		GridPane map = new GridPane();
			
		//System.out.print(MainScene().getHeight()); The .get, does not work, 
		//leaves a error need a way to detect size in
		//here so its kind of expandable
		double Hgt = 500;
		double Wdt = 500;
		double subByH = Hgt/rows;
		double subByW = Wdt/columns;
		double tileSizeH = (Hgt/rows);
		double tileSizeW = (Wdt/columns);
		int maxX = columns - 1;
		int maxY = rows - 1;
		//maxX = mapMaxX;
		mapMaxX = maxX;
		//maxY = mapMaxY;
		mapMaxY = maxY;
		
		Random ranNum = new Random();
		int ranX = ranNum.nextInt(columns);
		int ranY = ranNum.nextInt(rows);
		String randPos = ""+ranX+","+ranY;
		System.out.println(randPos);
		System.out.println(curPos);
		curPos = randPos;
		System.out.println(curPos);
		
		for(int i = 0; i < rows; i++) {
			for(int x = 0; x < columns; x++) {
				Rectangle tile = new Rectangle();
				
				//(tileSizeW)*(x+1), (tileSizeH)*(i+1),
				//tileSizeW- (1), tileSizeH-(1) 
				
				tile.setX((tileSizeW)*(x+1));
				tile.setY((tileSizeH)*(i+1));
				tile.setWidth(tileSizeW- (1));
				tile.setHeight(tileSizeH-(1));
				
				
				
				tile.setFill(Color.GREY);	
				
				//System.out.println(tile.getId());
				tile.setOnMouseEntered(event ->{
					tile.setFill(Color.FIREBRICK);
					//System.out.println(map.lookup("#"+curPos));
					//System.out.println(tile.getId());
					move(tile,tile.getId(),maxX,maxY);
					System.out.println(map.lookup("#"+curPos));
				});
				tile.setOnMouseExited(event ->{
					tile.setFill(Color.GREY);
				});
				
				map.add(tile, x, i);
				
				tile.setId(""+x+","+i);
				
				System.out.println(tile.getId());
				
				//System.out.println(tile.getId());
				
				
				
				//TRY to see if i can get IDs out of this area and into
				//the real area to confirm if IDs can be used to change
				//color at least
				
				
				if(tile.getId().equalsIgnoreCase(curPos)) {
					tile.setFill(Color.AQUA);
				}
			}
			//next line code	
		}
		//worldMap.getChildren().add(map);
		
		//VBox t = new VBox();
		CheckBox debug = new CheckBox("Debug\nToggle");
		
		map.add(debug, columns/2, rows);
	
		worldMap.getChildren().addAll(map);
		return map;
	}
	*/
	
	//in charge of making the tiles on the map
	public static GridPane mapTiles2(int rows, int columns,Stage primaryStage) {

		GridPane map = new GridPane();
		
		Random ranNum = new Random();
		int ranX = ranNum.nextInt(columns);
		int ranY = ranNum.nextInt(rows);
		
		
		for(int i = 0; i < rows; i++) {
			for(int x = 0; x < columns; x++) {
				TileObj tile = new TileObj(rows, columns, x, i, primaryStage);
				map.add(tile, x, i);
				
				//Makes it so its X,Y, bad naming here
				worldMap.add(tile, i, x);
				//tile.setId(""+x+","+i);
				//System.out.println(tile.getId());
				//System.out.println(tile.getId());
				
				String randPos = ""+tile.getRanX()+","+tile.getRanY();
				//System.out.println(randPos);
				//System.out.println(curPos);
				curPos = randPos;
				//System.out.println(curPos);
				curPosX = tile.getRanX();
				curPosY = tile.getRanY();
			
			}
				
		}
		TileObj.firstRun = false;
/*
		//Makes things visible or not
		CheckBox debug = new CheckBox("Debug\nToggle");
		debug.setIndeterminate(false);
		debug.setOnMouseClicked( e ->{
			if(TileObj.visable == false) {
				TileObj.visable = true;
			}
			else if(TileObj.visable == true) {
				TileObj.visable = false;
			}
			refreshMap();
		});
		//Group x = new Group();
		//x.getChildren().add(debug);
		worldMap.add(debug, columns/2, rows);
*/
		//worldMap.getChildren().addAll(map);
		return map;
	}
	
	//old move method, keeping as might be good for another project for something else/smaller(?)
	public static void move(Rectangle rec, String IDgiven, int maxX, int maxY) {
		Scanner reader = new Scanner(IDgiven);
		String line = reader.nextLine();
		String[] ID = line.split(",");
		String X = ID[0];
		String Y = ID[1];
		
		
		//System.out.println(rec.getId()+" Given by move "+maxX+","+maxY+" Is max");
		
		curPos = ""+X+","+Y;
		//System.out.println("The curPos is "+""+curPos+" ");
	}
	
	//used to decide where to move and uses other method to detect what player is on
	public static void moveTest(KeyEvent e) {
		
		//refreshUserPanel();
		if(e.getCode() == KeyCode.W) {
			if (!(curPosY <= 0)) {
				prevPosX = curPosX;
				prevPosY = curPosY;

				//System.out.println("The W key was pressed!, moveTest");
				curPosY--;
				// System.out.println(TileObj.ranY);
				TileObj.ranY = TileObj.ranY - 1;
				// System.out.println(TileObj.ranY);
				refreshMap();
				//refreshUserPanel();
				entityCheck(curPosX,curPosY);
				// rootPane.re
			}
		}
		if(e.getCode() == KeyCode.S) {
			if (!(curPosY >= mapMaxY-1)) {
				prevPosX = curPosX;
				prevPosY = curPosY;

				//System.out.println("The S key was pressed!, moveTest");
				curPosY++;
				TileObj.ranY = TileObj.ranY + 1;
				refreshMap();
				//refreshUserPanel();
				entityCheck(curPosX,curPosY);
				
			}
		}
		if(e.getCode() == KeyCode.A) {
			if (!(curPosX <= 0)) {
				prevPosX = curPosX;
				prevPosY = curPosY;

				//System.out.println("The A key was pressed!, moveTest");
				curPosX--;
				TileObj.ranX = TileObj.ranX - 1;
				refreshMap();
				//refreshUserPanel();
				entityCheck(curPosX,curPosY);
			}
		}
		if(e.getCode() == KeyCode.D) {
			if (!(curPosX >= mapMaxX-1)) {
				prevPosX = curPosX;
				prevPosY = curPosY;

				//System.out.println("The D key was pressed!, moveTest");
				curPosX++;
				TileObj.ranX = TileObj.ranX + 1;
				refreshMap();
				//refreshUserPanel();
				entityCheck(curPosX,curPosY);
			}
		}
	}
	
	//checks to see what is on the tile we are standing on and decides what to do (give ammo, game over? etc)
	public static boolean entityCheck(int x, int y) {
		boolean checkedOthers = false;
		//String message = "";
		
		if(TileObj.bossX == curPosX && TileObj.bossY == curPosY) {
			
			//System.out.println("This tile has a boss on it!");
			isAlive = false;
			worldMap.getChildren().clear();
			rootPane.getChildren().clear();
			Stage end = new Stage();
			primaryStageG.setScene(GameOver.endGame(end));
			PlayerInfo.userConStage.close();
			//detect += "A tile has a boss on it nearby! \n";
			return true;
		} 
		else if (checkedOthers == false) {
			
			for(int i = 0; i < TileObj.TrapsX.size(); i++) {
				if(TileObj.TrapsX.get(i) == curPosX &&
						TileObj.TrapsY.get(i) == curPosY) {
					
					//System.out.println("This tile has a Trap on it!");
					isAlive = false;
					worldMap.getChildren().clear();
					rootPane.getChildren().clear();
					Stage end = new Stage();
					primaryStageG.setScene(GameOver.endGame(end));
					PlayerInfo.userConStage.close();
					//detect += "You hear a trap prepare itself nearby! \n";
					return true;
				}
			}
			
			for(int i = 0; i < TileObj.CritX.size(); i++) {
				if(TileObj.CritX.get(i) == curPosX &&
						TileObj.CritY.get(i) == curPosY) {
					
					//System.out.println("This tile has a Critter on it!");
					curPosY = TileObj.myY;
					curPosX = TileObj.myX;
					TileObj.ranX = TileObj.startX;
					TileObj.ranY = TileObj.startY;
					refreshMap();
					
					//System.out.println("You ran back to the start!");
					//detect += "You hear a critter move around nearby! \n";
					return true;
				}
			}
			
			for(int i = 0; i < TileObj.ammoX.size(); i++) {
				if(TileObj.ammoX.get(i) == curPosX &&
						TileObj.ammoY.get(i) == curPosY) {
					
					//System.out.println("This tile has some ammo on it!");
					TileObj.ammoX.remove(i);
					TileObj.ammoY.remove(i);
					PlayerInfo.playerAmmo++;
					refreshUserPanel();
					
					
					//detect += "You sense ammo nearby! \n";
					return true;
				}
			}
			
		} 

		else 
			return false;
		
		return false;
	
	}
	
	//as the name says, can see what is in nearby tiles and adds
	//to a string to alert the player in the players "Shooting" stage / window
	public static void nearCheck(int x, int y) {

		if (TileObj.bossX == x && TileObj.bossY == y) {
			detect += "You sense a very strong creature nearby! \n";
		}

		for (int i = 0; i < TileObj.TrapsX.size(); i++) {
			if (TileObj.TrapsX.get(i) == x && TileObj.TrapsY.get(i) == y) {
				detect += "You hear mechanical sounds nearby! \n";
			}
		}

		for (int i = 0; i < TileObj.CritX.size(); i++) {
			if (TileObj.CritX.get(i) == x && TileObj.CritY.get(i) == y) {
				detect += "You smell something strange nearby! \n";
			}
		}

		for (int i = 0; i < TileObj.ammoX.size(); i++) {
			if (TileObj.ammoX.get(i) == x && TileObj.ammoY.get(i) == y) {
				detect += "You see more bombs nearby! \n";
			}
		}
	}
	
	//Utility
	
	
	//Refreshes the shooting window
	public static void refreshUserPanel() {
		PlayerInfo.getRootPane().getChildren().clear();
		PlayerInfo.userConStage.setScene(PlayerInfo.playerControl(PlayerInfo.userConStage));
		refreshMap();
	}
	//refreshes the map
	public static void refreshMap() {
		worldMap.getChildren().clear();
		rootPane.getChildren().clear();
		primaryStageG.setScene(mainScene(mapMaxX, mapMaxY,primaryStageG));
	}
	
	public static String getDetect() {
		return detect;
	}
	
	public static int getCurPosY() {
		return curPosY;
	}
	
	public static int getCurPosX() {
		return curPosX;
	}
	
	
	//this area is for the method that is used for shooting into the room over
	public static void checkBoss(int x, int y) {

		if (TileObj.bossX == x && TileObj.bossY == y) {
			detect += "YOU KILLED THE BOSS!";
			refreshUserPanel();
			Stage end = new Stage();
			primaryStageG.setScene(GameOver.endGameWin(end));
			PlayerInfo.userConStage.close();
			
		}

	}
	
	
	
	
	
	
}
