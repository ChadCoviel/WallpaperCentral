package wallpapercentral.editor;

import javafx.beans.binding.Bindings;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import wallpapercentral.model.UIImageView;

public class UIImageScrollPane extends ScrollPane{
    private Canvas canvas;
    private UIImageView img;
    private RubberbandSelection rubberband;
    private StackPane stack = new StackPane();

    public UIImageScrollPane() {
        super();
        System.out.println("Stack resizable: "+stack.isResizable());
        canvas = new ResizeableCanvas();
        img = new UIImageView();
        rubberband = new RubberbandSelection(canvas);

        System.out.println("yuuuuuuuu");

        img.setPreserveRatio(true);
        stack.getChildren().addAll(img, canvas);
        stack.minWidthProperty().bind(Bindings.createDoubleBinding(() ->
                getViewportBounds().getWidth(), viewportBoundsProperty()));


        this.setContent(stack);
        rubberband.on();
    }

    //Getters
    public Canvas getCanvas() {return canvas;}
    public UIImageView getImgView() {return img;}
    public Image getImg() {return img.getImage();}
    public RubberbandSelection getRubberband() {return rubberband;}

    //Void
    public void resizeCanvas() {
        canvas.setWidth(img.getImage().getWidth());
        canvas.setHeight(img.getImage().getHeight());
    }
}
