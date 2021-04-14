package sample.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    @FXML
    private ImageView dashImage;
    @FXML
    private MenuItem exitApp;

    public void exitMenuItemAction(ActionEvent event) {
        exitApp.setOnAction((ActionEvent t) -> {
            System.exit(0);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        File bannerFile = new File("images/dashboard.png");
        Image bannerImage = new Image(bannerFile.toURI().toString());
        dashImage.setImage(bannerImage);
    }
}
