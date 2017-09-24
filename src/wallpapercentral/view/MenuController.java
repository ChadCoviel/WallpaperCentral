package wallpapercentral.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import wallpapercentral.MainApp;
import wallpapercentral.model.WallpaperModel;

import java.io.IOException;

public class MenuController{

    @FXML
    private MenuBar menuBar;

    private Stage primaryStage;
    private BorderPane rootLayout;
    private WallpaperModel model;

    public MenuController() {

    }

    public BorderPane getRootLayout() { return this.rootLayout;}
    public void setRootCenter(Node n) {rootLayout.setCenter(n);}
    public void initModel(WallpaperModel model) {this.model = model;}
}
