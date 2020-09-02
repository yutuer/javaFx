package helloworld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class EventTest extends Application
{

    @Override
    public void start(Stage primaryStage)
    {
        Circle circle = new Circle(50, 50, 50);
        circle.setFill(Color.CORAL);

        Rectangle rect = new Rectangle(100, 100);
        rect.setFill(Color.TAN);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(circle, rect);

        Scene scene = new Scene(hBox, 300, 250);

        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            handleMouseEvent(e);
        });

        primaryStage.setTitle("Event Test!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleMouseEvent(MouseEvent e)
    {
        String source = e.getSource().getClass().getSimpleName();
        String target = e.getTarget().getClass().getSimpleName();

        double sourceX = e.getX();
        double sourceY = e.getY();

        // Mouse location relative to the scene
        double sceneX = e.getSceneX();
        double sceneY = e.getSceneY();

        // Mouse location relative to the screen
        double screenX = e.getScreenX();
        double screenY = e.getScreenY();

        System.out.println("Source=" + source + ", Target=" + target +
                ", Location:" +
                " source(" + sourceX + ", " + sourceY + ")" +
                ", scene(" + sceneX + ", " + sceneY + ")" +
                ", screen(" + screenX + ", " + screenY + ")");
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}