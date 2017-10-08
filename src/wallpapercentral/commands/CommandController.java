package wallpapercentral.commands;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.main.MainController;

public class CommandController {
    @FXML
    private FlowPane commands;

    private WallpaperModel model;
    private MainController main;

    public CommandController() {
        model = new WallpaperModel();
    }

    public void initModel(WallpaperModel model) {this.model = model;}
    public void initMediator(MainController main) {this.main = main;}
}
