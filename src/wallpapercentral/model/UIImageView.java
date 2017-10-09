package wallpapercentral.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;

public class UIImageView extends ImageView implements Listenable{

    private BooleanProperty selected = new SimpleBooleanProperty(false);
    private BooleanProperty cropped = new SimpleBooleanProperty(false);
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public UIImageView() {
        super();
    }
    public UIImageView(String url) {
        super(url);
//        setOnMouseClicked(event -> setSelected(true));
    }

    public BooleanProperty selectedProperty() {return selected;}
    public BooleanProperty croppedProperty() {return cropped;}
    public void setSelected(boolean sel) {
        System.out.println(selected.get());
        this.pcs.firePropertyChange("selected",this.selected.get(),sel);
        this.selected.set(sel);
    }

    public void cropImage(double originx, double originy, double x, double y) {

        System.out.println("originx: "+originx+" originy: "+originy+"\nwidth: "+x+" height: "+y);
        System.out.println("(int) originx: "+(int)Math.rint(originx)+" (int) originy: "+(int)Math.rint(originy)+
                "\n(int) width: "+(int)Math.rint(x)+" (int) height: "+(int)Math.rint(y));

        final double boundsWidth = getBoundsInLocal().getWidth();
        final double widthRatio = getImage().getWidth()/boundsWidth;

        final double boundsHeight = getBoundsInLocal().getHeight();
        final double heightRatio = getImage().getHeight()/boundsHeight;

        WritableImage image = new WritableImage(getImage().getPixelReader(),(int) Math.rint(originx * widthRatio),
                (int) Math.rint(originy * heightRatio),
                (int) Math.rint(x * widthRatio),
                (int) Math.rint(y * heightRatio));
        System.out.println("WI width: "+image.getWidth()+" WI height: "+image.getHeight());
        setImage(image);
        cropped.set(true);
        showDimensions();
    }

    public void showDimensions() {
        System.out.println("width: "+getImage().getWidth()+" height: "+getImage().getHeight());
    }

    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcs;
    }
}
