package wallpapercentral.view;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class RubberbandSelection {
    private Point2D anchor;
    private Point2D currentPoint;
    private GraphicsContext gc;
    private Canvas canvas;

    public RubberbandSelection(Canvas c) {
        this.canvas = c;

        gc = canvas.getGraphicsContext2D();
        gc.fillRect(0.0,canvas.getHeight(),canvas.getWidth(),-canvas.getHeight());
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.setGlobalBlendMode(BlendMode.SCREEN);
        gc.setGlobalAlpha(0.33);
    }

    public void on() {
        canvas.setOnMousePressed(processPress);
        canvas.setOnMouseDragged(processDrag);
        canvas.setOnMouseReleased(processRelease);
    }

    public void off() {
        canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED,processPress);
        canvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED,processDrag);
        canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED,processRelease);
        GraphicsContextUtils.clear(gc);
    }

    public Point2D getUpperLeftPoint() {
        return GraphicsContextUtils.getRectUpperLeftPoint(anchor,currentPoint);
    }

    public double getSelectionWidth() {
        return 0;
    }

    public double getSelectionHeight() {
        if (anchor != null)
            return 0.0;
        return 0;
    }

    EventHandler<MouseEvent> processPress = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            GraphicsContextUtils.clear(gc);
            anchor = new Point2D(event.getX(),event.getY());
        }
    };

    EventHandler<MouseEvent> processDrag = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            GraphicsContextUtils.clear(gc);
            if (canvas.getBoundsInLocal().contains(event.getX(),event.getY()))
                currentPoint = new Point2D(event.getX(),event.getY());
        }
    };

    EventHandler<MouseEvent> processRelease = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

        }
    };
}
