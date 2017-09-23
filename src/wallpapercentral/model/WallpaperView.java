package wallpapercentral.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WallpaperView extends ImageView implements PropertyChangeListener{

//    public Wallpaper() {
//        this();
//    }
    //private ImageView imgView;

    private boolean isDesktopBackgroundImage;

    public WallpaperView() {super();}
    public WallpaperView(String url) {
        super(url);
    }
    public WallpaperView(Image img) {super(img);}
    //public static crop() {}
    //public static getImage() {}

    /*
    ***The model is in charge of adding all listeners to itself. It is not aware of who/what is listening.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

//    public void addPropertyChangeListener(PropertyChangeListener listener) {
//        propertyChangeSupport.addPropertyChangeListener(listener);
//    }
//
//    public void removePropertyChangeListener(PropertyChangeListener listener) {
//        propertyChangeSupport.removePropertyChangeListener(listener);
//    }
//
//    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
//        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
//    }
}
