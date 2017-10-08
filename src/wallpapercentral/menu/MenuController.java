package wallpapercentral.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.main.MainController;

import java.io.File;
import java.util.List;

public class MenuController{

    //IMPLEMENT ALL OF THE MENU OPTIONS IN THE FXML AND SPECIFY FUNCTION CALLS THERE AS WELL!!
    @FXML
    private MenuBar menuBar;

    private WallpaperModel model;
    private MainController main;

    public void initModel(WallpaperModel model) {this.model = model;}
    public void initMediator(MainController main) {this.main = main;}

    @FXML
    public void close(ActionEvent actionEvent) {
        menuBar.getScene().getWindow().hide();
    }

    @FXML
    public void load(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);

        List<File> files = fileChooser.showOpenMultipleDialog(menuBar.getScene().getWindow());
        model.addImageFiles(files);
    }

    @FXML
    public void save(ActionEvent actionEvent) {
    }
}
