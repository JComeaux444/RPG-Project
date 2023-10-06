/* Joseph Comeaux
 * CS2012
 * 05 & 06 
 * Description: Class that is in charge of each tile object extends rectangle because I felt 
 * that would be smart to do, probably not. Each tile has info about what it is and makes
 * sure nothing is placed on top of it if it has something already, most of the way the game
 * works is through this method including debugging 
 * Other Comments: Some old code probably left in.
 */
package cs2012final;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TileObj extends Rectangle {
	
	public static boolean firstRun = true;
	
	static int rows = 5;
	static int columns = 5;
	int maxX = columns - 1;
	int maxY = rows - 1;
	
	//used for how big the map is 
	//once it reaches the end of going through each tile object
	//iteration kinda
	static int myX = 0;
	static int myY = 0;
	
	double Hgt = 500;
	double Wdt = 500;
	
	
	double subByH = Hgt/rows;
	double subByW = Wdt/columns;
	double tileSizeH = (Hgt/rows);
	double tileSizeW = (Wdt/columns);

	
	//For debugging
	public static boolean visable = false;
	
	//used as cords
	static boolean randomRan = false;
	//starts off random but this is actually where the player is
	public static int ranX = 0;
	public static int ranY = 0;
	
	
	public static int startY = 0;
	public static int startX = 0;
	
	
	public boolean playerHere = false;
	
	//for boss
	static boolean randomRanBoss = false;
	static int bossX = 0;
	static int bossY = 0;
	
	//track if entity/object exists
	public boolean hasEntity = false;

	//for Traps other ents
	static boolean randomRanTraps = false;
	static ArrayList<Integer> TrapsX = new ArrayList<>();
	static ArrayList<Integer> TrapsY = new ArrayList<>();
	
	static boolean randomRanCritters = false;
	static ArrayList<Integer> CritX = new ArrayList<>();
	static ArrayList<Integer> CritY = new ArrayList<>();
	
	static boolean randomRanAmmo = false;
	static ArrayList<Integer> ammoX = new ArrayList<>();
	static ArrayList<Integer> ammoY = new ArrayList<>();
	
	//Object used as tiles, extends rectangle 
	public TileObj(int gridX, int gridY, int curRow, int curCol,Stage primaryStage) {
		
		TileObj.rows = gridY;
		TileObj.columns = gridX;
		tileSizeH = (Hgt/rows);
		tileSizeW = (Wdt/columns);
		
		ranNum();
		ranNumBoss();
		ranNumTraps();
		ranNumCreatures();
		ranNumAmmo();
		
		myY = curRow;
		myX = curCol;
		
		//Rectangle tile = new Rectangle();

		this.setX((this.tileSizeW) * (curCol + 1));
		this.setY((this.tileSizeH) * (curRow + 1));
		this.setWidth(this.tileSizeW - (1));
		this.setHeight(this.tileSizeH - (1));
		this.setArcHeight(10);
		this.setArcWidth(10);
		

		this.setFill(Color.GREY);
		this.hasEntity = false;

		this.setId("" + curCol + "," + curRow);

		//System.out.println(this.getId());

		//This area of this method places all the entities, player, ammo, boss, etc
		//FIX doesn't correctly place things around player!
		if (curRow == ranY && curCol == ranX) {
			this.setFill(Color.AQUA);
			if(firstRun) {
			this.playerHere = true;
			//System.out.print("Player here");
			}
			//System.out.print("Player here");
			
		}
		
		
			
		
		
		if (curRow == bossY && curCol == bossX) {
			//entityCheck(curCol,curRow);
			if(this.playerHere == true) {
				
				randomRanBoss=false;
				ranNumBoss();
				
				primaryStage.setScene(Tiles.mainScene(gridX, gridY,primaryStage));
			}
			else {
				this.setFill(Color.RED);
				this.hasEntity = true;
				checkVisable(this);

			}
		}
		for(int i = 0; i < TrapsX.size(); i++) {
			if(TileObj.TrapsX.get(i) == curCol && TileObj.TrapsY.get(i) == curRow) {
				
				if((this.playerHere || hasEnt())) {
					randomRanTraps = false;
					ranNumTraps();
					primaryStage.setScene(Tiles.mainScene(gridX, gridY,primaryStage));
				}
				else {
					this.setFill(Color.BLACK);
					this.hasEntity = true;
					checkVisable(this);
					
				}
			}
		}
		for(int i = 0; i < CritX.size(); i++) {
			if(TileObj.CritX.get(i) == curCol && TileObj.CritY.get(i) == curRow) {
				
				if((this.playerHere || hasEnt())) {
					randomRanCritters = false;
					ranNumCreatures();
					primaryStage.setScene(Tiles.mainScene(gridX, gridY,primaryStage));
				}
				else {
					this.setFill(Color.PINK);
					this.hasEntity = true;
					checkVisable(this);
					
				}
			}
		}
		for(int i = 0; i < ammoX.size(); i++) {
			if(TileObj.ammoX.get(i) == curCol && TileObj.ammoY.get(i) == curRow) {
				
					
				if((this.playerHere || hasEnt())) {
					
					randomRanAmmo = false;
					ranNumAmmo();
					primaryStage.setScene(Tiles.mainScene(gridX, gridY,primaryStage));
					
				}
				else {
					this.setFill(Color.GREENYELLOW);
					this.hasEntity = true;
					checkVisable(this);
				}
				
				
				
			}
		}
		
		//if(this.playerHere == true) {
			//System.out.print("Player here");
		//}
		
		
		//Needed to keep track of what is needed the first time this is ran!
		//This method also runs each time the player moves and checks things!
		if(gridX - 1  == curRow && gridY - 1 == curCol) {
			//System.out.println("This should print one time "+curRow+" , "+curCol);
		}
		//firstRun = false;
	}
	
	//checks if this area / rectangle is already an entity
	public boolean hasEnt() {
		
		if (this.hasEntity == true) {
			return true;
		}
		else
			return false;
	}
	
	
	
	//makes a random pos for player 
	public void ranNum() {
		
		if (randomRan == false) {
			final Random ranNum = new Random();
			final int ranX = ranNum.nextInt(columns);
			final int ranY = ranNum.nextInt(rows);
			//String randPos = ""+ranX+","+ranY;
			TileObj.ranX = ranX;
			TileObj.ranY = ranY;
			TileObj.startY = ranY;
			TileObj.startX = ranX;
			randomRan = true;
			if (myY == ranY && myX == ranX) {
				if(firstRun == true) {
					this.playerHere = true;
				}
			}
			
		}
	}
	//Random position for boss
	public static void ranNumBoss() {
		
		if (randomRanBoss == false) {
			final Random ranNum = new Random();
			final int ranX = ranNum.nextInt(columns);
			final int ranY = ranNum.nextInt(rows);
			//String randPos = ""+ranX+","+ranY;
			TileObj.bossX = ranX;
			TileObj.bossY = ranY;
			randomRanBoss = true;
		}
	}
	
	//random positions for traps
	public static void ranNumTraps() {
		
		if (randomRanTraps == false) {
			TileObj.TrapsX.clear();
			TileObj.TrapsY.clear();
			for (int i = 0; i < (3 + addMoreEnt()); i++) {

				Random ranNum = new Random();
				final int ranX = ranNum.nextInt(columns);
				final int ranY = ranNum.nextInt(rows);
				// String randPos = ""+ranX+","+ranY;
				TileObj.TrapsX.add(ranX);
				TileObj.TrapsY.add(ranY);
				

			}
			randomRanTraps = true;
		}
	}
	
	//(old note)
	//any 000 means to put a method there that returns int, based on size of board
	
	
	//random positions for creatures
	public static void ranNumCreatures() {
		
		if (randomRanCritters == false) {
			TileObj.CritX.clear();
			TileObj.CritY.clear();
			for (int i = 0; i < (3 + addMoreEnt()); i++) {

				Random ranNum = new Random();
				final int ranX = ranNum.nextInt(columns);
				final int ranY = ranNum.nextInt(rows);
				// String randPos = ""+ranX+","+ranY;
				TileObj.CritX.add(ranX);
				TileObj.CritY.add(ranY);
				

			}
			randomRanCritters = true;
		}
	}
	
	
	
	
	public static void ranNumAmmo() {
		
		if (randomRanAmmo == false) {
			TileObj.ammoX.clear();
			TileObj.ammoY.clear();
			for (int i = 0; i < (2 + addMoreEnt()); i++) {

				Random ranNum = new Random();
				final int ranX = ranNum.nextInt(columns);
				final int ranY = ranNum.nextInt(rows);
				// String randPos = ""+ranX+","+ranY;
				TileObj.ammoX.add(ranX);
				TileObj.ammoY.add(ranY);
				

			}
			randomRanAmmo = true;
		}
	}
	
	
	public static int addMoreEnt() {
		int addMe = ((columns*rows)/25) - 1;
		return addMe;
	}
	
	
	
	//checks to see if boolean Visible is checked yes or not, allows map to change and
	//see what is where
	public static void checkVisable(Rectangle x) {
		if(visable == false) {
			x.setFill(Color.GREY);
		}
		//x.setFill(Color.GREY);
	}
	
	
	// Below Are not used
	//getters
	public int getRanX() {
		return this.ranX;
	}
	
	public int getRanY() {
		return this.ranY;
	}
	
	
	//setters
	
	
	public void setColorGrey() {
		this.setFill(Color.GREY);
	}
	
	public void setColorIAmHere() {
		this.setFill(Color.AQUA);
	}
	
	public void setRandomRanTrue() {
		randomRan = true;
	}
	
	
	public static void test(boolean x) {
		//SetHasEnt(x);
	}
	
	public void setHasEnt(boolean x) {
		
		this.hasEntity = x;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
