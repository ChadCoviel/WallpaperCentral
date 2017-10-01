package wallpapercentral.view;

import javafx.fxml.FXML;
import wallpapercentral.SceneController;
import wallpapercentral.model.WallpaperModel;

public class EditorController {

    private SceneController sceneController;

//    @FXML
//    public void initialize() {sceneController.addScene("editor",this);}

    public void initModel(WallpaperModel model) {

    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}
