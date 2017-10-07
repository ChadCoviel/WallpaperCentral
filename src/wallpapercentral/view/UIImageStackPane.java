package wallpapercentral.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import wallpapercentral.model.UIImageView;

public class UIImageStackPane extends StackPane{
    private Canvas canvas;
    private UIImageView img;
    private RubberbandSelection rubberband;

    public UIImageStackPane() {
        super();
        canvas = new Canvas();
        img = new UIImageView();
        rubberband = new RubberbandSelection(canvas);

        System.out.println("yuuuuuuuu");

        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());
        img.fitWidthProperty().bind(widthProperty());
        img.fitHeightProperty().bind(heightProperty());

        this.getChildren().addAll(img,canvas);
        rubberband.on();
    }

    public Image getImage() {return img.getImage();}
    public void setImage(Image img){this.img.setImage(img);}
    public RubberbandSelection getRubberband() {return rubberband;}
}
