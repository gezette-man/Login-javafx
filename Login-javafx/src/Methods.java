import javafx.scene.control.Label;

public class Methods {
    public Label status;

    static void close() {
        System.out.println("Je data is opgeslagen");

    }

    public void NoConnection() {
        status.setText("We kunnen geen verbinding met de database aanmaken");
    }
}