package wallpapercentral.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import wallpapercentral.model.WallpaperModel;

import java.util.HashMap;

public class MainController {
    @FXML private BorderPane main;
    @FXML private AnchorPane commands;
    @FXML private AnchorPane contents;
    @FXML private MenuBar menu;
    @FXML private MenuController menuController;
    @FXML private ContentController contentsController;
    @FXML private CommandController commandsController;
    private WallpaperModel model;

    @FXML
    public void initialize() {
//        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/Menu.fxml"));
        //menuController.initModel();
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        menuController.initModel(model);
        commandsController.initModel(model);
        contentsController.initModel(model);
    }
}
