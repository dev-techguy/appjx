package sample;

import com.sun.javafx.scene.ImageViewHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

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

        String slq_query = "SELECT username,password FROM users WHERE username ='" + usernameTextField.getText() + "' && password = '" + passwordTextField.getText() + "' ";

        try {
            ResultSet resultSet = connectDB.createStatement().executeQuery(slq_query);

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Welcome...", "System Notification", JOptionPane.INFORMATION_MESSAGE);
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
