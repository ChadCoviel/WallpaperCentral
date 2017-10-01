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

    public static List<File> openVideosDialog(Window w) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        FileChooser.ExtensionFilter extFilterMKV = new FileChooser.ExtensionFilter("MKV files (*.mkv)", "*.MKV");
        FileChooser.ExtensionFilter extFilterMP4 = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
        FileChooser.ExtensionFilter extFilterAVI = new FileChooser.ExtensionFilter("AVI files (*.avi)", "*.AVI");
        fileChooser.getExtensionFilters().addAll(extFilterMKV, extFilterMP4, extFilterAVI);

        List<File> files = fileChooser.showOpenMultipleDialog(w);
        return files;
    }
}
