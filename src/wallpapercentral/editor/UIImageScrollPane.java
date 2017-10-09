package wallpapercentral.editor;

import javafx.beans.binding.Bindings;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import wallpapercentral.model.UIImageView;

public class UIImageScrollPane extends ScrollPane{
    private Canvas canvas = new ResizeableCanvas();
    private UIImageView img = new UIImageView();
    private RubberbandSelection rubberband = new RubberbandSelection(canvas);
    private ImageZoom zoom = new ImageZoom(img,this);
    private StackPane stack = new StackPane();
    private boolean isZoomable = false;

    public UIImageScrollPane() {
        super();

        System.out.println("yuuuuuuuu");

        img.setPreserveRatio(true);
        stack.getChildren().addAll(img, canvas);
        stack.minWidthProperty().bind(Bindings.createDoubleBinding(() ->
                getViewportBounds().getWidth(), viewportBoundsProperty()));
        stack.minHeightProperty().bind(Bindings.createDoubleBinding(() ->
                getViewportBounds().getHeight(), viewportBoundsProperty()));

        setContent(stack);
        rubberband.on();
        zoom.off();
    }

    //Getters
    public Canvas getCanvas() {return canvas;}
    public UIImageView getImgView() {return img;}
    public Image getImg() {return img.getImage();}
    public RubberbandSelection getRubberband() {return rubberband;}
    public ImageZoom getZoom(){return zoom;}
    public boolean isZoomable() {return isZoomable;}

    //Must be called whenever the image view has its image changed
    public void update() {
        canvas.setWidth(img.getBoundsInLocal().getWidth());
        canvas.setHeight(img.getBoundsInLocal().getHeight());
    }

    public void setZoom(boolean zoomable) {
        this.isZoomable = zoomable;
        if(isZoomable) {
            zoom.on();
            zoom.zoomProperty().addListener((observable, oldValue, newValue) -> update());
            rubberband.off();
        }
        else {
            zoom.off();
            resetImage();
        }
    }

    private void resetImage() {
        final double imageWidth = img.getImage().getWidth();
        final double imageHeight = img.getImage().getHeight();
        img.setFitWidth(imageWidth);
        img.setFitHeight(imageHeight);
        update();
    }
}
