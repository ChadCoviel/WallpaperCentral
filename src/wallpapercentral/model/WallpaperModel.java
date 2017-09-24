package wallpapercentral.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

//This is our model classes. It will contain the observable lists and will be able to load ans store data. This is like
//our server if this were a web application with server side requests/operations.
public class WallpaperModel extends java.util.Observable {
    private ObservableList<WallpaperView> wallpaperData;
    private ObjectProperty<WallpaperView> currentWallpaper;

    public WallpaperModel() {
        wallpaperData = FXCollections.observableArrayList();
        currentWallpaper = new SimpleObjectProperty<>(null);
    }

    //public get


    public void setWallpaperData(ObservableList<WallpaperView> wallpaperData) {
        this.wallpaperData = wallpaperData;
    }

    public void addWallpaperData(ObservableList<WallpaperView> wallpaperData) {
        System.out.println("in");
        this.wallpaperData.addAll(wallpaperData);
        System.out.println(this.wallpaperData);
    }

    public ObservableList<WallpaperView> getWallpaperData() {
        return wallpaperData;
    }
}
