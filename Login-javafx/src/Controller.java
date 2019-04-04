import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;

public class Controller {
    public TextField mail;
    public TextField mail2;
    public PasswordField password;
    public PasswordField password1;
    public PasswordField password2;
    public Label status;
    public SimpleObjectProperty<Calendar> hoi = new SimpleObjectProperty<Calendar>(this, "hoi");
    Stage window = new Stage();
    MD5 cryp = new MD5();
    DBConnect connection = new DBConnect();

    public void LogInButtonClicked(ActionEvent event) throws IOException {
        String userPassword = password.getText();
        String userMail = mail.getText();

        if (connection.getData(userMail, cryp.encrypt(userPassword))) {
            Parent settings = FXMLLoader.load(getClass().getResource("nice.fxml"));
            Scene scene = new Scene(settings);
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    public void CreateAccountClicked(ActionEvent event) throws IOException {
        String userPassword1 = password1.getText();
        String userPassword2 = password2.getText();
        String userMail = mail2.getText();

        if (connection.comparePasswords(userPassword1, userPassword2)) {
            if (connection.mailCheck(userMail)) {
                if (connection.accountInsert(userPassword1, userMail)) {
                    Parent settings = FXMLLoader.load(getClass().getResource("Sign-in.fxml"));
                    Scene scene = new Scene(settings);
                    //This line gets the Stage information
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                    System.out.println("Account succesvol aangemaakt");
                } else {
                    System.out.println("Account aanmaken niet gelukt wegen onbekende reden");
                }
            } else {
                System.out.println("Mail adress reeds bekend");
            }
        } else {
            System.out.println("Wachtwoorden komen niet overeen");
        }


    }

    public void NoAccountClicked(ActionEvent event) throws IOException {
        Parent settings = FXMLLoader.load(getClass().getResource("Sign-up.fxml"));
        Scene scene = new Scene(settings);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void AccountClicked(ActionEvent event) throws IOException {

        Parent settings = FXMLLoader.load(getClass().getResource("Sign-in.fxml"));
        Scene scene = new Scene(settings);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    /*
    public void popupScreen() throws IOException {
        Parent settings = FXMLLoader.load(getClass().getResource("settings.fxml"));
        Scene scene = new Scene(settings);
        window.setScene(scene);
        window.setTitle("hoi");
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }*/
}
