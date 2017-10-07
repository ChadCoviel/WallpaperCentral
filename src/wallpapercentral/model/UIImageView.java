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

//        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(getImage(), null);
//        BufferedImage croppedBufferedImage;
//
//        System.out.println("MinX: "+bufferedImage.getMinX()+" MinY: "+bufferedImage.getMinY()+
//                "\nwidth: "+bufferedImage.getWidth()+" height: "+bufferedImage.getHeight());
//        croppedBufferedImage = bufferedImage.getSubimage((int) Math.rint(originx), (int) Math.rint(originy),
//                    (int) Math.rint(x),
//                    (int) Math.rint(y));
//        Image croppedImage = SwingFXUtils.toFXImage(croppedBufferedImage, null);
        System.out.println("originx: "+originx+" originy: "+originy+"\nwidth: "+x+" height: "+y);
        System.out.println("(int) originx: "+(int)Math.rint(originx)+" (int) originy: "+(int)Math.rint(originy)+
                "\n(int) width: "+(int)Math.rint(x)+" (int) height: "+(int)Math.rint(y));

        WritableImage image = new WritableImage(getImage().getPixelReader(),(int) Math.rint(originx), (int) Math.rint(originy),
                (int) Math.rint(x),
                (int) Math.rint(y));
        System.out.println("WI width: "+image.getWidth()+" WI height: "+image.getHeight());
//        Image croppedImage = SwingFXUtils.toFXImage(croppedBufferedImage, null);
        setImage(image);
        cropped.set(true);
        showDimensions();
        //BufferedImage bufferedImage = (BufferedImage) wp.getImage();
    }

    public void showDimensions() {
        System.out.println("width: "+getImage().getWidth()+" height: "+getImage().getHeight());
    }

    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcs;
    }
}
