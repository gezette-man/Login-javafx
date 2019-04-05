import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class HI extends Application {
    private static final int height = 1400;
    private static final int width = 1400;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;

    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    @Override
    public void start(Stage primaryStage) throws Exception {

        ObjModelImporter importer = new ObjModelImporter();
        importer.read("BMj.obj");
        Node[] bmw = importer.getImport();
        importer.close();
        Group group = new Group(bmw);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(group, width, height, true, SceneAntialiasing.DISABLED);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        group.translateXProperty().set(width / 2);
        group.translateYProperty().set(height / 2);


        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    group.translateYProperty().set(group.getTranslateY() - 150);
                    break;
                case S:
                    group.translateYProperty().set(group.getTranslateY() + 150);
                    break;
                case A:
                    group.translateXProperty().set(group.getTranslateX() - 150);
                    break;
                case D:
                    group.translateXProperty().set(group.getTranslateX() + 150);
                    break;
            }
        });
        Rotate xRotate;
        Rotate yRotate;

        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );

        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);
        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });
        scene.setOnMouseDragged(event -> {

            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });

        primaryStage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() - delta);
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
