package wallpapercentral.main;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import wallpapercentral.app.SceneController;
import wallpapercentral.commands.CommandController;
import wallpapercentral.content.ContentController;
import wallpapercentral.menu.MenuController;
import wallpapercentral.model.WallpaperModel;

public class MainController {
    @FXML private BorderPane main;
    @FXML private AnchorPane commands;
    @FXML private AnchorPane contents;
    @FXML private MenuBar menu;
    @FXML private MenuController menuController;
    @FXML private ContentController contentsController;
    @FXML private CommandController commandsController;

    private WallpaperModel model;
    private SceneController sceneController;

    @FXML
    public void initialize() {
        menuController.initMediator(this);
        commandsController.initMediator(this);
        contentsController.initMediator(this);
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        menuController.initModel(model);
        commandsController.initModel(model);
        contentsController.initModel(model);
    }

    public void changeToEditor() {
        sceneController.activate("editor");
    }
//    public void diaplayContent() { sceneController.activate("content");}
    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}
