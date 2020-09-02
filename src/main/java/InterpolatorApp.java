import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class InterpolatorApp extends Application
{

    private final Timeline timeline = new Timeline();
    private Circle circle1;
    private Circle circle2;
    private Circle circle3;
    private Circle circle4;
    private Circle circle5;

    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(245, 230);
        root.setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        root.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        // create circles by method createMovingCircle listed below
        // default interpolator
        circle1 = createMovingCircle(Interpolator.LINEAR,
                1, 0.7, Color.RED);
        // circle slows down when reached both ends of trajectory
        circle2 = createMovingCircle(Interpolator.EASE_BOTH,
                2, 0.45, Color.VIOLET);
        // circle slows down in the beginning of animation
        circle3 = createMovingCircle(Interpolator.EASE_IN,
                3, 0.2, Color.BLUE);
        // circle slows down in the end of animation
        circle4 = createMovingCircle(Interpolator.EASE_OUT,
                4, 0.35, Color.YELLOW);
        // one can define own behaviour of interpolator by spline method
        circle5 = createMovingCircle(Interpolator.SPLINE(0.5, 0.1, 0.1, 0.5),
                5, 0.7, Color.GREEN);

        root.getChildren().addAll(circle1, circle2, circle3, circle4, circle5);
        return root;
    }

    private Circle createMovingCircle(Interpolator interpolator, int which,
                                      double opacity, Color color) {
        // create a transparent circle
        Circle circle = new Circle(45, 45, 40, color);
        // set initial opacity
        circle.setOpacity(opacity);
        circle.setCenterY((which * 35) + 5);
        // add effect
        circle.setEffect(new Lighting());
        // create a timeline for moving the circle
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        // create a keyValue for horizontal translation of circle to
        // the position 155px with given interpolator
        KeyValue keyValue = new KeyValue(circle.translateXProperty(), 155,
                interpolator);
        // create a keyFrame with duration 4s
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(4), keyValue);
        // add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
        return circle;
    }

    public void play() {
        timeline.play();
    }

    @Override
    public void stop() {
        timeline.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        play();
    }

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}