package wallpapercentral.utils;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public final class GraphicsContextUtils{

    private GraphicsContextUtils() throws Exception {}

    public static void fillRectWithAnchor(GraphicsContext gc, double originx, double originy, double x, double y) {
//        if (x <)
            if(x - originx > 0 && originy - y > 0)
                gc.fillRect(originx,y,x - originx, originy - y);
            else if(x - originx > 0 && originy - y < 0)
                gc.fillRect(originx,originy,x - originx, y - originy);
            else if(x - originx < 0 && originy - y < 0)
                gc.fillRect(x,originy,originx - x,y - originy);
            else if(x - originx < 0 && originy - y > 0)
                gc.fillRect(x,y,originx - x,originy - y);
    }

    public static void strokeRectWithAnchor(GraphicsContext gc, double originx, double originy, double x, double y) {
//        if (x <)
        if(x - originx > 0 && originy - y > 0)
            gc.strokeRect(originx,y,x - originx, originy - y);
        else if(x - originx > 0 && originy - y < 0)
            gc.strokeRect(originx,originy,x - originx, y - originy);
        else if(x - originx < 0 && originy - y < 0)
            gc.strokeRect(x,originy,originx - x,y - originy);
        else if(x - originx < 0 && originy - y > 0)
            gc.strokeRect(x,y,originx - x,originy - y);
    }

    public static Point2D getRectUpperLeftPoint(Point2D anchor, Point2D point) {
        if(point.getX() - anchor.getX() > 0 && anchor.getY() - point.getY() > 0)
            return new Point2D(anchor.getX(),point.getY());
        else if(point.getX() - anchor.getX() > 0 && anchor.getY() - point.getY() < 0)
            return new Point2D(anchor.getX(),anchor.getY());
        else if(point.getX() - anchor.getX() < 0 && anchor.getY() - point.getY() < 0)
            return new Point2D(point.getX(),anchor.getY());
        else if(point.getX() - anchor.getX() < 0 && anchor.getY() - point.getY() > 0)
            return new Point2D(point.getX(),point.getY());
        else
            return null;
    }

    public static void clear(GraphicsContext gc) {
        Canvas canvas = gc.getCanvas();
        gc.fillRect(0.0,canvas.getHeight(),canvas.getWidth(),-canvas.getHeight());
    }
}
