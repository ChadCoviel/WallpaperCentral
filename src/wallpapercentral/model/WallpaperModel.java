package wallpapercentral.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

import java.util.Iterator;

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

    public void addWallpaperData(ObservableList<WallpaperView> wallpaperData) {
        System.out.println("in");
        this.wallpaperData.addAll(wallpaperData);
        System.out.println(this.wallpaperData);
        updateWallpaperRows();

    }

    public void addWallpaper(WallpaperView wp) {
        this.wallpaperData.add(wp);
    }

    //public getRecentlyAdded
    public ObservableList<WallpaperView> getWallpaperData() {
        return wallpaperData;
    }
    public ObservableList<HBox> getWallpaperView() {
        return wallpaperRows;
    }

    private void updateWallpaperRows() {
        if (wallpaperRows.size() != 0) {

        }
        else {

        }

    }
}
