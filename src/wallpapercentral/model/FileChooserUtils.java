package wallpapercentral.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public final class FileChooserUtils {

    private FileChooserUtils() throws Exception{}

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

    public static void saveImage(Image image, Window w) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(w);
        if (file == null)
            return;
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", file);
            System.out.println("File saved in: "+file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
