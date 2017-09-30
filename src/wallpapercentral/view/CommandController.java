package wallpapercentral.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import wallpapercentral.model.WallpaperModel;

public class CommandController {
    @FXML
    private FlowPane commands;

    private WallpaperModel model;

    public CommandController() {
        model = new WallpaperModel();
    }

    public void initModel(WallpaperModel model) {this.model = model;}

    public void crop(ActionEvent actionEvent) {
    }
}
