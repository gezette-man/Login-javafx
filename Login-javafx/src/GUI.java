import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {
    //Methods methods = new Methods();
    //DBConnect connection = new DBConnect();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent beginscherm = FXMLLoader.load(getClass().getResource("nice.fxml"));
        Scene scene = new Scene(beginscherm);
        primaryStage.setTitle("Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        //connection.connect();
        primaryStage.setOnCloseRequest(E -> {
            //methods.close();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
