package cs2012final;

/* Joseph Comeaux
 * CS2012
 * 05 & 06 
 * Description: This is a java project that creates a "popper" that shoots out random
 * colored confetti in random areas and colors. It uses gradients for some things as well.
 * Other Comments: Was fun to make, the only issue I had was trying to get the polygon to look
 * a certain way, ended up just rotating multiples of them as triangles 
 */
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class JavaFX  {


	// takes all of the other methods and the purpose is to pass the scene to the
	// start method
	public static Scene MainScene() {
		Group group1 = new Group();
		group1.getChildren().addAll(Background(), PopperPullLine(), 
				PopperMethods.PopperFire(), PopperMethods.PopperStuffs(), wooshLid(),
				confetCubes(), confetCircles(), name());
		//System.out.print(javafx.scene.text.Font.getFamilies());

		StackPane pane1 = new StackPane();
		pane1.getChildren().add(group1);

		Scene scene = new Scene(pane1, 600, 600);
		return scene;
	}
	
	public static StackPane MainStackPane() {
		Group group1 = new Group();
		group1.getChildren().addAll(Background(), PopperPullLine(), 
				PopperMethods.PopperFire(), PopperMethods.PopperStuffs(), wooshLid(),
				confetCubes(), confetCircles(), name());
		//System.out.print(javafx.scene.text.Font.getFamilies());

		StackPane pane1 = new StackPane();
		pane1.getChildren().add(group1);

		//Scene scene = new Scene(pane1, 600, 600);
		return pane1;
	}

	// text for my name at the bottom right
	public static Text name() {
		Text textName = new Text(500, 590, "Joseph Comeaux");
		textName.setFont(Font.font("Impact"));

		return textName;
	}

	// creates arcs that makes it look like the lid is flying away
	public static Group wooshLid() {
		Group group1 = new Group();

		Arc wooshLine = new Arc(350, 390, 200, 200, 50, 30);
		wooshLine.setType(ArcType.OPEN);
		wooshLine.setFill(Color.rgb(161, 178, 255));
		wooshLine.setStroke(Color.BLACK);
		group1.getChildren().add(wooshLine);

		Arc wooshLine2 = new Arc(330, 420, 200, 200, 50, 30);
		wooshLine2.setType(ArcType.OPEN);
		wooshLine2.setFill(Color.rgb(161, 178, 255));
		wooshLine2.setStroke(Color.BLACK);
		group1.getChildren().add(wooshLine2);

		Arc wooshLine3 = new Arc(310, 450, 200, 200, 50, 30);
		wooshLine3.setType(ArcType.OPEN);
		wooshLine3.setFill(Color.rgb(161, 178, 255));
		wooshLine3.setStroke(Color.BLACK);
		group1.getChildren().add(wooshLine3);

		return group1;
	}

	// just the background color
	public static Rectangle Background() {
		// background
		Rectangle r1 = new Rectangle(0, 0, 600, 600);
		Color colorBackground = Color.rgb(161, 178, 255);
		r1.setFill(colorBackground);
		r1.setRotate(90);
		// r1.setX(getWidth());
		return r1;
	}



	// returns a random color for things that need rgb, added later some old code
	// may still be around
	public static Color randomColor() {
		Random ranNum = new Random();
		int red = ranNum.nextInt(256);
		int green = ranNum.nextInt(256);
		int blue = ranNum.nextInt(256);
		Color rgbColor = Color.rgb(red, green, blue);
		return rgbColor;
	}

	// figured out how to use gradients, same as about but with gradients
	public static LinearGradient rainbowStyle() {

		Stop temp = new Stop(0, randomColor());

		Stop temp2 = new Stop(1, randomColor());

		Stop[] stops = new Stop[] { temp, temp2 };
		LinearGradient fancyColors = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
		return fancyColors;
	}

	

	// just the line that is supposed to be the string you pull to fire off the
	// popper
	public static Line PopperPullLine() {
		// Line on popper
		Line line1 = new Line(53, 520, 40, 575);
		line1.setStrokeWidth(5);
		line1.setStroke(Color.BROWN);
		return line1;
	}

	// creates tons of rectangles that pop up in random spots with random color
	// gradients
	public static Group confetCubes() {
		// rectangle confet
		Group confetGroup = new Group();
		for (int i = 0; i < 20; i++) {
			// Random ranNum = new Random();
			// int red = ranNum.nextInt(256);
			// int green = ranNum.nextInt(256);
			// int blue = ranNum.nextInt(256);
			int rotato = (int) (Math.random() * (160 - -160) + -160);

			int randX = (int) (Math.random() * (500 - 180) + 180);
			int randY = (int) (Math.random() * (500 - 30) + 30);

			Rectangle confet = new Rectangle(randX, randY, 50, 15);
			// Color rgbColor = Color.rgb(red,green,blue,1);
			confet.setFill(rainbowStyle());
			confet.setRotate(rotato);
			confetGroup.getChildren().add(confet);
		}
		return confetGroup;
	}

	// same as above but no gradient and are circles. They also come in random
	// sizes(within range) as well
	public static Group confetCircles() {
		// circle confet
		Group confetGroup = new Group();
		for (int i = 0; i < 15; i++) {
			Random ranNum = new Random();
			int red = ranNum.nextInt(256);
			int green = ranNum.nextInt(256);
			int blue = ranNum.nextInt(256);

			int randX = (int) (Math.random() * (500 - 180) + 180);
			int randY = (int) (Math.random() * (500 - 50) + 50);
			int randSize = (int) (Math.random() * (20 - 10) + 10);

			Circle confetCirc = new Circle(randX, randY, randSize);
			Color rgbColor = Color.rgb(red, green, blue, .5);
			confetCirc.setFill(rgbColor);
			confetGroup.getChildren().add(confetCirc);
		}
		return confetGroup;
	}

	
}