package wallpapercentral.editor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class ImageZoom {

    private final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
    private ScrollPane scrollPane;
//    private ImageView imgView;
    private InvalidationListener invalidationListener;
    private EventHandler scrollHandler;

    public ImageZoom(ImageView img) {
//        imgView = img;
        zoomProperty.addListener(createInvalidationListener(img));
    }

    public ImageZoom(ImageView img, ScrollPane scrollPane) {
        zoomProperty.addListener(createInvalidationListener(img));
        setScrollPane(scrollPane);
    }

    public void setScrollPane(ScrollPane scrollPane) {
        scrollPane.addEventFilter(ScrollEvent.ANY, createEventHandler(scrollPane));
    }

    private EventHandler createEventHandler(ScrollPane scrollPane) {
        return scrollHandler = new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaY() > 0) {
                    zoomProperty.set(zoomProperty.get() * 1.1);
                } else if (event.getDeltaY() < 0) {
                    zoomProperty.set(zoomProperty.get() / 1.1);
                }
            }
        };
    }

    private InvalidationListener createInvalidationListener(ImageView imgView) {
        return invalidationListener = new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                System.out.println("Zoom property: "+zoomProperty);
                imgView.setFitWidth(zoomProperty.get() * 4);
                imgView.setFitHeight(zoomProperty.get() * 3);
            }
        };
    }

    public void off() {
        zoomProperty.removeListener(invalidationListener);
        scrollPane.removeEventFilter(ScrollEvent.ANY,scrollHandler);
    }
}
