package wallpapercentral.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public final class ImageUtils {

    public static Image cropFXImage(Image img, double x, double y, double w, double h) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(img, null);
        BufferedImage croppedBufferedImage = bufferedImage.getSubimage((int) Math.rint(x), (int) Math.rint(y),
                (int) Math.rint(w),
                (int) Math.rint(h));
        return SwingFXUtils.toFXImage(croppedBufferedImage, null);
    }
}
