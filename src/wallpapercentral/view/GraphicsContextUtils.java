package wallpapercentral.view;

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
            else
                return;
    }
}
