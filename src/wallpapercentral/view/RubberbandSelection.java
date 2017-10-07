package wallpapercentral.view;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
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
        canvas.setOnMouseEntered(entered);
        canvas.setOnMouseExited(exited);
//        canvas.setOnMouseReleased(processRelease);
    }

    public void off() {
        canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED,processPress);
        canvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED,processDrag);
        canvas.removeEventHandler(MouseEvent.MOUSE_ENTERED,entered);
        canvas.removeEventHandler(MouseEvent.MOUSE_EXITED,exited);
//        canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED,processRelease);
        reset();
    }

    public void reset() {
        GraphicsContextUtils.clear(gc);
    }

    public Point2D getUpperLeftPoint() {
        if (selectionCreated())
            return GraphicsContextUtils.getRectUpperLeftPoint(anchor,currentPoint);
        return null;
    }

    public double getSelectionWidth() {
        if (selectionCreated())
            return Math.abs(anchor.getX() - currentPoint.getX());
        return 0;
    }

    public double getSelectionHeight() {
        if (selectionCreated())
            return Math.abs(anchor.getY() - currentPoint.getY());
        return 0;
    }

    public boolean selectionCreated() {
        return anchor != null && currentPoint != null;
    }

    private EventHandler<MouseEvent> processPress = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("Clicked, x:" + event.getSceneX() + " y:" + event.getSceneY());
            reset();
            anchor = new Point2D(event.getX(),event.getY());
        }
    };

    private EventHandler<MouseEvent> processDrag = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("initialX: "+anchor.getX()+" initialY: "+anchor.getY()+"\ncurrentX: "+event.getX()+
                    " currentY: "+event.getY());
            reset();
            if (canvas.getBoundsInLocal().contains(event.getX(),event.getY())) {
                GraphicsContextUtils.fillRectWithAnchor(gc,anchor.getX(),anchor.getY(),event.getX(),event.getY());
                currentPoint = new Point2D(event.getX(),event.getY());
            }
        }
    };

    private EventHandler<MouseEvent> entered = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            canvas.getScene().setCursor(Cursor.CROSSHAIR);
        }
    };

    private EventHandler<MouseEvent> exited = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            canvas.getScene().setCursor(Cursor.DEFAULT);
        }
    };
}
