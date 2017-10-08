package wallpapercentral.editor;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import wallpapercentral.model.UIImageView;

public class UIImageStackPane extends StackPane{
    private Canvas canvas;
    private UIImageView img;
    private RubberbandSelection rubberband;
    private StackPane stack = new StackPane();
    private ScrollPane scroll;
    private ScrollPane s2;

    public UIImageStackPane() {
        super();
        canvas = new Canvas();
        img = new UIImageView();
        scroll = new ScrollPane();
        s2 = new ScrollPane();
        rubberband = new RubberbandSelection(canvas);

        System.out.println("yuuuuuuuu");

        canvas.widthProperty().bind(img.fitWidthProperty());
        canvas.heightProperty().bind(img.fitHeightProperty());
//        img.fitWidthProperty().bind(widthProperty());
//        img.fitHeightProperty().bind(heightProperty());
        img.setPickOnBounds(true);
        img.setPreserveRatio(true);
        stack.getChildren().addAll(img, canvas);
        scroll.setContent(stack);
//        s2.setContent(canvas);

        this.getChildren().addAll(stack);
        rubberband.on();
    }

    public Canvas getCanvas() {return canvas;}
    public UIImageView getImgView() {return img;}
    public Image getImg() {return img.getImage();}
    public RubberbandSelection getRubberband() {return rubberband;}
}
