package wallpapercentral.model;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.List;

public class FileChooserUtils {

    public static List<File> openImagesDialog(Window w) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);

        List<File> files = fileChooser.showOpenMultipleDialog(w);
        return files;
    }
}
