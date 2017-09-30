package wallpapercentral.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

//This is our model classes. It will contain the observable lists and will be able to load ans store data. This is like
//our server if this were a web application with server side requests/operations.
public class WallpaperModel extends java.util.Observable {
    private ObservableList<WallpaperView> wallpaperData;
    private ObjectProperty<WallpaperView> currentWallpaper;
    private ObservableList<HBox> wallpaperRows;

    public WallpaperModel() {
        wallpaperData = FXCollections.observableArrayList();
        currentWallpaper = new SimpleObjectProperty<>(null);
        wallpaperRows = FXCollections.observableArrayList();
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

    //public getRecentlyAdded
    public ObservableList<WallpaperView> getWallpaperData() {
        return wallpaperData;
    }
    public ObservableList<HBox> getWallpaperView() {
        return wallpaperRows;
    }
}
