package wallpapercentral.model;

import javafx.scene.image.Image;

public class Wallpaper extends Image{

//    public Wallpaper() {
//        this();
//    }

    private boolean isDesktopBackgroundImage;

    public Wallpaper(String url) {
        super(url);
    }
}
