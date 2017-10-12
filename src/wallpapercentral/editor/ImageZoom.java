package wallpapercentral.editor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;

public class ImageZoom implements Toggleable{

    private final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
    private ScrollPane scrollPane;
    private ImageView imgView;
    private InvalidationListener invalidationListener;
    private EventHandler scrollHandler;
//    public enum ZoomConstant {MAX_ZOOM, MIN_ZOOM;};
    private final double MAX_ZOOM = 1000.0;
    private final double MIN_ZOOM = 30.0;

    public ImageZoom(ImageView img) {
        imgView = img;
        on();
    }

    public ImageZoom(ImageView img, ScrollPane scrollPane) {
        imgView = img;
        this.scrollPane = scrollPane;
        on();
    }

    private EventHandler createEventHandler(ScrollPane scrollPane) {
        return scrollHandler = new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                System.out.println("Zoom property: "+zoomProperty.doubleValue());
                if (event.getDeltaY() > 0 && zoomProperty.doubleValue() < MAX_ZOOM) {
                    zoomProperty.set(zoomProperty.get() * 1.1);
                } else if (event.getDeltaY() < 0 && zoomProperty.doubleValue() > MIN_ZOOM) {
                    zoomProperty.set(zoomProperty.get() / 1.1);
                }
            }
        };
    }

    private InvalidationListener createInvalidationListener(ImageView imgView) {
        return invalidationListener = new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
//                System.out.println("Zoom property: "+zoomProperty);
                imgView.setFitWidth(zoomProperty.get() * 4);
                imgView.setFitHeight(zoomProperty.get() * 3);
            }
        };
    }

    @Override
    public void on() {
        zoomProperty.addListener(createInvalidationListener(imgView));
        scrollPane.addEventFilter(ScrollEvent.ANY, createEventHandler(scrollPane));
    }

    @Override
    public void off() {
        zoomProperty.removeListener(invalidationListener);
        scrollPane.removeEventFilter(ScrollEvent.ANY,scrollHandler);
    }
}
