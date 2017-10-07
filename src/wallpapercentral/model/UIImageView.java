package wallpapercentral.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;

public class UIImageView extends ImageView implements Listenable{

    private BooleanProperty selected = new SimpleBooleanProperty();
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public UIImageView() {super();}
    public UIImageView(String url) {
        super(url);
        selected.set(false);
//        setOnMouseClicked(event -> setSelected(true));
    }

    public BooleanProperty selectedProperty() {return selected;}
    public void setSelected(boolean sel) {
        System.out.println(selected.get());
        this.pcs.firePropertyChange("selected",this.selected.get(),sel);
        this.selected.set(sel);
    }

    public void cropImage(double originx, double originy, double x, double y) {

        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(getImage(), null);
        BufferedImage croppedBufferedImage;
        System.out.println("MinX: "+bufferedImage.getMinX()+" MinY: "+bufferedImage.getMinY()+
                "\nwidth: "+bufferedImage.getWidth()+" height: "+bufferedImage.getHeight());
        croppedBufferedImage = bufferedImage.getSubimage((int) Math.rint(originx), (int) Math.rint(originy),
                    (int) Math.rint(x),
                    (int) Math.rint(y));
        Image croppedImage = SwingFXUtils.toFXImage(croppedBufferedImage, null);
        setImage(croppedImage);
        //BufferedImage bufferedImage = (BufferedImage) wp.getImage();
    }

    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcs;
    }
}
