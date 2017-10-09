package wallpapercentral.editor;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ResizeableCanvas extends Canvas {

    public ResizeableCanvas() {
        super();
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    @Override
    public boolean isResizable(){return true;}

    @Override
    public double prefWidth(double width) {return getWidth();}

    @Override
    public double prefHeight(double height) {return getHeight();}

    private void draw() {
        double width = getWidth();
        double height = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
//
//        gc.setStroke(Color.RED);
//        gc.strokeLine(0, 0, width, height);
//        gc.strokeLine(0, height, width, 0);
    }
}
