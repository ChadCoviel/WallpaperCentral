package wallpapercentral.editor;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import wallpapercentral.utils.GraphicsContextUtils;

public class RubberbandSelection implements Toggleable{
    private Point2D anchor;
    private Point2D currentPoint;
    private GraphicsContext gc;
    private Canvas canvas;
    private final BooleanProperty selectionProperty = new SimpleBooleanProperty(false);;

    public RubberbandSelection(Canvas c) {
        this.canvas = c;

        gc = canvas.getGraphicsContext2D();
        clear();
        gc.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.6));
        gc.setStroke(Color.DEEPSKYBLUE);
        gc.setLineWidth(2);
        gc.setGlobalBlendMode(BlendMode.SCREEN);
        gc.setGlobalAlpha(0.33);
    }

    @Override
    public void on() {
        canvas.setOnMousePressed(processPress);
        canvas.setOnMouseDragged(processDrag);
        canvas.setOnMouseEntered(entered);
        canvas.setOnMouseExited(exited);
    }

    @Override
    public void off() {
        canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED,processPress);
        canvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED,processDrag);
        canvas.removeEventHandler(MouseEvent.MOUSE_ENTERED,entered);
        canvas.removeEventHandler(MouseEvent.MOUSE_EXITED,exited);
        reset();
    }

    public void reset() {
        anchor = null;
        currentPoint = null;
        selectionProperty.set(false);
        clear();
    }

    public void clear() {
        GraphicsContextUtils.clear(gc);}

    public Point2D getUpperLeftPoint() {
        if (selectionProperty.get())
            return GraphicsContextUtils.getRectUpperLeftPoint(anchor,currentPoint);
        return null;
    }

    public double getSelectionWidth() {
        if (selectionProperty.get())
            return Math.abs(anchor.getX() - currentPoint.getX());
        return 0;
    }

    public double getSelectionHeight() {
        if (selectionProperty.get())
            return Math.abs(anchor.getY() - currentPoint.getY());
        return 0;
    }

    public BooleanProperty selectionProperty() {return selectionProperty;}

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
            clear();
            if (canvas.getBoundsInLocal().contains(event.getX(),event.getY())) {
                GraphicsContextUtils.fillRectWithAnchor(gc,anchor.getX(),anchor.getY(),event.getX(),event.getY());
                GraphicsContextUtils.strokeRectWithAnchor(gc,anchor.getX(),anchor.getY(),event.getX(),event.getY());
                currentPoint = new Point2D(event.getX(),event.getY());
                selectionProperty.set(true);
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
