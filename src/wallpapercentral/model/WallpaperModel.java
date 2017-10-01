package wallpapercentral.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class WallpaperModel extends java.util.Observable {

    private ObservableList<WallpaperView> wallpaperData;

    public WallpaperModel() {
        wallpaperData = FXCollections.observableArrayList();
        System.out.println(wallpaperData);
    }

    public void addImageFiles(List<File> files) {
        try {
            //files.forEach();
            for (File file: files) {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                WallpaperView wp = new WallpaperView(file.toURI().toURL().toString());
                //WallpaperView wp = new WallpaperView(image);
                wp.setPreserveRatio(false);
                wp.setCache(true);
                wp.setCacheHint(CacheHint.SPEED);
                wp.setFitHeight(200);
                wp.setFitWidth(200);
                //myImageView.setImage(image);
                wallpaperData.add(wp);
            }
        } catch (IOException ex) {
            //Logger.getLogger(FileChooserSample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<WallpaperView> getWallpaperData() {
        return wallpaperData;
    }
}
