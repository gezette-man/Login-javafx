import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class HI extends Application {
    private static final int height = 1400;
    private static final int width = 1400;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Box box = new Box(100, 100, 300);

        Group group = new Group(box);
        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(group, width, height, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        box.translateXProperty().set(width / 2);
        box.translateYProperty().set(height / 2);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    box.translateYProperty().set(box.getTranslateY() - 50);
                    break;
                case S:
                    box.translateYProperty().set(box.getTranslateY() + 50);
                    break;
                case A:
                    box.translateXProperty().set(box.getTranslateX() - 50);
                    break;
                case D:
                    box.translateXProperty().set(box.getTranslateX() + 50);
                    break;
            }


        });
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
