package com.mycompany.circlefx;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {


	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setResizable(false);

		// stage center
		double centerX = pane.getWidth() / 2.0;
		double centerY = pane.getHeight() / 2.0;

		// Big circle (path of pointCircle)
		double r = 100.0;
		Circle bigCircle = new Circle(centerX, centerY, r);
		bigCircle.setFill(null);
		bigCircle.setStroke(Color.RED);

		// the pointCircle
		double pointRadius = 15.0;
		Circle pointCircle = new Circle(centerX + r, centerY, pointRadius);
		pointCircle.setFill(Color.BLUE);

		//Event that will occurs when mousePosition is pressed
		pointCircle.setOnMousePressed((MouseEvent mouseEvent) -> {
			pointCircle.getCenterY();
			mouseEvent.getSceneY();
			pointCircle.getCenterX();
			mouseEvent.getSceneX();

		});
		//Event that will translate the point circle according to mousePosition position
		pointCircle.setOnMouseDragged((MouseEvent mouseEvent) -> {
			
			Point2D redCenter = new Point2D(bigCircle.getCenterX(), bigCircle.getCenterY());
			Point2D mousePosition = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			Point2D centerToMouse = mousePosition.subtract(redCenter);
			Point2D centerToNewPoint = centerToMouse.normalize().multiply(bigCircle.getRadius());
			Point2D updatedPoint = centerToNewPoint.add(redCenter);
			pointCircle.setCenterX(updatedPoint.getX());
			pointCircle.setCenterY(updatedPoint.getY());
			
			int colorX = (int) updatedPoint.getX() ;
			if(colorX - 255 > 0) {
				colorX -= 255;
			}
			int colorY = (int) updatedPoint.getY() ;
			if(colorY - 255 > 0) {
				colorY -= 255;
			}
			
			
			int colorZ = (int) updatedPoint.getY();
			if(colorZ - 255 > 0) {
				colorZ -= 255;
			}
			
			
			pointCircle.setFill(Color.rgb(colorX, colorY, colorZ));
		});

		pane.getChildren().addAll(bigCircle, pointCircle);

		primaryStage.setTitle("ProblemSolvingExamBCS345");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}