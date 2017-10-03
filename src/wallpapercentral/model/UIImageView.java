package wallpapercentral.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;

public class UIImageView extends ImageView implements Listenable{

    private boolean isSelected;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public UIImageView(String url) {
        super(url);
        setOnMouseClicked(event -> setSelected(true));
    }

    public UIImageView(Image img) {
        super(img);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        System.out.println("we made it");
        this.pcs.firePropertyChange("selected",this.isSelected,selected);
        this.isSelected = selected;
    }

    public void cropImage(double originx, double originy, double x, double y) {

        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(getImage(), null);
        BufferedImage croppedBufferedImage;
        System.out.println("MinX: "+bufferedImage.getMinX()+" MinY: "+bufferedImage.getMinY()+
                "\nwidth: "+bufferedImage.getWidth()+" height: "+bufferedImage.getHeight());

        if(x - originx > 0 && originy - y > 0)
            croppedBufferedImage = bufferedImage.getSubimage((int) Math.rint(originx), (int) Math.rint(y),
                    (int) Math.rint(x - originx),
                    (int) Math.rint(originy - y));
        else if(x - originx > 0 && originy - y < 0)
            croppedBufferedImage = bufferedImage.getSubimage((int) Math.rint(originx), (int) Math.rint(originy),
                    (int) Math.rint(x),
                    (int) Math.rint(y));
        else if(x - originx < 0 && originy - y < 0)
            croppedBufferedImage = bufferedImage.getSubimage((int) Math.rint(x), (int) Math.rint(originy),
                    (int) Math.rint(originx - x),
                    (int) Math.rint(y - originy));
        else if(x - originx < 0 && originy - y > 0)
            croppedBufferedImage = bufferedImage.getSubimage((int) Math.rint(x), (int) Math.rint(y),
                    (int) Math.rint(originx - x),
                    (int) Math.rint(originy - y));
        else
            return;
        Image croppedImage = SwingFXUtils.toFXImage(croppedBufferedImage, null);
        setImage(croppedImage);
        //BufferedImage bufferedImage = (BufferedImage) wp.getImage();
    }

    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcs;
    }
}
