package wallpapercentral.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WallpaperView extends ImageView implements PropertyChangeListener{

    private boolean isDesktopBackgroundImage;

    public WallpaperView() {super();}
    public WallpaperView(String url) {
        super(url);
    }
    public WallpaperView(Image img) {super(img);}
    public void addPropertyChangeListener(PropertyChangeListener listener) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void cropImage(double x, double y, double w, double h) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(getImage(), null);
        BufferedImage croppedBufferedImage = bufferedImage.getSubimage((int) Math.rint(x), (int) Math.rint(y),
                (int) Math.rint(w),
                (int) Math.rint(h));
        Image croppedImage = SwingFXUtils.toFXImage(croppedBufferedImage, null);
        setImage(croppedImage);

        //BufferedImage bufferedImage = (BufferedImage) wp.getImage();
    }
}
