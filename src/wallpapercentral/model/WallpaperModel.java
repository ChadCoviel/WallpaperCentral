package wallpapercentral.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//This is our model classes. It will contain the observable lists and will be able to load ans store data. This is like
//our server if this were a web application with server side requests/operations.
public class WallpaperModel {
    private ObservableList<WallpaperView> wallpaperData = FXCollections.observableArrayList();
    private final ObjectProperty<WallpaperView> currentWallpaper = new SimpleObjectProperty<>(null);

}
