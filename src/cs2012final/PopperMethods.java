package cs2012final;
/* Joseph Comeaux
 * CS2012
 * 05 & 06 
 * Description: This is a java project that creates the main part of the popper
 * Other Comments: 
 */

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;

public class PopperMethods {
	// arcs for the fire/POP effect behind the popper
	public static Group PopperFire() {

		// fire effect for popper "Bang"
		Group group1 = new Group();

		Arc popperThing = new Arc(100, 425, 200, 200, 10, 30);
		popperThing.setType(ArcType.ROUND);
		popperThing.setFill(Color.DARKRED);
		popperThing.setRotate(180);
		group1.getChildren().add(popperThing);

		Arc popperThing2 = new Arc(200, 400, 200, 200, 90, 30);
		popperThing2.setType(ArcType.ROUND);
		popperThing2.setFill(Color.DARKRED);
		popperThing2.setRotate(180);
		group1.getChildren().add(popperThing2);

		Arc popperThing3 = new Arc(80, 410, 200, 200, 40, 30);
		popperThing3.setType(ArcType.ROUND);
		popperThing3.setFill(Color.DARKRED);
		popperThing3.setRotate(180);
		group1.getChildren().add(popperThing3);

		Arc popperThing4 = new Arc(100, 420, 190, 190, 13.5, 25);
		popperThing4.setType(ArcType.ROUND);
		popperThing4.setFill(Color.ORANGERED);
		popperThing4.setRotate(180);
		group1.getChildren().add(popperThing4);

		Arc popperThing5 = new Arc(190, 412, 200, 200, 90.5, 25);
		popperThing5.setType(ArcType.ROUND);
		popperThing5.setFill(Color.ORANGERED);
		popperThing5.setRotate(180);
		group1.getChildren().add(popperThing5);

		Arc popperThing6 = new Arc(80, 415, 200, 200, 43, 25);
		popperThing6.setType(ArcType.ROUND);
		popperThing6.setFill(Color.ORANGERED);
		popperThing6.setRotate(180);
		group1.getChildren().add(popperThing6);

		return group1;

	}

	// Anything else to do with the popper, like the cap or the popper "container"
	// loooking arc for the popper
	public static Group PopperStuffs() {
		// popper stuff
		Group group1 = new Group();

		Stop[] gradColor01 = new Stop[] { new Stop(0, JavaFX.randomColor()), new Stop(1, JavaFX.randomColor()) };
		LinearGradient Stripes = new LinearGradient(5, 0, 0, 2, false, CycleMethod.REFLECT, gradColor01);

		Arc popperThing = new Arc(50, 525, 200, 200, 40, 40);
		popperThing.setType(ArcType.ROUND);
		popperThing.setFill(Stripes);
		group1.getChildren().add(popperThing);

		Arc popperHole = new Arc(50, 525, 195, 195, 41, 38);
		popperHole.setType(ArcType.CHORD);
		popperHole.setFill(Color.BLACK);
		group1.getChildren().add(popperHole);

		Ellipse capOutter = new Ellipse(500, 300, 60, 40);
		capOutter.setRotate(120);
		Color colorOutterPopperCap = Color.rgb(107, 72, 35);
		capOutter.setFill(colorOutterPopperCap);
		group1.getChildren().add(capOutter);

		Stop[] gradColor = new Stop[] { new Stop(0, Color.SILVER), new Stop(1, Color.rgb(120, 120, 120)) };
		LinearGradient shinyLid = new LinearGradient(0, 0, 20, 1, false, CycleMethod.REFLECT, gradColor);

		Ellipse capInner = new Ellipse(500, 300, 55, 35);
		capInner.setRotate(120);
		capInner.setFill(shinyLid);
		group1.getChildren().add(capInner);

		for (int x = 0; x < 9; x++) {

			Polygon BurntThingOutter = new Polygon();
			BurntThingOutter.setFill(Color.BLACK);
			BurntThingOutter.setStroke(Color.BLACK);
			ObservableList<Double> points = BurntThingOutter.getPoints();
			double CntX = 492, CntY = 300;

			for (int i = 0; i < 3; i++) {
				points.add(CntX + 25 * Math.cos(((4 * i * Math.PI) / 6)));
				points.add(CntY - 25 * Math.sin(((4 * i * Math.PI) / 6)));
			}
			BurntThingOutter.setRotate(30 * x);
			group1.getChildren().add(BurntThingOutter);

		}

		return group1;
	}
}
