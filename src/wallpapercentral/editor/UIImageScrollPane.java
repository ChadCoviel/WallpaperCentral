package wallpapercentral.editor;

import javafx.beans.binding.Bindings;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import wallpapercentral.model.UIImageView;

public class UIImageScrollPane extends ScrollPane{
    private Canvas canvas;
    private UIImageView img;
    private RubberbandSelection rubberband;
    private ImageZoom zoom;
    private StackPane stack = new StackPane();
    private boolean isZoomable = false;

//    private

    public UIImageScrollPane() {
        super();
        canvas = new ResizeableCanvas();
        img = new UIImageView();
        rubberband = new RubberbandSelection(canvas);

        System.out.println("yuuuuuuuu");

//        img.fitWidthProperty().bind()
        img.setPreserveRatio(true);
        stack.getChildren().addAll(img, canvas);
        stack.minWidthProperty().bind(Bindings.createDoubleBinding(() ->
                getViewportBounds().getWidth(), viewportBoundsProperty()));
        stack.minHeightProperty().bind(Bindings.createDoubleBinding(() ->
                getViewportBounds().getHeight(), viewportBoundsProperty()));

        this.setContent(stack);
        rubberband.on();
    }

    //Getters
    public Canvas getCanvas() {return canvas;}
    public UIImageView getImgView() {return img;}
    public Image getImg() {return img.getImage();}
    public RubberbandSelection getRubberband() {return rubberband;}
    public boolean isZoomable() {return isZoomable;}

    //Must be called whenever the image view has its image changed
    public void update() {
        canvas.setWidth(img.getImage().getWidth());
        canvas.setHeight(img.getImage().getHeight());
        img.getBoundsInParent();
//        canvas.widthProperty().bind(img.fitWidthProperty());
//        canvas.heightProperty().bind(img.fitHeightProperty());
    }

    public void setZoom(boolean zoomable) {
        this.isZoomable = zoomable;
        if(isZoomable) {
            zoom = new ImageZoom(img,this);
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
