package wallpapercentral.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;

public class WallpaperView extends ImageView implements Listenable{

    private boolean isSelected;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public WallpaperView(String url) {
        super(url);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        System.out.println("we made it");
        this.pcs.firePropertyChange("selected",this.isSelected,selected);
        this.isSelected = selected;
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

    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcs;
    }
}
