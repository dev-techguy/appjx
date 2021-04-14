package sample;

import com.sun.javafx.scene.ImageViewHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView bannerImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        File bannerFile = new File("images/banner.jpg");
        Image bannerImage = new Image(bannerFile.toURI().toString());
        bannerImageView.setImage(bannerImage);
    }

    public void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            validateLogin();
        } else {
            JOptionPane.showMessageDialog(null, "Please enter username and password.", "System Notification", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void validateLogin() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getConnection();

        String sqlQuery = "SELECT username,password FROM users WHERE username ='" + usernameTextField.getText() + "' && password = '" + passwordTextField.getText() + "' ";

        try {
            // database variables
            Statement statement = connectDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                try {
                    Stage stageClose = (Stage) loginButton.getScene().getWindow();
                    stageClose.close();


                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("dashboard.fxml"));
                    /*
                     * if "fx:controller" is not set in fxml
                     * fxmlLoader.setController(NewWindowController);
                     */
                    Scene scene = new Scene(fxmlLoader.load(), 630, 400);
                    Stage stage = new Stage();
                    stage.setTitle("DashBoard");
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed to create new Window.", e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wrong credentials", "System Notification", JOptionPane.WARNING_MESSAGE);
                passwordTextField.setText("");
            }

        } catch (Exception e) {
            e.getStackTrace();
            e.getCause();
        }
    }

}
